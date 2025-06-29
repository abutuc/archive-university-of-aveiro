import React, { useState, useEffect } from 'react';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Pagination from 'react-bootstrap/Pagination';
import PlantCard from './PlantCard';

export default function PlantsContainer({ plants }) {
  const [plantsData, setPlantsData] = useState(plants);
  useEffect(() => {
    setPlantsData(plants);
  }, [plants]);

  const [currentPage, setCurrentPage] = useState(1);
  const plantsPerPage = 9; // Set the number of plants per page to 9 (3 cards per row)

  const indexOfLastPlant = currentPage * plantsPerPage;
  const indexOfFirstPlant = indexOfLastPlant - plantsPerPage;
  const currentPlants = plantsData.slice(indexOfFirstPlant, indexOfLastPlant);

  const items = [];
  for (let number = 1; number <= Math.ceil(plantsData.length / plantsPerPage); number++) {
    items.push(
      <Pagination.Item
        key={number}
        active={number === currentPage}
        onClick={() => setCurrentPage(number)}
      >
        {number}
      </Pagination.Item>
    );
  }

  return (
    <>
      <Row>
        {currentPlants.map((plant) => (
          <Col key={plant["plantId"]} xs={12} sm={6} md={4} lg={4} className="mb-4">
            <PlantCard plant={plant} />
          </Col>
        ))}
      </Row>
      <Row>
        <Col>
          <Pagination>{items}</Pagination>
        </Col>
      </Row>
    </>
  );
}
