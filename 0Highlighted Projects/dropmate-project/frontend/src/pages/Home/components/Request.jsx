import React, { useState } from 'react';

// Bootstrap
import Card from 'react-bootstrap/Card';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

export default function Request({ handleRequest }) {
  // Form data
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [city, setCity] = useState('');
  const [address, setAddress] = useState('');
  const [manager, setManager] = useState('');
  const [description, setDescription] = useState('');

  // Form validation
  const [validated, setValidated] = useState(false);

  // Handle Changes
  const handleNameChange = (e) => {
    setName(e.target.value);
  }
  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  }
  const handlePhoneChange = (e) => {
    setPhone(e.target.value);
  }
  const handleCityChange = (e) => {
    setCity(e.target.value);
  }
  const handleAddressChange = (e) => {
    setAddress(e.target.value);
  }
  const handleManagerChange = (e) => {
    setManager(e.target.value);
  }
  const handleDescriptionChange = (e) => {
    setDescription(e.target.value);
  }

  // Handle Submit
  const handleSubmit = (e) => {
    e.preventDefault();
    const form = e.currentTarget;

    if (form.checkValidity() === false) {
      e.stopPropagation();
    } else {
      const request = {
        name: name,
        email: email,
        phone: phone,
        city: city,
        address: address,
        manager: manager,
        description: description,
      };
      handleRequest(request);
    }

    setValidated(true);
  }

  return (
    <>
      <h1>Request</h1>
      <Card>
        <Card.Body>
          <Form noValidate validated={validated} onSubmit={handleSubmit}>
            <Form.Group controlId="name" className='mb-3'>
              <Form.Label>Name</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter name"
                value={name}
                onChange={handleNameChange}
                required
              />
              <Form.Control.Feedback type="invalid">
                Please provide a name.
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group controlId="email" className='mb-3'>
              <Form.Label>Email</Form.Label>
              <Form.Control
                type="email"
                placeholder="Enter email"
                value={email}
                onChange={handleEmailChange}
                required
              />
              <Form.Control.Feedback type="invalid">
                Please provide a valid email.
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group controlId="phone" className='mb-3'>
              <Form.Label>Phone</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter phone"
                value={phone}
                onChange={handlePhoneChange}
                required
              />
              <Form.Control.Feedback type="invalid">
                Please provide a phone number.
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group controlId="city" className='mb-3'>
              <Form.Label>City</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter city"
                value={city}
                onChange={handleCityChange}
                required
              />
              <Form.Control.Feedback type="invalid">
                Please provide a city.
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group controlId="address" className='mb-3'>
              <Form.Label>Address</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter address"
                value={address}
                onChange={handleAddressChange}
                required
              />
              <Form.Control.Feedback type="invalid">
                Please provide an address.
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group controlId="manager" className='mb-3'>
              <Form.Label>Manager Contact</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter manager contact"
                value={manager}
                onChange={handleManagerChange}
                required
              />
              <Form.Control.Feedback type="invalid">
                Please provide a manager contact.
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group controlId="description" className='mb-3'>
              <Form.Label>Description</Form.Label>
              <Form.Control
                as="textarea"
                rows="3"
                placeholder="Enter description"
                value={description}
                onChange={handleDescriptionChange}
                required
              />
              <Form.Control.Feedback type="invalid">
                Please provide a description.
              </Form.Control.Feedback>
            </Form.Group>
            <Button variant="primary" type="submit">
              Submit
            </Button>
          </Form>
        </Card.Body>
      </Card>
    </>
  )
}
