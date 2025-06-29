import { useState } from "react";
import { View } from "react-native";
import { RadioButton, Text } from "react-native-paper";

export default function SensorTypeRadioGroup({sensorType, setSensorType}) {

    return (
        <View style={{marginLeft: 16}}>
            <Text variant="bodyLarge">Select Sensor Type:</Text>
            <RadioButton.Group onValueChange={newValue => setSensorType(newValue)} value={sensorType}>
                <View style={{ flexDirection: 'row', alignItems: 'center', marginTop: 5 }}>
                    <View style={{ flexDirection: 'row', alignItems: 'center' }}>
                        <RadioButton value="" />
                        <Text>All</Text>
                    </View>
                    <View style={{ marginLeft: 16, flexDirection: 'row', alignItems: 'center' }}>
                        <RadioButton value="division" />
                        <Text>Division</Text>
                    </View>
                    <View style={{ marginLeft: 16, flexDirection: 'row', alignItems: 'center' }}>
                        <RadioButton value="plant" />
                        <Text>Plant</Text>
                    </View>
                </View>
            </RadioButton.Group>
        </View>
    )
}