import {View} from "react-native";
import {Text, TextInput, useTheme} from "react-native-paper";
import React, {useState} from "react";
import DropDown from "react-native-paper-dropdown";

export default function DivisionInfo({setName, luminosityTarget, setLuminosityTarget}){
    const theme = useTheme();

    const luminosityOptions = [
        {name: "Sunny light", value: 3},
        {name: "High-light", value: 2},
        {name: "Medium-light", value: 1},
        {name: "Low-light", value: 0}
    ];

    const [showLuminosityDropDown, setShowLuminosityDropDown] = useState(false);

    return (
        <View style={{marginTop: 20, marginBottom: 30}}>
            <View style={{marginHorizontal: 50}}>
                <Text variant={"bodyMedium"} style={{ color: theme.colors.primary }}>Division name</Text>
            </View>
            <TextInput style={{ width: 300, backgroundColor: theme.colors.background, fontSize: 14, marginLeft: 50, marginBottom: 10}}
                       underlineColor={theme.colors.primaryContainer}
                       activeUnderlineColor={theme.colors.primary}
                       textColor={theme.colors.onBackground}
                       placeholder={"Insert division name"} placeholderTextColor={theme.colors.tertiary}
                       onChangeText={text => setName(text)}
            />

            <View style={{marginHorizontal: 50, marginTop: 10}}>
                <Text variant={"bodyMedium"} style={{ color: theme.colors.primary }}>Division luminosity</Text>
            </View>
            <View style={{marginHorizontal: 50}}>
                <DropDown
                    mode={"outlined"}
                    visible={showLuminosityDropDown}
                    showDropDown={() => setShowLuminosityDropDown(true)}
                    onDismiss={() => setShowLuminosityDropDown(false)}
                    value={luminosityTarget}
                    setValue={setLuminosityTarget}
                    list={luminosityOptions.map((luminosity, index) => ({ label: luminosity.name, value: luminosity.value, key: index.toString() }))}
                    placeholder={"Select division luminosity"}
                    dropDownStyle={{maxHeight: 200 }}
                />
            </View>
        </View>
    )
}