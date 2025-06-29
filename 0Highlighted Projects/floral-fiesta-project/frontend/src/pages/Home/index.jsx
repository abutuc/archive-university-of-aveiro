import React, { useState, useEffect } from "react";

// Components
import BasicExample from "../../components/Navbar";
import SearchForm from "./components/SearchForm";
import PlantsContainer from "./components/PlantsContainer";
import Container from "react-bootstrap/Container";

// Services
import {
  getAllPlants,
  getAllCategories,
} from "../../services/HomeService";

export default function Home() {
  const [plantsData, setPlantsData] = useState([]);
  const [plantsSearch, setPlantsSearch] = useState([]);
  const [search, setSearch] = useState({});
  const [categories, setCategories] = useState([]);

  const fetchData = async () => {
    const plants = await getAllPlants();
    const categories = await getAllCategories();
    setPlantsData(plants);
    setPlantsSearch(plants);
    setCategories(categories);
  };

  useEffect(() => {
    fetchData();
  }, []);

  useEffect(() => {
    const { name, type } = search;
    const filteredPlants = plantsData.filter((plant) => {
      if (name && type) {
        return (
          plant.name.toLowerCase().includes(name.toLowerCase()) &&
          plant.category["categoryId"] == type
        );
      } else if (name) {
        return plant.name.toLowerCase().includes(name.toLowerCase());
      } else if (type) {
        return plant.category["categoryId"] == type
      } else {
        return true;
      }
    });
    setPlantsSearch(filteredPlants);
  }, [search]);

  return (
    <>
      <BasicExample />
      <Container className="mt-5">
        <SearchForm searchSubmit={setSearch} categories={categories}/>
      </Container>
      <Container className="mt-5">
        {plantsData && <PlantsContainer plants={plantsSearch} />}
      </Container>
    </>
  );
}
