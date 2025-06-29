import axios from "axios";
const baseURL = "http://13.80.159.172/growmate"


export const getSpeciesFromQuery = async (query) => {
    const response = await axios.get(baseURL + "/public/catalogue/species/?query=" + query);
    return response.data;
}
