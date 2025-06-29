import axios from "axios";
const baseURL = "http://13.80.159.172/growmate";
import { setItem, getItem } from "../cache";

export const getPlantSpeciesInfo = async (userId, plantId) => {
    const cacheKey = "plantSpeciesInfo" + userId + plantId;

    const cacheData = await getItem(cacheKey);
    if (cacheData !== null) {
        console.log("cache");
        return cacheData;
    }    

    const response = await axios.get(baseURL + "/user/" + userId + "/inventory/" + plantId + "/speciesinfo");
    await setItem(cacheKey, response.data);

    return response.data;
}

export const getSpeciesInfo = async (specieID) => {
    const cacheKey = "speciesInfo" + specieID;

    const cacheData = await getItem(cacheKey);
    if (cacheData !== null) {
        console.log("cache");
        return cacheData;
    }

    const response = await axios.get(baseURL + "/public/catalogue/species/" + specieID);
    await setItem(cacheKey, response.data);

    return response.data;
}