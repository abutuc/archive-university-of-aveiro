import axios from "axios";
const baseURL = "http://13.80.159.172/growmate"


const getFirstName = async (id) => {
  const response = await axios.get(baseURL + "/user/" + id);
  return response.data;
}

const getPlants = async (id) => {
  const response = await axios.get(baseURL + "/user/" + id + "/plants");
  return response.data;
};

const getDivisionsAndAssociatedPlants = async (id) => {
  let response = await axios.get(baseURL + "/user/" + id + "/divisions");
  const divisions = response.data;
  const data = [];
  for (let i = 1; i <= divisions.length; i++) {
    data.push(
      {
        id: divisions[i - 1].id,
        name: divisions[i - 1].name,
        plants: (await axios.get(baseURL + "/user/" + id + "/division/" + divisions[i - 1].id + "/plants")).data
      })
  }
  return data;
}

const addPlantToDivision = async (userID, plantID, divisionID) => {
  await axios.post(baseURL + "/user/" + userID + "/division/" + divisionID + "/plants/" + plantID)
}

const changePlantDivision = async (userID, plantID, divisionID, newDivisionID) => {
  await axios.put(
    baseURL + "/user/" + userID + "/division/" + divisionID + "/plants/" + plantID,
    null,
    { params: { newDivisionID: newDivisionID } }
  )
}

const removePlantFromDivision = async (userID, plantID, divisionID) => {
  await axios.put(
    baseURL + "/user/" + userID + "/division/" + divisionID + "/plants/" + plantID,
    null,
    { params: { newDivisionID: null } }
  )
}

const getSensors = async (userID) => {
  let sensorsFiltered = [];

  // First get all the sensors associated with plants
  let response = await axios.get(baseURL + "/user/" + userID + "/sensors?type=1");
  const plants = response.data;

  // Then put the plant sensors with the right format
  Object.keys(plants).forEach((k) => {
    plants[k].map((plant) => {
      sensorsFiltered.push({
        id: `${plant.id}_plant`,
        original_id: plant.id,
        name: plant.name,
        sensorCode: plant.sensorCode,
        plant_id: k,
        type: "plant",
      });
    });
  });

  // Get the sensors associated with divisions
  let response2 = await axios.get(baseURL + "/user/" + userID + "/sensors?type=0");
  const divisions = response2.data;

  // Then put the division sensors with the right format
  Object.keys(divisions).forEach((k) => {
    divisions[k].map((division) => {
      sensorsFiltered.push({
        id: `${division.id}_division`,
        original_id: division.id,
        name: division.name,
        sensorCode: division.sensorCode,
        division_id: k,
        type: "division",
      });
    });
  });

  return sensorsFiltered;
};

const deleteSensor = async (userID, sensorID, sensorType) => {
  await axios.delete(
    baseURL + '/user/' + userID + '/sensors/' + sensorID + '?sensorType=' + sensorType,
  );
};

const getSensorLastMeasurement = async (userID, sensorID, sensorType) => {
  let response = await axios.get(
    baseURL + '/user/' + userID + '/sensors/' + sensorID + '/last?sensorType=' + sensorType,
  );
  const data = response.data;

  let sensorLastMeasurement;

  Object.keys(data).forEach((key) => {
    const measurement = data[key];
    if (measurement !== null) {
      const type = key.split("_")[1]; // Extract the value after the underscore
      sensorLastMeasurement = {
        id: measurement.id,
        value: measurement.measurement,
        date: measurement.postDate,
        type: type, // Assign the extracted value to the type property
      };
    }
  });

  if (sensorLastMeasurement === undefined) {
    sensorLastMeasurement = "No data";
  }

  return sensorLastMeasurement;
};

const editSensor = async (userID, sensorID, sensorType, newName, newAssociatedID) => {
  await axios.put(
    baseURL + '/user/' + userID + '/sensors/' + sensorID + '?sensorType=' + sensorType + '&newName=' + newName + '&newAssociatedID=' + newAssociatedID,
  );
};

const deleteDivision = async (userID, divisionID) => {
    await axios.delete(
        baseURL + '/user/' + userID + '/division/' + divisionID,
    );
}



export { getPlants, getDivisionsAndAssociatedPlants, addPlantToDivision, changePlantDivision, removePlantFromDivision,
  getFirstName, getSensors, deleteSensor, getSensorLastMeasurement, editSensor, deleteDivision }