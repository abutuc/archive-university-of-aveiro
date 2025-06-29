import React, { useState, useEffect } from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Table from 'react-bootstrap/Table';
import Pagination from 'react-bootstrap/Pagination';

const TABLE_HEADERS = [
  'ID',
  'Parcel Limit',
  'Parcels in Delivery',
  'Parcels Waiting for Pickup',
  'Total Parcels',
];

export default function ACPStatisticsTable({ stats }) {
  const [info, setInfo] = useState();
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 5; // Number of items to display per page

  useEffect(() => {
    setInfo(stats);
  }, [stats]);

  // Calculate the index range of items to display on the current page
  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentItems = info?.slice(indexOfFirstItem, indexOfLastItem);

  // Calculate the total number of pages
  const totalPages = Math.ceil(info?.length / itemsPerPage);

  // Handle page change
  const handlePageChange = (pageNumber) => setCurrentPage(pageNumber);

  if (!stats) {
    return (
      <>
        <Container className="mt-5 mb-5">
          <Row className="mb-3">
            <Col>
              <h2>ACPs Statistics</h2>
            </Col>
          </Row>
          <Row>
            <Col>
              <Table striped bordered hover>
                <thead>
                  <tr>
                    {TABLE_HEADERS.map((header, index) => (
                      <th key={index}>{header}</th>
                    ))}
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td colSpan={TABLE_HEADERS.length}>Loading...</td>
                  </tr>
                </tbody>
              </Table>
            </Col>
          </Row>
        </Container>
      </>
    );
  }

  return (
    <>
      <Container className="mt-5 mb-5">
        <Row className="mb-3">
          <Col>
            <h2>ACPs Statistics</h2>
          </Col>
        </Row>
        <Row>
          <Col>
            <Table striped bordered hover id="ACPStatisticsTable">
              <thead>
                <tr>
                  {TABLE_HEADERS.map((header, index) => (
                    <th key={index}>{header}</th>
                  ))}
                </tr>
              </thead>
              <tbody>
                {currentItems && currentItems.length > 0 ? (
                  currentItems.map((acp, index) => (
                    <tr key={index}>
                      <td>{acp.acpName}</td>
                      <td>{acp.parcels['deliveryLimit']}</td>
                      <td>{acp.parcels['parcels_in_delivery']}</td>
                      <td>{acp.parcels['parcels_waiting_pickup']}</td>
                      <td>{acp.parcels['total_parcels']}</td>
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
        {info && info.length > itemsPerPage && (
          <Row>
            <Col className="d-flex justify-content-center">
              <Pagination>
                {Array.from(Array(totalPages).keys()).map((pageNumber) => (
                  <Pagination.Item
                    key={pageNumber + 1}
                    active={pageNumber + 1 === currentPage}
                    onClick={() => handlePageChange(pageNumber + 1)}
                  >
                    {pageNumber + 1}
                  </Pagination.Item>
                ))}
              </Pagination>
            </Col>
          </Row>
        )}
      </Container>
    </>
  );
}
