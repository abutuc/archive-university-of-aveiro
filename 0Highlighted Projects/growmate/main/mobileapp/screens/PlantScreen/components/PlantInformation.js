import React, { useState, useEffect } from "react"
import { View } from "react-native";
import { Text, TextInput, useTheme, Button } from "react-native-paper";

export default function PlantInformation({ plant, division, handleSave }) {
    const theme = useTheme();

    // Form
    const [text, setText] = useState(plant.name);
    const [inputDate, setInputDate] = useState(plant.plantationDate);
    const [currentDivision, setCurrentDivision] = useState(division === null ? "None" : division.name);

    const dateRegex = /^(19|20)\d{2}-(0[1-9]|1[0-2])-(0[1-9]|1\d|2\d|3[01])$/;

    const [dateIsValid, setDateIsValid] = useState(true);
    const [nameIsValid, setNameIsValid] = useState(true);

    const nameValidate = (name) => {
        if (name.length > 0){
            setNameIsValid(true);
            setText(name);
        }
        else {
            setNameIsValid(false);
        }
    }

    const dateValidate = (date) => {
        if (dateRegex.test(date)){
            setDateIsValid(true);
        }
        else {
            setDateIsValid(false);
        }
        setInputDate(date);
    }

    useEffect(() => {
        setText(plant.name);
        setInputDate(plant.plantationDate);
        if (division === null) {
            setCurrentDivision("None");
        }
        else {
            setCurrentDivision(division.name);
        }
    }, [plant, division]);

    return (
        <View style={{ paddingBottom: 100, paddingHorizontal: 50 }}>
            <Text variant="titleMedium" style={{ color: theme.colors.primary }}>{plant.name}'s information:</Text>
            <TextInput
                label="Name"
                value={text}
                onChangeText={text => nameValidate(text)}
                contentStyle={{ backgroundColor: theme.colors.background }}
                underlineColor={nameIsValid ?  theme.colors.primary : theme.colors.error}
            />

            <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14, marginBottom: 5}}
                       underlineColor={dateIsValid ? theme.colors.primaryContainer : theme.colors.error}
                       activeUnderlineColor={dateIsValid ? theme.colors.primary : theme.colors.error}
                       textColor={theme.colors.onBackground}
                       value={inputDate}
                       placeholder={"Plantation Date (yyyy-mm-dd)"} placeholderTextColor={theme.colors.tertiary}
                       onChangeText={text => dateValidate(text)}
            />

            <TextInput
                label="Division"
                value={currentDivision}
                contentStyle={{ backgroundColor: theme.colors.background }}
                underlineColor={theme.colors.primary}
                disabled
            />
            <Button
                mode="contained"
                onPress={() => handleSave(text, inputDate, currentDivision)}
                style={{ marginTop: 20, backgroundColor: theme.colors.primary }}
                disabled={!nameIsValid || !dateIsValid}
            >
                Save
            </Button>
        </View>
    )
}