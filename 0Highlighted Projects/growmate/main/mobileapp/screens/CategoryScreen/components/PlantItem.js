import {TouchableOpacity, View} from "react-native";
import {Card, IconButton, Text, useTheme} from "react-native-paper";
import React from "react";
import {useNavigation} from "@react-navigation/native";


export default function PlantItem({name, image, difficulty, anonymous, speciesID, scientificName}){
    const theme = useTheme();
    const navigation = useNavigation();
    const image_width = 70;
    const image_height = 100;
    const stars = [];
    for (let i = 0; i < 5; i++){
        if (i < difficulty){
            stars.push(
                <IconButton key={i} icon={"star"} iconColor={theme.colors.primary}  size={12} style={{ marginLeft: -6, marginRight: -6, marginTop: -5 }}/>
            )
        }
        else {
            stars.push(
                <IconButton key={i} icon={"star-outline"} size={12} style={{ marginLeft: -6, marginRight: -6, marginTop: -5 }}/>
            )
        }
    }
    return (
        <TouchableOpacity style={{marginTop: 20, marginHorizontal: 40, flexDirection: "row", justifyContent: "space-between", alignItems: "center"}}
                          onPress={() => {navigation.navigate("SpeciesProfile", {plantId: -1, specieID: speciesID, anonymous: anonymous})}}
        >
            <Card.Cover source={ {uri: image}} style={{width: image_width, height: image_height, borderWidth: 1, borderColor: theme.colors.opaqueGrey}} />
            <View style={{flexDirection: "column"}}>
                <Text variant={"bodyLarge"} style={{fontWeight: "800", marginBottom: 10, marginTop: 20, color: theme.colors.primary, width: 120}}>{name}</Text>
                <Text variant={"bodyMedium"} style={{color: theme.colors.primary}}>Difficulty</Text>
                <View style={{flexDirection: "row", alignItems: "center"}}>
                    {stars}
                </View>
            </View>

            <TouchableOpacity onPress={() => {
                if (anonymous) {
                    navigation.navigate("Login");
                }
                else {
                    navigation.navigate("AddPlant", {speciesID: speciesID, scientificName: scientificName});
                }
            }}>

            <View
                style={{
                    borderRadius: 50,
                    backgroundColor: theme.colors.primary,
                    width: 50,
                    height: 50,
                    justifyContent: 'center',
                    alignItems: 'center',
                    elevation: 5
                }}
            >
                <IconButton icon="plus" iconColor={theme.colors.background} size={24}/>
            </View>
            </TouchableOpacity>
        </TouchableOpacity>
    )
}