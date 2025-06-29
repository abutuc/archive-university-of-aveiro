import { View } from "react-native";
import { useTheme } from "react-native-paper";
import React from "react";

export default function ThinDivider() {
    const theme = useTheme();

    return (
        <View style={{ marginHorizontal: 30, marginTop: 5 ,height: 1, backgroundColor: theme.colors.primary }}></View>
    )
}