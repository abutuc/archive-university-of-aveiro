import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Pagination } from 'react-bootstrap';

// Bootstrap
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

// Font Awesome
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash, faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';

const TABLE_HEADERS = [
  'Name',
  'Email',
  'Phone',
  'Address',
  'City',
  'Manager Contact',
  '',
];

const ITEMS_PER_PAGE = 5; // Number of items to show per page

export default function ACPTable({ acps, deleteACP }) {
  const [currentPage, setCurrentPage] = useState(1);
  const [info, setInfo] = useState([]);
  const [acpToDelete, setAcpToDelete] = useState(null);
  const [show, setShow] = useState(false);

  useEffect(() => {
    setInfo(acps);
  }, [acps]);

  const navigate = useNavigate();

  const handleView = (event, acp) => {
    navigate(`/acp/${acp.acpId}`);
  };

  const handleShow = (event, acp) => {
    event.preventDefault();
    setAcpToDelete(acp);
    setShow(true);
  };

  const handleDelete = (event) => {
    event.preventDefault();
    deleteACP(acpToDelete.acpId);
    handleClose();
  };

  const handleClose = () => {
    setShow(false);
  };

  const handlePageChange = (page) => {
    setCurrentPage(page);
  };

  // Pagination
  const indexOfLastItem = currentPage * ITEMS_PER_PAGE;
  const indexOfFirstItem = indexOfLastItem - ITEMS_PER_PAGE;
  const currentItems = info.slice(indexOfFirstItem, indexOfLastItem);

  const totalPages = Math.ceil(info.length / ITEMS_PER_PAGE);

  return (
    <>
      <Container className="mt-5 mb-5">
        <Row className="mb-3">
          <Col>
            <h2>Registered ACPs</h2>
          </Col>
        </Row>
        <Row>
          <Col>
            <Table striped bordered hover id="registeredACPsTable">
              <thead>
                <tr>
                  {TABLE_HEADERS.map((header, index) => (
                    <th key={index}>{header}</th>
                  ))}
                </tr>
              </thead>
              <tbody>
                {currentItems.length ? (
                  currentItems.map((acp, index) => (
                    <tr key={index} id={`row${acp.acpId}`}>
                      <td>{acp.name}</td>
                      <td>{acp.email}</td>
                      <td>{acp.telephoneNumber}</td>
                      <td>{acp.address}</td>
                      <td>{acp.city}</td>
                      <td>{acp.managerContact}</td>
                      <td>
                        <Row>
                          <Col md={6}>
                            <Button
                              variant="primary"
                              onClick={(event) => handleView(event, acp)}
                              id={`viewACP${acp.acpId}`}
                            >
                              <FontAwesomeIcon icon={faMagnifyingGlass} />
                            </Button>
                          </Col>
                          <Col md={6}>
                            <Button
                              variant="danger"
                              onClick={(event) => handleShow(event, acp)}
                              id={`deleteACP${acp.acpId}`}
                            >
                              <FontAwesomeIcon icon={faTrash} />
                            </Button>
                          </Col>
                        </Row>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan={TABLE_HEADERS.length}>No ACPs found</td>
                  </tr>
                )}
              </tbody>
            </Table>
          </Col>
        </Row>
        {info.length > ITEMS_PER_PAGE && (
          <Row>
            <Col className="d-flex justify-content-center">
              <Pagination>
                {Array.from({ length: totalPages }).map((_, index) => (
                  <Pagination.Item
                    key={index}
                    active={index + 1 === currentPage}
                    onClick={() => handlePageChange(index + 1)}
                  >
                    {index + 1}
                  </Pagination.Item>
                ))}
              </Pagination>
            </Col>
          </Row>
        )}
      </Container>
      {acpToDelete && (
        <Modal show={show} onHide={handleClose}>
          <Modal.Body>
            Are you sure you want to delete {acpToDelete.name}?
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Close
            </Button>
            <Button variant="danger" onClick={handleDelete} id={`confirmDeleteACP`}>
              Delete
            </Button>
          </Modal.Footer>
        </Modal>
      )}
    </>
  );
}
