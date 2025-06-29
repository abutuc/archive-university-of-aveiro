import axios from 'axios';

const plantURI = import.meta.env.VITE_FLORALFIESTA_API_URI + '/plant';
const categoryURI = import.meta.env.VITE_FLORALFIESTA_API_URI + '/category';

const getAllPlants = async () => {
    const response = await axios.get(plantURI + '/all');
    return response.data;
}

const getAllCategories = async () => {
    const response = await axios.get(categoryURI + '/all');
    return response.data;
}


export {
    getAllPlants,
    getAllCategories,
}