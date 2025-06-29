import {View} from "react-native";
import {RadioButton, Text, TextInput, useTheme} from "react-native-paper";
import React, {useState} from "react";
import DropDown from "react-native-paper-dropdown";
import {validCode} from "../../../service/AddSensorService";

export default function SensorInfo({plants, divisions, setSensorName, setSensorCode, setSensorType, setOwnerID, sensorType, ownerID, codeIsValid, setCodeIsValid}){
    const theme = useTheme();

    const [showDivisionDropDown, setShowDivisionDropDown] = useState(false);
    const [showPlantDropDown, setShowPlantDropDown] = useState(false);

    const validateCode = (code) => {
        if (validCode.test(code)){
            setCodeIsValid(true);
            setSensorCode(code);
        }
        else {
            setCodeIsValid(false);
        }
    }

    return (
        <View style={{marginTop: 20, marginBottom: 30}}>
            <View style={{marginHorizontal: 50}}>
                <Text variant={"bodyMedium"} style={{ color: theme.colors.primary }}>Sensor name</Text>
            </View>
            <TextInput style={{ width: 300, backgroundColor: theme.colors.background, fontSize: 14, marginLeft: 50, marginBottom: 20}}
                       underlineColor={theme.colors.primaryContainer}
                       activeUnderlineColor={theme.colors.primary}
                       textColor={theme.colors.onBackground}
                       placeholder={"Insert sensor name"} placeholderTextColor={theme.colors.tertiary}
                       onChangeText={text => setSensorName(text)}
            />
            <View style={{marginHorizontal: 50}}>
                <Text variant={"bodyMedium"} style={{ color: theme.colors.primary }}>Sensor code</Text>
            </View>
            <View style={{marginHorizontal: 50}}>
                <Text variant={"bodySmall"} style={{ color: theme.colors.secondary}}>Must be in the format AAA000</Text>
            </View>
            <TextInput style={{ width: 300, backgroundColor: theme.colors.background, fontSize: 14, marginLeft: 50, marginBottom: 20}}
                       underlineColor={codeIsValid ? theme.colors.primaryContainer : theme.colors.error}
                       activeUnderlineColor={codeIsValid ? theme.colors.primary : theme.colors.error}
                       textColor={theme.colors.onBackground}
                       placeholder={"Insert sensor code"} placeholderTextColor={theme.colors.tertiary}
                       onChangeText={text => validateCode(text)}
            />

            <View style={{marginHorizontal: 50}}>
                <Text variant={"bodyMedium"} style={{ color: theme.colors.primary }}>Sensor type</Text>
            </View>
            <View style={{marginHorizontal: 50, flexDirection: "row", marginBottom: 10}}>
                <View style={{alignItems: "center", justifyContent: "center", marginRight: 10}}>
                    <Text variant={"bodyMedium"} style={{ color: theme.colors.onBackground }}>
                        Division
                    </Text>
                </View>
                <RadioButton value="Division"
                             status={sensorType === 0 ? 'checked' : 'unchecked'}
                             onPress={() => {
                                 setSensorType(0);
                                 setOwnerID(null);
                             }}
                />
                <View style={{alignItems: "center", justifyContent: "center", marginLeft: 60, marginRight: 10}}>
                    <Text variant={"bodyMedium"} style={{ color: theme.colors.onBackground }}>
                        Plant
                    </Text>
                </View>
                <RadioButton value="Plant"
                             status={sensorType === 1 ? 'checked' : 'unchecked'}
                             onPress={() => {
                                 setSensorType(1);
                                 setOwnerID(null);
                             }}
                />
            </View>

            {sensorType === 0 ?
                <View style={{marginBottom: 10}}>
                    <View style={{marginHorizontal: 50, marginTop: 10}}>
                        <Text variant={"bodyMedium"} style={{ color: theme.colors.primary }}>Associate a Division </Text>
                    </View>
                    <View style={{marginHorizontal: 50}}>
                        <DropDown
                            mode={"outlined"}
                            visible={showDivisionDropDown}
                            showDropDown={() => setShowDivisionDropDown(true)}
                            onDismiss={() => setShowDivisionDropDown(false)}
                            value={ownerID}
                            setValue={setOwnerID}
                            list={divisions.map((division, index) => ({ label: division.name, value: division.id, key: index.toString() }))}
                            placeholder={"Select a division"}
                            dropDownStyle={{maxHeight: 200 }}
                        />
                    </View>
                </View>
                :
                <View style={{marginBottom: 10}}>
                    <View style={{marginHorizontal: 50, marginTop: 10}}>
                        <Text variant={"bodyMedium"} style={{ color: theme.colors.primary }}>Associate a Plant </Text>
                    </View>
                    <View style={{marginHorizontal: 50}}>
                        <DropDown
                            mode={"outlined"}
                            visible={showPlantDropDown}
                            showDropDown={() => setShowPlantDropDown(true)}
                            onDismiss={() => setShowPlantDropDown(false)}
                            value={ownerID}
                            setValue={setOwnerID}
                            list={plants.map((plant, index) => ({ label: plant.name, value: plant.id, key: index.toString() }))}
                            placeholder={"Select a plant"}
                            dropDownStyle={{maxHeight: 200 }}
                        />
                    </View>
                </View>
            }
        </View>
    )
}