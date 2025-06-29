import React from "react";
import { View } from "react-native";
import { useTheme } from 'react-native-paper';
import { StatusBar } from 'expo-status-bar';

export default function GreenBar(){
    const theme = useTheme();

    return (
        <>
            <StatusBar style="light" backgroundColor={theme.colors.primaryContainer}/>
            <View style={{marginBottom: 25}}/>
        </>
    )
}