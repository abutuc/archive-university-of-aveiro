import axios from "axios";
const baseURL = "http://13.80.159.172/growmate";
import { setItem, getItem } from "../cache";

const login = async (email, password) => {
    if (email === "" || password === ""){
        return false;
    }

    const cacheKey = "login";
    const cacheData = await getItem(cacheKey);
    if (cacheData !== null) {
        console.log("cache");
        return cacheData;
    }

    const response = await axios.post(baseURL + "/public/login?email=" + email+"&password=" +password).catch( () => {
        return false;
    })
    if (response.status === 200){
        await setItem(cacheKey, response.data);
        return response.data;
    }
    else {
        return false;
    }
}

export {login}