import React, { useState, useEffect } from "react";

// Bootstrap
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";

// Components
import BasicExample from "../../components/Navbar";
import OrderCard from "./components/OrderCard";

// Services
import { getOngoingOrders, getDeliveredOrders } from "../../services/PurchaseService";

export default function Tracking() {
  const [displayedOrders, setDisplayedOrders] = useState(5);
  const [user] = useState(JSON.parse(localStorage.getItem("user")));

  const [ongoingOrders, setOngoingOrders] = useState([]);
  const [deliveredOrders, setDeliveredOrders] = useState([]);

  const fetchData = async () => {
    const ongoingOrders = await getOngoingOrders(user.userId);
    const deliveredOrders = await getDeliveredOrders(user.userId);
    setOngoingOrders(ongoingOrders);
    setDeliveredOrders(deliveredOrders);
  };

  useEffect(() => {
    fetchData();
  }, []);

  const loadMoreOrders = () => {
    setDisplayedOrders(prevCount => prevCount + 5);
  };

  return (
    <>
      <BasicExample />
      <Container className="mt-5 mb-5">
        <Row className="justify-content-center">
          <Col xs={12} md={6}>
            <h1 className="text-center">Tracking</h1>
          </Col>

          <Col xs={12} md={6}>
            <h1 className="text-center">Delivered</h1>
          </Col>

        </Row>
        <Row className="justify-content-center">
          <Col xs={12} md={6}>
            {ongoingOrders.slice(0, displayedOrders).map((order, index) => (
              <OrderCard key={index} order={order} />
            ))}
            {ongoingOrders.length > displayedOrders && (
              <Button
                variant="outline-primary"
                className="mt-3"
                onClick={loadMoreOrders}
              >
                Load More
              </Button>
            )}
          </Col>

          <Col xs={12} md={6}>
            {deliveredOrders.map((order, index) => (
              <OrderCard key={index} order={order} />
            ))}
          </Col>
        </Row>
      </Container>
    </>
  );
}
