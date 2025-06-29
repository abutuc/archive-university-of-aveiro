import React, { useState, useEffect } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

export default function SearchForm({ searchSubmit, categories }) {
  const [plantName, setPlantName] = useState('');
  const [plantType, setPlantType] = useState('');

  const [plantTypes, setPlantTypes] = useState([]);

  useEffect(() => {
    setPlantTypes(categories);
  }, [categories]);

  const handleSearchChange = (e) => {
    setPlantName(e.target.value);
  };

  const handleTypeChange = (e) => {
    setPlantType(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Search:', plantName);
    console.log('Type:', plantType);

    const search = {
      name: plantName,
      type: plantType,
    };

    searchSubmit(search);
  };

  const handleReset = (e) => {
    e.preventDefault();
    setPlantName('');
    setPlantType('');

    searchSubmit({});
  };

  return (
    <Form onSubmit={handleSubmit} onReset={handleReset}>
      <Row className="align-items-center justify-content-center">
        <Col xs={12} md={4} lg={3}>
          <Form.Group controlId="plantName">
            <Form.Control
              type="text"
              placeholder="Search for a plant"
              value={plantName}
              onChange={handleSearchChange}
            />
          </Form.Group>
        </Col>
        <Col xs={12} md={4} lg={3}>
          <Form.Select aria-label="plantType" onChange={handleTypeChange}>
            <option value="">Plant Category</option>
            {plantTypes && plantTypes.map((type) => (
              <option key={type.categoryId} value={type.categoryId}>
                {type.name}
              </option>
            ))}
          </Form.Select>
        </Col>
        <Col xs={12} md={2} lg={1}>
          <Button variant="primary" type="submit" className="w-100">
            Search
          </Button>
        </Col>
        <Col xs={12} md={2} lg={1}>
          <Button variant="secondary" type="reset" className="w-100">
            Reset
          </Button>
        </Col>
      </Row>
    </Form>
  );
}
