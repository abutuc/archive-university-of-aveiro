import React, { useState, useEffect } from 'react';

// Bootstrap
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Table from 'react-bootstrap/Table';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';
import Pagination from 'react-bootstrap/Pagination';
import Button from 'react-bootstrap/Button';

// Font Awesome
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';

const ITEMS_PER_PAGE = 10; // Number of items to display per page

export default function ParcelsWaitingDeliveryTable({ parcels, updateStatus }) {
    const [data, setData] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);

    useEffect(() => {
        if (parcels) {
            setData(parcels);
        } else {
            setData([]);
        }
    }, [parcels]);

    // Modal
    const [parcel, setParcel] = useState(null);
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);

    const handleShow = (event, parcel) => {
        event.preventDefault();
        setParcel(parcel);
        setShow(true);
    };

    // Form
    const [deliveryID, setDeliveryID] = useState('');

    const handleSubmission = (event) => {
        event.preventDefault();

        // Update parcel status
        updateStatus(parcel.parcelId, deliveryID, 'IN_DELIVERY');

        // Reset form
        setDeliveryID('');
        handleClose();
    };

    // Pagination
    const indexOfLastItem = currentPage * ITEMS_PER_PAGE;
    const indexOfFirstItem = indexOfLastItem - ITEMS_PER_PAGE;
    const [currentItems, setCurrentItems] = useState([]);

    useEffect(() => {
        setCurrentItems(data.slice(indexOfFirstItem, indexOfLastItem));
    }, [currentPage, data, indexOfFirstItem, indexOfLastItem]);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <Container>
            <Row>
                <Col>
                    <h2>Parcels in Delivery</h2>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Table striped bordered hover id="inDeliveryTable">
                        <thead>
                            <tr>
                                <th>Parcel ID</th>
                                <th>Weight</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {currentItems.length > 0 ? (
                                currentItems.map((parcel, index) => (
                                    <tr key={index}>
                                        <td>{parcel.parcelId}</td>
                                        <td>{parcel.weight}</td>
                                        <td>
                                            <Button
                                                variant="primary"
                                                onClick={(event) => handleShow(event, parcel)}
                                            >
                                                <FontAwesomeIcon icon={faMagnifyingGlass} />
                                            </Button>
                                        </td>
                                    </tr>
                                ))
                            ) : (
                                <tr>
                                    <td colSpan="7">No parcels found.</td>
                                </tr>
                            )}
                        </tbody>
                    </Table>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Pagination>
                        <Pagination.Prev
                            onClick={() =>
                                setCurrentPage((prevPage) =>
                                    prevPage > 1 ? prevPage - 1 : prevPage
                                )
                            }
                            disabled={currentPage === 1}
                        />
                        {[...Array(Math.ceil(data.length / ITEMS_PER_PAGE))].map(
                            (item, index) => (
                                <Pagination.Item
                                    key={index + 1}
                                    active={index + 1 === currentPage}
                                    onClick={() => paginate(index + 1)}
                                >
                                    {index + 1}
                                </Pagination.Item>
                            )
                        )}
                        <Pagination.Next
                            onClick={() =>
                                setCurrentPage((prevPage) =>
                                    prevPage < Math.ceil(data.length / ITEMS_PER_PAGE)
                                        ? prevPage + 1
                                        : prevPage
                                )
                            }
                            disabled={
                                currentPage === Math.ceil(data.length / ITEMS_PER_PAGE)
                            }
                        />
                    </Pagination>
                </Col>
            </Row>
            {parcel && (
                <Modal show={show} onHide={handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Parcel {parcel.parcelId}</Modal.Title>
                    </Modal.Header>
                    <Form>
                        <Modal.Body>
                            <Form.Group controlId="formParcelDeliveryID">
                                <Form.Label>Delivery ID</Form.Label>
                                <Form.Control
                                    type="text"
                                    placeholder="Delivery ID"
                                    value={deliveryID}
                                    onChange={(event) => setDeliveryID(event.target.value)}
                                />
                            </Form.Group>
                        </Modal.Body>
                        <Modal.Footer>
                            <Button variant="primary" onClick={handleSubmission}>
                                Save
                            </Button>
                        </Modal.Footer>
                    </Form>
                </Modal>
            )}
        </Container>
    );
}
