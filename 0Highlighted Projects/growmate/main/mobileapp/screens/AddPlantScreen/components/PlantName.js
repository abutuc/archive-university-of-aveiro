import {View} from "react-native";
import {Text, TextInput, useTheme} from "react-native-paper";
import React from "react";

export default function PlantName({isImage, setName}){
    const theme = useTheme()
    return (
        <View>
            {isImage ?
                <View style={{marginHorizontal: 30, marginTop: 20, marginBottom: 30 ,alignItems: "center", justifyContent: "center"}}>
                    <Text variant={"bodyMedium"} style={{color: theme.colors.primary, marginBottom: 10}}>
                        What do you want to name your plant?
                    </Text>
                    <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14}}
                               underlineColor={theme.colors.primaryContainer}
                               activeUnderlineColor={theme.colors.primary}
                               textColor={theme.colors.onBackground}
                               placeholder={"Plant name"} placeholderTextColor={theme.colors.tertiary}
                               onChangeText={text => setName(text)}
                    />
                </View>

                :

                <View style={{marginHorizontal: 30, marginTop: 20, marginBottom: 75 ,alignItems: "center", justifyContent: "center"}}>
                    <Text variant={"bodyMedium"} style={{color: theme.colors.primary, marginBottom: 10}}>
                        What do you want to name your plant?
                    </Text>
                    <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14}}
                               underlineColor={theme.colors.primaryContainer}
                               activeUnderlineColor={theme.colors.primary}
                               textColor={theme.colors.onBackground}
                               placeholder={"Plant name"} placeholderTextColor={theme.colors.tertiary}
                               onChangeText={text => setName(text)}
                    />
                </View>
            }

        </View>
    )
}