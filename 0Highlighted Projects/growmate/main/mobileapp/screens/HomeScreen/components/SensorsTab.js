import React, { useState, useEffect } from "react";
import { View, ScrollView, Dimensions } from "react-native";
import { List, Card, useTheme, Text } from "react-native-paper";

import SensorTypeRadioGroup from "./SensorTypeRadioGroup";
import SensorDropdown from "./SensorDropdown";
import SensorInfoCard from "./SensorInfoCard";
import SensorDialog from "./SensorDIalog";

import { userID } from "../../../user";
import { deleteSensor as deleteSensorService, getSensorLastMeasurement, editSensor as editSensorService } from "../../../service/HomeScreenService";

export default function SensorsTab({ userDivisions, sensors, userPlants, handleUpdate }) {
  const theme = useTheme();
  const screenHeight = Dimensions.get('screen').height;
  const screenWidth = Dimensions.get("screen").width;

  const [sensorsList, setSensorsList] = useState(sensors);

  const [divisionsList, setDivisionsList] = useState([]);
  const [plantsList, setPlantsList] = useState([]);
  const [division, setDivision] = useState("");
  const [plant, setPlant] = useState("");
  const [sensorType, setSensorType] = useState("");
  const [filteredSensors, setFilteredSensors] = useState([]);
  const [sensorModalVisible, setSensorModalVisible] = useState(false);
  const [selectedSensor, setSelectedSensor] = useState(null);
  const [selectedSensorLastMeasurement, setSelectedSensorLastMeasurement] = useState(null);

  useEffect(() => {
    const formatDivisionsList = (data) =>
      [{ label: 'All', value: "" }, ...data.map(item => ({ label: item.name, value: item.id }))];

    const formatPlantsList = (data) =>
      [{ label: 'All', value: "" }, ...data.map(item => ({ label: item.name, value: item.id }))];

    setDivisionsList(formatDivisionsList(userDivisions));
    setPlantsList(formatPlantsList(userPlants));
  }, [userDivisions, userPlants]);

  useEffect(() => {
    setSensorsList(sensors);
    const filteredSensorsList = sensorsList.filter((sensor) => {
      if (sensorType === 'division') {
        if (division === "") {
          return sensor.type === 'division'; // Include all division sensors
        } else {
          return sensor.type === 'division' && sensor.division_id === String(division); // Matched division sensor
        }
      } else if (sensorType === 'plant') {
        if (plant === "") {
          return sensor.type === 'plant'; // Include all plant sensors
        } else {
          return sensor.type === 'plant' && sensor.plant_id === String(plant); // Matched plant sensor
        }
      }
      return true; // No filter applied for other sensor types
    }, [sensorsList, sensorType, division, plant, sensors]);

    setFilteredSensors(filteredSensorsList);
  }, [sensorType, division, plant, sensorsList, sensors]);

  const countSensors = filteredSensors.length;

  const editSensor = async (name, sensor, dropDownValue) => {
    let sensorType = sensor.type === "division" ? 0 : 1;

    await editSensorService(userID, sensor.original_id, sensorType, name, dropDownValue);

    // update sensor name
    const newSensors = sensorsList.map((item) => {
      if (item.id === sensor.id) {
        item.name = name;
        if (sensorType === 0) {
          item.division_id = dropDownValue;
        } else {
          item.plant_id = dropDownValue;
        }
      }
      return item;
    });

    setSensorsList(newSensors);
  }

  const deleteSensor = async (sensor) => {
    console.log("Deleting " + sensor.name);

    let sensorType = sensor.type === "division" ? 0 : 1;

    await deleteSensorService(userID, sensor.original_id, sensorType).then(() => {
      handleUpdate();
      const newSensors = sensorsList.filter((item) => item.original_id !== sensor.original_id);
      setSensorsList(newSensors);
    });
  }

  // Get selected sensor last measurement value
  useEffect(() => {
    const getSensorLastMeasurementValue = async () => {
      if (selectedSensor) {
        const sensorType = selectedSensor.type === "division" ? 0 : 1;
        const lastMeasurement = await getSensorLastMeasurement(userID, selectedSensor.original_id, sensorType);
        setSelectedSensorLastMeasurement(lastMeasurement);
      }
    }
    getSensorLastMeasurementValue();
  }, [selectedSensor]);

  const handleDismiss = () => {
    setSensorModalVisible(false);
    setSelectedSensor(null);
    setSelectedSensorLastMeasurement(null);
  }

  return (
    <>
      <View style={{ margin: 10 }}>
        <Card contentStyle={{ backgroundColor: theme.colors.background }}>
          <List.Accordion title="Filters">
            <List.Item
              title=""
              left={(props) => (
                <SensorTypeRadioGroup sensorType={sensorType} setSensorType={setSensorType} />
              )}
            />
            {sensorType === 'division' && (
              <List.Item
                title=""
                left={(props) => (
                  <View style={{ marginLeft: 16, width: screenWidth - 100 }}>
                    <SensorDropdown value={division} setValue={setDivision} list={divisionsList} label="Division" />
                  </View>
                )}
              />
            )}
            {sensorType === 'plant' && (
              <List.Item
                title=""
                left={(props) => (
                  <View style={{ marginLeft: 16, width: screenWidth - 100 }}>
                    <SensorDropdown value={plant} setValue={setPlant} list={plantsList} label="Plant" />
                  </View>
                )}
              />
            )}
          </List.Accordion>
        </Card>
        <View style={{ marginTop: 10 }}>
          <Text variant="titleLarge">{countSensors} Sensor{countSensors !== 1 ? 's' : ''}</Text>
        </View>
        <ScrollView style={{ height: screenHeight / 2 }}>
          {filteredSensors.map((sensor) => (
            <SensorInfoCard
              key={sensor.id}
              sensor={sensor}
              onPress={() => {
                setSelectedSensor(sensor);
                setSensorModalVisible(true);
              }} />
          ))}
          <View style={{ height: 200 }} />
        </ScrollView>
      </View>

      {selectedSensor && selectedSensorLastMeasurement && <SensorDialog
        sensor={selectedSensor}
        lastMeasurement={selectedSensorLastMeasurement}
        visible={sensorModalVisible}
        onDismiss={() => handleDismiss()}
        onSave={(name, sensor, dropDownValue) => editSensor(name, sensor, dropDownValue)}
        onDelete={(sensor) => deleteSensor(sensor)}
        dropDownList={selectedSensor.type === 'division' ? userDivisions : userPlants}
      />}
    </>
  );
}
