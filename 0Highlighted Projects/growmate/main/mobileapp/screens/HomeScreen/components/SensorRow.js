import {Dimensions, ScrollView, View} from "react-native";
import {Text, useTheme} from "react-native-paper";
import DropDown from "react-native-paper-dropdown";
import {useState} from "react";
import SensorMeasurement from "./SensorMeasurement";

export default function SensorRow({ sensorsValues , sensorsType, sensorTargets }) {
    const theme = useTheme();
    const screenWidth = Dimensions.get('screen').width;
    const [showDropDown, setShowDropDown] = useState(false);
    const [sensorTarget, setSensorTarget] = useState("");
    const sensorTargetsNames = sensorTargets.map(target => target.name);

    return (
        <View style={{ marginTop: 30}} >
            <View style={{marginHorizontal: 30,}}>
                <Text variant={"titleLarge"} style={{ color: theme.colors.primary, fontWeight: '700' }}>{sensorsType} sensors</Text>
            </View>
            <View style={{marginHorizontal: 30}}>
            <DropDown
                mode={"outlined"}
                visible={showDropDown}
                showDropDown={() => setShowDropDown(true)}
                onDismiss={() => setShowDropDown(false)}
                value={sensorTarget}
                setValue={setSensorTarget}
                list={sensorTargetsNames.map((name, index) => ({ label: name, value: name, key: index.toString() }))}
                placeholder={"Select a " + sensorsType}
            />
            </View>
            {sensorTarget &&
                <ScrollView horizontal style={{ marginTop: 10}} contentOffset={{ x:  screenWidth/3, y: 0 }}>
                    {sensorsValues.map((sensorValue, index) => (
                        <SensorMeasurement
                            key={index}
                            type={sensorValue.type}
                            value={sensorValue.value}
                        />
                    ))}
                </ScrollView>
            }
        </View>
    )
}