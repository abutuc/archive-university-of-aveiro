import React from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';

export default function CartItem({ item, handleRemoveFromCart, handleIncreaseQuantity, handleDecreaseQuantity, formatPrice }) {
    return (
        <Card key={item.id} className="mb-3">
            <Card.Body>
                <Container fluid>
                    <Row>
                        <Col xs={4} sm={4} md={4}>
                            <div
                                className="image-container"
                                style={{
                                    width: '100%',
                                    height: '100%',
                                    borderRadius: '0',
                                    overflow: 'hidden',
                                }}
                            >
                                <Card.Img
                                    src={item.photo}
                                    alt={item.name}
                                    style={{
                                        width: '100%',
                                        height: '100%',
                                        objectFit: 'cover',
                                    }}
                                />
                            </div>
                        </Col>
                        <Col xs={8} sm={8} md={8}>
                            <div className="d-flex flex-column h-100 justify-content-between">
                                <div>
                                    <Card.Title>{item.name}</Card.Title>
                                    <Card.Text>
                                        Price: {formatPrice(item.price)}
                                    </Card.Text>
                                </div>
                            </div>
                        </Col>
                    </Row>
                </Container>
                <Container fluid>
                    <Row className="mt-3">
                        <Col>
                            <Button
                                variant="danger"
                                size="sm"
                                onClick={() => handleRemoveFromCart(item.plantId)}
                            >
                                Remove
                            </Button>
                        </Col>
                        <Col className="d-flex justify-content-end">
                            <div>
                                <Button
                                    variant="secondary"
                                    size="sm"
                                    onClick={() => handleDecreaseQuantity(item.plantId)}
                                    disabled={item.quantity === 1}
                                    style={{ marginRight: '0.5rem' }}
                                >
                                    -
                                </Button>
                                <span>{item.quantity}</span>
                                <Button
                                    variant="secondary"
                                    size="sm"
                                    onClick={() => handleIncreaseQuantity(item.plantId)}
                                    style={{ marginLeft: '0.5rem' }}
                                >
                                    +
                                </Button>
                            </div>
                        </Col>
                    </Row>
                </Container>
            </Card.Body>
        </Card>
    );
};