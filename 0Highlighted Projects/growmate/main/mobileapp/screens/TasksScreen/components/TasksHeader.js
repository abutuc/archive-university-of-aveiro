import { View } from "react-native";
import { Text, useTheme } from "react-native-paper";
import React from "react";

export default function TasksHeader() {
    const theme = useTheme();

    return (
        <View style={{ marginTop: 35, justifyContent: "center", alignItems:"center"}}>
            <Text variant={"headlineSmall"} style={{ color: theme.colors.primary, fontWeight: '700'}}>
                Here are your next tasks
            </Text>
        </View>
    )
}