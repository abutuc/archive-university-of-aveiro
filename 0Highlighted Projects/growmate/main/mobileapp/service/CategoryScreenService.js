import axios from "axios";
const baseURL = "http://13.80.159.172/growmate";
import { setItem, getItem } from "../cache";

export const getCategorySpecies = async (id) => {
    const cacheKey = "categorySpecies" + id;

    const cacheData = await getItem(cacheKey);
    if (cacheData !== null) {
        console.log("cache");
        return cacheData;
    }

    const response = await axios.get(baseURL + "/public/catalogue/categories/" + id + "/species");
    await setItem(cacheKey, response.data);
    
    return response.data;
};

export const getSuggestedSpecies = async (userID) => {
    const cacheKey = "suggestedSpecies";

    const cacheData = await getItem(cacheKey);
    if (cacheData !== null) {
        console.log("cache");
        return cacheData;
    }
    const response = await axios.get(baseURL + "/user/" + userID + "/recommendation");
    await setItem(cacheKey, response.data);
    return response.data;
}