import React from "react";
import Card from "react-bootstrap/Card";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";

export default function OrderCard({ order }) {
    // {
    //     "orderId": 1,
    //     "totalPrice": 27.0,
    //     "pickupCode": "KKKK0000",
    //     "description": "Package 1",
    //     "status": "IN_DELIVERY",
    //     "deliveryDate": "2023-06-07",
    //     "pickupDate": null,
    //     "acpID": 3,
    // },

    return (
        <Card className="mb-3">
            <Card.Body>
                <Row>
                    <Col xs={12} md={6}>
                        <Card.Title>Order ID: {order.orderId}</Card.Title>
                        <Card.Text>
                            <strong>Price:</strong> ${order.totalPrice}
                        </Card.Text>
                        <Card.Text>
                            <strong>Pickup Code:</strong> {order.pickupCode}
                        </Card.Text>
                        <Card.Text>
                            <strong>Description:</strong> {order.description}
                        </Card.Text>
                        <Card.Text>
                            <strong>Status:</strong> {order.status}
                        </Card.Text>
                        <Card.Text>
                            <strong>Delivery Date:</strong> {order.deliveryDate}
                        </Card.Text>
                        <Card.Text>
                            <strong>Pickup Date:</strong> {order.pickupDate}
                        </Card.Text>
                        <Card.Text>
                            <strong>ACP ID:</strong> {order.acpID}
                        </Card.Text>
                    </Col>
                </Row>
            </Card.Body>
        </Card>
    );
}
