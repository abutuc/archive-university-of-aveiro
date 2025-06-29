import React, { useContext, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import Modal from 'react-bootstrap/Modal';
import { CartContext } from '../../../components/Cart/CartContext';

export default function PlantCard({ plant }) {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem('user'));
  const { dispatch } = useContext(CartContext);
  const [showModal, setShowModal] = useState(false);

  const handleAddToCart = () => {
    if (!user) {
      navigate('/login');
    } else {
      dispatch({ type: 'ADD_TO_CART', payload: plant });
      setShowModal(true);
    }
  };

  useEffect(() => {
    if (showModal) {
      const timeout = setTimeout(() => {
        setShowModal(false);
      }, 1000); // Set the desired duration in milliseconds
      return () => clearTimeout(timeout);
    }
  }, [showModal]);

  return (
    <Card style={{ width: '18rem' }} className="d-flex flex-column h-100">
      <Card.Img variant="top" src={plant.photo} style={{height: 250}}/>
      <Card.Body className="d-flex flex-column">
        <Card.Title>{plant.name}</Card.Title>
        <Card.Text>{plant.description}</Card.Text>
        <Card.Text>{plant.price} €</Card.Text>
        <div className="mt-auto">
          <Button variant="primary" onClick={handleAddToCart}>
            Add to cart
          </Button>
        </div>
      </Card.Body>

      <Modal show={showModal} onHide={() => setShowModal(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>Item Added to Cart</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <p>{plant.name} has been successfully added to your cart.</p>
        </Modal.Body>
      </Modal>
    </Card>
  );
}
