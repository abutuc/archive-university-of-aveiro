import axios from "axios";
import { getSensorLastMeasurement } from "./HomeScreenService";

const baseURL = "http://13.80.159.172/growmate"

const getPlantInfo = async (userID, plantID) => {
    const response = await axios.get(baseURL + "/user/" + userID + "/inventory/" + plantID);
    return response.data;
}

const getSensorsForPlant = async (userID, plantID, division) => {
    let sensors = [];

    await axios.get(baseURL + "/user/" + userID + "/sensors/plant/" + plantID)
        .then((plantSensors) => plantSensors.data.map((sensor) => sensors.push({
            id: sensor["id"],
            name: sensor["name"],
            sensorCode: sensor["sensorCode"],
            type: 1,
        })));

    if (division !== null) {
        await axios.get(baseURL + "/user/" + userID + "/sensors/division/" + division["id"])
            .then((divisionSensors) => divisionSensors.data.map((sensor) => sensors.push({
                id: sensor["id"],
                name: sensor["name"],
                sensorCode: sensor["sensorCode"],
                type: 0,
            })));
    }

    for (let i = 0; i < sensors.length; i++) {
        sensors[i]["value"] = await getSensorLastMeasurement(userID, sensors[i]["id"], sensors[i]["type"]);
    }

    return sensors;
}

const getAllDivisions = async (userID) => {
    const response = await axios.get(baseURL + "/user/" + userID + "/divisions");
    return response.data;
}

const groupSensorData = (data) => {
    const groupedData = {};

    for (const sensorType in data) {
        for (const item of data[sensorType]) {
            const date = item.postDate.slice(0, 10);
            const type = sensorType.split("_")[1];

            if (!groupedData[type]) {
                groupedData[type] = {};
            }

            if (!groupedData[type][date]) {
                groupedData[type][date] = [];
            }

            groupedData[type][date].push(item);
        }
    }

    return groupedData;
};

const getLastThreeDaysMeasurements = async (userID, plantID) => {
    try {
        const response = await axios.get(
            `${baseURL}/user/${userID}/sensors/last/plant/${plantID}`
        );

        const sensorData = response.data;
        const groupedData = groupSensorData(sensorData);
        return groupedData;
    } catch (error) {
        console.error("Error fetching measurements:", error);
        return null;
    }
};

const getPlantTasksTodo = async (userID, plantID) => {
    const response = await axios.get(baseURL + "/user/tasks/" + userID + "/plant/" + plantID + "/todo");
    return response.data;
}

const updateTask = async (userID, plantID, taskID) => {
    const response = await axios.put(
        baseURL + "/user/tasks/" + userID + "/plant/" + plantID + "/updateTask/" + taskID,
        null,
    );
    return response.data;
}

const deletePlant = async (userID, plantID, dead) => {
    await axios.delete(baseURL + "/user/" + userID + "/inventory/" + plantID + "?dead=" + dead);
}

const getTaskSettings = async (userID, plantID, taskType) => {
    const response = await axios.get(baseURL + "/user/tasks/" + userID + "/settings?idPlant=" + plantID + "&taskType=" + taskType);
    return response.data
}

const updateTaskDate = async (userID, taskID, newDate) => {
    await axios.put(baseURL + "/user/tasks/" + userID + "/task/" + taskID + "?taskDate=" + newDate);
}

const toggleTaskMode = async (userID, plantID, taskType, taskFrequency) => {
    if (taskFrequency) {
        await axios.put(baseURL + "/user/tasks/" + userID + "/plant/" + plantID + "/toggleMode?taskType=" + taskType + "&frequency=" + taskFrequency);
    }
    else {
        await axios.put(baseURL + "/user/tasks/" + userID + "/plant/" + plantID + "/toggleMode?taskType=" + taskType);
    }

}

const updateTaskFrequency = async (userID, plantID, taskType, frequency) => {
    await axios.put(baseURL + "/user/tasks/" + userID + "/plant/" + plantID + "/frequency?taskType=" + taskType + "&frequency=" + frequency);
}

const updatePlantInfo = async (userID, plantID, plantName, plantationDate) => {

    await axios.put(baseURL + "/user/" + userID + "/inventory/" + plantID + "?nomePlanta=" + plantName + "&plantation-date=" + plantationDate);
}

export {
    getPlantInfo, getAllDivisions, getSensorsForPlant, getPlantTasksTodo, updateTask, deletePlant, getTaskSettings,
    updateTaskDate, toggleTaskMode, updateTaskFrequency, getLastThreeDaysMeasurements, updatePlantInfo
}