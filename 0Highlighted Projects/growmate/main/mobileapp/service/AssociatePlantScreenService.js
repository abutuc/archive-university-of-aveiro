import axios from "axios";
const baseURL = "http://13.80.159.172/growmate"


export const createNewPlant = async (userId, name, photoURL, speciesId, divisionId, sensorId, plantationDate) => {
    if (divisionId === null && sensorId === null) {
        await axios.post(baseURL +
            "/user/" + userId +
            "/addplant?plantName=" + name +
            "&photoURL=" + photoURL +
            "&species-id=" + speciesId +
            "&plantation-date=" + plantationDate);
    }
    else if (divisionId === null && sensorId !== null) {
        await axios.post(baseURL +
            "/user/" + userId +
            "/addplant?plantName=" + name +
            "&photoURL=" + photoURL +
            "&species-id=" + speciesId +
            "&sensor-id=" + sensorId +
            "&plantation-date=" + plantationDate);
    }
    else if (divisionId !== null && sensorId === null) {
        await axios.post(baseURL +
            "/user/" + userId +
            "/addplant?plantName=" + name +
            "&photoURL=" + photoURL +
            "&species-id=" + speciesId +
            "&division-id=" + divisionId +
            "&plantation-date=" + plantationDate);
    }
    else {
        await axios.post(baseURL +
            "/user/" + userId +
            "/addplant?plantName=" + name +
            "&photoURL=" + photoURL +
            "&species-id=" + speciesId +
            "&division-id=" + divisionId +
            "&sensor-id=" + sensorId +
            "&plantation-date=" + plantationDate);
    }
}

export const getPlantSensors = async (userId) => {
    const response = await axios.get(baseURL + "/user/" + userId + "/sensors?type=1")
    return response.data;
}

export const getDivisions = async (userId) => {
    const response = await axios.get(baseURL + "/user/" + userId + "/divisions");
    return response.data;
}

