import React, { useState, useEffect } from 'react';

// Bootstrap
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Table from 'react-bootstrap/Table';
import Pagination from 'react-bootstrap/Pagination';

const ITEMS_PER_PAGE = 10; // Number of items to display per page

export default function ParcelsDelivered({ parcels }) {
    const [data, setData] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);

    useEffect(() => {
        if (parcels) {
            setData(parcels);
        } else {
            setData([]);
        }
    }, [parcels]);

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
                    <h2>Parcels Delivered</h2>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Table striped bordered hover id="deliveredTable">
                        <thead>
                            <tr>
                                <th>Parcel ID</th>
                                <th>Weight</th>
                                <th>Delivery Code</th>
                                <th>Delivery Date</th>
                                <th>Pickup Code</th>
                                <th>Pickup Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            {currentItems.length > 0 ? (
                                currentItems.map((parcel, index) => (
                                    <tr key={index}>
                                        <td>{parcel.parcelId}</td>
                                        <td>{parcel.weight}</td>
                                        <td>{parcel.deliveryCode}</td>
                                        <td>{parcel.deliveryDate}</td>
                                        <td>{parcel.pickupCode}</td>
                                        <td>{parcel.pickupDate}</td>
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
        </Container>
    );
}
