import {View} from "react-native";
import {Text, TextInput, useTheme} from "react-native-paper";
import React from "react";


export default function AddPlantationDate({setInputDate, setValidDate}){
    const theme = useTheme();

    const validateDate = (date) => {
        const dateRegex = new RegExp("^(19|20)\\d\\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$")
        setInputDate(date);
        setValidDate(dateRegex.test(date));
    }

    return (
        <View style={{marginHorizontal: 30, marginTop: 10, marginBottom: 10}}>
            <Text variant={"titleMedium"} style={{color: theme.colors.primary, fontWeight: "600", marginLeft: 30}}>
                Add plantation date:
            </Text>

            <View style={{marginLeft: 30}}>
                <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14}}
                           underlineColor={theme.colors.primaryContainer}
                           activeUnderlineColor={theme.colors.primary}
                           textColor={theme.colors.onBackground}
                           placeholder={"Plantation Date (yyyy-mm-dd)"} placeholderTextColor={theme.colors.tertiary}
                           onChangeText={text => validateDate(text)}
                />
            </View>

        </View>
    )
}