import React, { useState, useEffect } from 'react';

// Bootstrap
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Table from 'react-bootstrap/Table';
import Form from 'react-bootstrap/Form';

export default function Parcels({ parcels }) {
  const [data, setData] = useState(parcels);
  const [status, setStatus] = useState('All');
  const [currentPage, setCurrentPage] = useState(1);
  const [itemsPerPage, setItemsPerPage] = useState(10);

  useEffect(() => {
    if (status === 'All') {
      setData(parcels);
    } else {
      setData(parcels.filter((parcel) => parcel.parcelStatus === status));
    }
  }, [status, parcels]);

  const handleStatusChange = (event) => {
    setStatus(event.target.value);
  };

  const totalPages = Math.ceil(data.length / itemsPerPage);

  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentItems = data.slice(indexOfFirstItem, indexOfLastItem);

  const renderPagination = () => {
    const pageNumbers = [];

    for (let i = 1; i <= totalPages; i++) {
      pageNumbers.push(i);
    }

    return (
      <ul className="pagination">
        {pageNumbers.map((number) => (
          <li
            key={number}
            className={`page-item${currentPage === number ? ' active' : ''}`}
          >
            <button className="page-link" onClick={() => setCurrentPage(number)}>
              {number}
            </button>
          </li>
        ))}
      </ul>
    );
  };

  return (
    <Container>
      <Row>
        <Col>
          <h2>Parcels</h2>
        </Col>
        <Col>
          <Form>
            <Form.Select
              name="deliveryStatus"
              aria-label="Select Delivery Status"
              onChange={handleStatusChange}
              value={status}
            >
              <option value="All">All</option>
              <option value="IN_DELIVERY">In Delivery</option>
              <option value="WAITING_FOR_PICKUP">Waiting for Pickup</option>
            </Form.Select>
          </Form>
        </Col>
      </Row>
      <Row>
        <Col>
          <Table striped bordered hover id="parcelsTable">
            <thead>
              <tr>
                <th>Parcel ID</th>
                <th>Delivery Code</th>
                <th>Pickup Code</th>
                <th>Weight</th>
                <th>Delivery Date</th>
                <th>Pickup Date</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              {currentItems.length > 0 ? (
                currentItems.map((parcel, index) => (
                  <tr key={index}>
                    <td>{parcel.parcelId}</td>
                    <td>{parcel.deliveryCode}</td>
                    <td>{parcel.pickupCode}</td>
                    <td>{parcel.weight}</td>
                    <td>{parcel.deliveryDate}</td>
                    <td>{parcel.pickupDate}</td>
                    <td>{parcel.parcelStatus}</td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="7">No parcels found</td>
                </tr>
              )}
            </tbody>
          </Table>
          {renderPagination()}
        </Col>
      </Row>
    </Container>
  );
}
