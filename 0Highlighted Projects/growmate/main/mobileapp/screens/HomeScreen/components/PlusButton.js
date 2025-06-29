import React from 'react';
import {Dimensions, TouchableOpacity, View} from 'react-native';
import {IconButton, useTheme} from 'react-native-paper';
import {useNavigation} from "@react-navigation/native";

export default function PlusButton({ index}) {
    const screenHeight = Dimensions.get('screen').height;
    const screenWidth = Dimensions.get('screen').width;
    const navigation = useNavigation();
    const theme = useTheme();
    let page;
    switch (index) {
        case 0:
            page = "AddPlant"
            break
        case 1:
            page = "AddDivision"
            break
        case 2:
            page = "AddSensor"
            break
    }
    return (
        <TouchableOpacity onPress={() => {
            navigation.navigate(page, {speciesID: null, scientificName: null, destiny: "Home"});
        }} style={{
            position: 'absolute',
            bottom: screenHeight/12 * 2,
            right: screenWidth/12,
            borderRadius: 50,
            backgroundColor: theme.colors.primary,
            width: 50,
            height: 50,
            justifyContent: 'center',
            alignItems: 'center',
            elevation: 5, // to add shadow
        }}>
        <View>
            <IconButton icon="plus" iconColor={theme.colors.background} size={24}/>
        </View>
        </TouchableOpacity>
    );
};
