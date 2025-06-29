import axios from "axios";
const baseURL = "http://13.80.159.172/growmate"

export const createNewSensor = async (userId, sensorType, sensorName, sensorCode, ownerId) => {
    const response = axios.post(baseURL +
        "/user/" + userId + "/sensors/" +
        "?sensorType=" + sensorType +
        "&sensorName=" + sensorName +
        "&sensorCode=" + sensorCode +
        "&ownerID=" + ownerId)
    ;
}

export const getDivisions = async (id) => {
    const response = await axios.get(baseURL + "/user/" + id + "/divisions");
    return response.data;
}

export const validCode = new RegExp(
    '^[A-Z][A-Z][A-Z][0-9][0-9][0-9]$'
);