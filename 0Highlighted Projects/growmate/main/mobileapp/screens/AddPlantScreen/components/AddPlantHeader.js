import { View } from "react-native";
import { Text, IconButton, useTheme } from "react-native-paper";
import React from "react";
import { useNavigation } from '@react-navigation/native';

export default function AddPlantHeader({text, division}) {
    const navigation = useNavigation();
    const theme = useTheme();

    return (
        <View style={{ marginTop: 25, marginBottom: 5, flexDirection: 'row', alignItems: 'center', justifyContent: 'flex-start' }}>
            <View style={{ flex: 1, marginLeft: 10, marginRight: 20 }}>
                <IconButton
                    icon='chevron-left'
                    size={35}
                    iconColor={theme.colors.primary}
                    onPress={() => {
                        if (division) {
                            navigation.goBack();
                        }
                        else {
                            navigation.navigate('Home', {reload: false, variance: ""});
                        }
                    }}
                />
            </View>
            <View style={{ flex: 5 }}>
                <Text variant={"headlineSmall"} style={{ color: theme.colors.primary, fontWeight: '700', textAlign: 'center' }}>{text}</Text>
            </View>
            <View style={{ flex: 1 }} />
        </View>
    )
}