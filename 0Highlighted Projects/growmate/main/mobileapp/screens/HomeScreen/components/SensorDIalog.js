import React, { useState } from "react";
import { Dialog, Portal, Button, TextInput, Text, useTheme } from "react-native-paper";
import { View, StyleSheet } from "react-native";

import SensorDropdown from "./SensorDropdown";
import { getMeasurementUnit, lastTimeUpdated } from "../../../utils";

export default function SensorDialog({ sensor, lastMeasurement, visible, onDismiss, onSave, onDelete, dropDownList }) {
    const [editedName, setEditedName] = useState(sensor.name);
    const [dropDownValue, setDropDownValue] = useState(sensor.type === "plant" ? sensor.plant_id : sensor.division_id);
    const [alert, setAlert] = useState(false);
    const dropDownLabel = sensor.type === "plant" ? "Plant" : "Division";
    const theme = useTheme();

    const dropDownListFormatted = dropDownList.map(({ name, id }) => ({ label: name, value: String(id) }));

    const handleNameChange = (name) => {
        setEditedName(name);
    };

    const handleSave = () => {
        onSave(editedName, sensor, dropDownValue);
        onDismiss();
    };

    const handleDelete = () => {
        setAlert(false);
        onDelete(sensor);
        onDismiss();
    };

    return (
        <Portal>
            <Dialog visible={visible} onDismiss={onDismiss} style={{ backgroundColor: theme.colors.background }}>
                <Dialog.Title>Sensor Details</Dialog.Title>
                <Dialog.Content>
                    <View style={styles.row}>
                        <Text variant="bodyLarge">Sensor Name:</Text>
                        <TextInput value={editedName} onChangeText={handleNameChange} style={styles.input} />
                    </View>
                    <View style={styles.row}>
                        <Text variant="bodyLarge">Sensor Type:</Text>
                        <Text style={styles.text} variant="bodyMedium">{sensor.type}</Text>
                    </View>
                    <View style={styles.row}>
                        <Text variant="bodyLarge">Sensor Code:</Text>
                        <Text style={styles.text} variant="bodyMedium">{sensor.sensorCode}</Text>
                    </View>
                    <View style={styles.row}>
                        <Text variant="bodyLarge">Last Measurement:</Text>
                        {lastMeasurement !== "No data" ? (
                            <>
                                <Text style={styles.text} variant="bodyMedium">{lastMeasurement.value} {getMeasurementUnit(lastMeasurement.type)}</Text>
                                <Text style={styles.text} variant="bodySmall">{lastTimeUpdated(lastMeasurement.date)}</Text>
                            </>
                        ) : (
                            <Text style={styles.text} variant="bodyMedium">No measurements</Text>
                        )}
                    </View>
                    <SensorDropdown value={dropDownValue} setValue={setDropDownValue} list={dropDownListFormatted} label={dropDownLabel} />
                </Dialog.Content>
                <Dialog.Actions>
                    <Button onPress={handleSave} buttonColor={theme.colors.primary} textColor={theme.colors.background}>Save</Button>
                    <Button onPress={() => setAlert(true)} buttonColor={theme.colors.error} textColor={theme.colors.background}>Delete</Button>
                    <Button onPress={onDismiss} buttonColor={theme.colors.secondary} textColor={theme.colors.background}>Close</Button>
                </Dialog.Actions>
            </Dialog>
            <Dialog visible={alert} onDismiss={() => setAlert(false)} style={{ backgroundColor: theme.colors.background }}>
                <Dialog.Title>Alert</Dialog.Title>
                <Dialog.Content>
                    <Text variant="bodyMedium">Are you sure you want to delete the sensor?</Text>
                </Dialog.Content>
                <Dialog.Actions>
                    <Button onPress={() => setAlert(false)} buttonColor={theme.colors.error} textColor={theme.colors.background}>Cancel</Button>
                    <Button onPress={handleDelete} buttonColor={theme.colors.primary} textColor={theme.colors.background}>Confirm</Button>
                </Dialog.Actions>
            </Dialog>
        </Portal>
    );
}

const styles = StyleSheet.create({
    row: {
        flexDirection: 'column',
        alignItems: 'flex-start',
        justifyContent: 'space-between',
        marginVertical: 5
    },
    text: {
        marginBottom: 5
    },
    input: {
        width: '100%',
        backgroundColor: 'white'
    },
});
