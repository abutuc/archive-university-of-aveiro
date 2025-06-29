import { useState } from "react";
import { View, Dimensions } from "react-native";
import { Text, useTheme } from "react-native-paper";
import DropDown from "react-native-paper-dropdown";

export default function SensorDropdown({ value, setValue, list, label }) {
    const theme = useTheme();
    const [showDropdown, setShowDropdown] = useState(false);

    return (
        <View>
            <View style={{ marginBottom: 5 }}>
                <Text variant="bodyLarge">Select {label}:</Text>
            </View>
            <DropDown
                label={label}
                mode="outlined"
                visible={showDropdown}
                showDropDown={() => setShowDropdown(true)}
                onDismiss={() => setShowDropdown(false)}
                value={value}
                setValue={setValue}
                list={list}
            />
        </View>
    )
}