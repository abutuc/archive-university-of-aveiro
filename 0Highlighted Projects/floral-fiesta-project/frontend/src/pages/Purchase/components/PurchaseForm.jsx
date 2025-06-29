import React, { useState, useContext, useEffect } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import { CartContext } from '../../../components/Cart/CartContext';

const PurchaseForm = ({ setFormData, allACP }) => {
  const [acps, setACPs] = useState([]);

  useEffect(() => {
    setACPs(allACP);
  }, [allACP]);

  const [selectedLocation, setSelectedLocation] = useState('');

  const { cartState } = useContext(CartContext);
  const hasCartItems = cartState.items.length > 0;

  const handleLocationChange = (e) => {
    setSelectedLocation(e.target.value);
  };

  const handleFormSubmit = (e) => {
    e.preventDefault();

    setFormData({
      acpId: selectedLocation,
    });

    setSelectedLocation('');
  };


  return (
    <Card className="p-4">
      <Card.Title>DropMate Service</Card.Title>
      <Card.Body>
        <Form onSubmit={handleFormSubmit}>

          <Form.Group controlId="location" className="mb-3 mt-3">
            <Form.Control
              as="select"
              value={selectedLocation}
              onChange={handleLocationChange}
              disabled={!hasCartItems}
            >
              <option value="">Select a location</option>
              {acps && acps.map((acp, idx) => (
                <option key={idx} value={acp.acpId}>
                  {acp.name}
                </option>
              ))}
            </Form.Control>
          </Form.Group>

          <Button variant="primary" type="submit" disabled={!hasCartItems}>
            Submit
          </Button>
        </Form>
      </Card.Body>
    </Card>
  );
};

export default PurchaseForm;
