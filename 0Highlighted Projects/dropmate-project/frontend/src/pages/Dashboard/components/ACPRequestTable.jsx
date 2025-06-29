import React, { useState, useEffect } from 'react';

// Bootstrap
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';

// Font Awesome
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';

const TABLE_HEADERS = [
  'Name',
  'Email',
  'Phone',
  'Address',
  'City', 
  'Status',
  '',
];

export default function ACPRequestTable({ requests, changeAcpStatus }) {
  const [info, setInfo] = useState();

  useEffect(() => {
    setInfo(requests);
  }, [requests]);

  // Modal
  const [acpRequest, setAcpRequest] = useState(null);
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);

  const handleShow = (event, acp) => {
    event.preventDefault();
    setAcpRequest(acp);
    setShow(true);
  };

  const handleStatusChange = (event) => {
    event.preventDefault();
    const { value } = event.target;
    setAcpRequest({ ...acpRequest, status: value });
  };

  const handleSave = () => {
    changeAcpStatus(acpRequest.acpId, acpRequest.status);
    handleClose();
  };

  const getStatusText = (status) => {
    switch (status.toString()) {
      case "0":
        return 'Pending';
      case "1":
        return 'Rejected';
      case "2":
        return 'Approved';
      default:
        return 'Unknown';
    }
  };

  if (!info) {
    return (
      <Container className="mt-5 mb-5">
        <Row className="mb-3">
          <Col>
            <h2>ACPs Requests</h2>
          </Col>
        </Row>
        <Row>
          <Col>
            <p>Loading...</p>
          </Col>
        </Row>
      </Container>
    )
  }

  return (
    <>
      <Container className="mt-5 mb-5">
        <Row className="mb-3">
          <Col>
            <h2>ACPs Requests</h2>
          </Col>
        </Row>
        <Row>
          <Col>
            <Table striped bordered hover id="acpReviewTable">
              <thead>
                <tr>
                  {TABLE_HEADERS.map((header, index) => (
                    <th key={index}>{header}</th>
                  ))}
                </tr>
              </thead>
              <tbody>
                {info.length >= 1 ? (
                  info.map((acp, index) => (
                    <tr key={index} id={`acpReviewRow-${acp.acpId}`}>
                      <td>{acp.name}</td>
                      <td>{acp.email}</td>
                      <td>{acp.telephoneNumber}</td>
                      <td>{acp.address}</td>
                      <td>{acp.city}</td>
                      <td>{getStatusText(acp.status)}</td>
                      <td>
                        {acp.status === 0 && <Row>
                          <Col md={6}>
                            <Button
                              variant="primary"
                              onClick={(event) => handleShow(event, acp)}
                              id={`acpReviewButton-${acp.acpId}`}
                            >
                              <FontAwesomeIcon icon={faMagnifyingGlass} />
                            </Button>
                          </Col>
                        </Row>}
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan={TABLE_HEADERS.length}>No Requests found</td>
                  </tr>
                )}
              </tbody>
            </Table>
          </Col>
        </Row>
      </Container>
      {acpRequest && (
        <Modal show={show} onHide={handleClose} id="acpReviewModal">
          <Modal.Header closeButton>
            <Modal.Title>{acpRequest.name}</Modal.Title>
          </Modal.Header>
          <Modal.Body>{acpRequest.description}</Modal.Body>
          <Modal.Footer>
            <Form.Select onChange={handleStatusChange} value={acpRequest.status} name={`acpStatus-${acpRequest.acpId}`}>
              <option value={0}>Pending</option>
              <option value={2}>Approved</option>
              <option value={1}>Rejected</option>
            </Form.Select>
            <Button variant="primary" onClick={handleSave} id="submitACPReview">
              Save
            </Button>
          </Modal.Footer>
        </Modal>
      )}
    </>
  );
}
