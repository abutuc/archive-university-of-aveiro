import React, { useState, useEffect } from 'react';

// Bootstrap
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Table from 'react-bootstrap/Table';

const TABLE_HEADERS = [
    'Name',
    'Email',
    'Phone',
    'Address',
    'City',
    'Field',
    'Manager Contact',
    'Orders Delivered',
];

export default function PartnerTable({ partners }) {
    const [info, setInfo] = useState(partners);

    useEffect(() => {
        setInfo(partners);
    }, [partners]);

    return (
        <>
            <Container className="mt-5 mb-5">
                <Row className="mb-3">
                    <Col>
                        <h2>Registered Partners</h2>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <Table striped bordered hover id="registeredPartnersTable">
                            <thead>
                                <tr>
                                    {TABLE_HEADERS.map((header, index) => (
                                        <th key={index}>{header}</th>
                                    ))}
                                </tr>
                            </thead>
                            <tbody>
                                {info ? (
                                    info.map((partner, index) => (
                                        <tr key={index}>
                                            <td>{partner.name}</td>
                                            <td>{partner.email}</td>
                                            <td>{partner.telephoneNumber}</td>
                                            <td>{partner.address}</td>
                                            <td>{partner.city}</td>
                                            <td>{partner.field}</td>
                                            <td>{partner.managerContact}</td>
                                            <td>{partner.ordersDelivered}</td>
                                        </tr>
                                    ))
                                ) : (
                                    <tr>
                                        <td colSpan={TABLE_HEADERS.length}>No Partners found</td>
                                    </tr>
                                )}
                            </tbody>
                        </Table>
                    </Col>
                </Row>
            </Container>
        </>
    );
}