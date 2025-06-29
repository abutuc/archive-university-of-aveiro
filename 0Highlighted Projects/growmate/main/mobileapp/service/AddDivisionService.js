import axios from "axios";
const baseURL = "http://13.80.159.172/growmate"


export const createNewDivision = async (userId, name, luminosity) => {
    const response = axios.post(baseURL +
        "/user/" + userId +
        "/divisions?name=" + name +
        "&luminosity=" + luminosity);
}