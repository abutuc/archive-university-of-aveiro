import {ScrollView, View} from "react-native";
import {IconButton, Text, TextInput, useTheme} from "react-native-paper";
import React, {useState} from "react";
import {getSpeciesFromQuery} from "../../../service/AddPlantScreenService";
import PlantItem from "../../CategoryScreen/components/PlantItem";

export default function DiscoverNewPlantsSearchBar({anonymous, setHideCategories}) {
    const theme = useTheme();
    const [isDropdownVisible, setIsDropDownVisible] = useState(false);
    const [species, setSpecies] = useState([]);

    const onChangeText = (text) => {
        if (text.length > 2){
            getSpeciesFromQuery(text).then((species) => {
                setHideCategories(true);
                setIsDropDownVisible(true);
                setSpecies(species)});
        }
        else {
            setSpecies([])
            setIsDropDownVisible(false);
            setHideCategories(false);
        }
    }

    return (
        <View >
            <View style={{ marginTop: 20, marginHorizontal: 35, marginBottom: 10, flexDirection: "row"}}>
                <View style={{ borderRadius: 50 }}>
                    <IconButton icon={"magnify"} iconColor={theme.colors.primary} size={25} />
                </View>
                <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14 }}
                           underlineColor={theme.colors.primaryContainer}
                           activeUnderlineColor={theme.colors.primary}
                           textColor={theme.colors.onBackground}
                           placeholder={"Search a plant!"} placeholderTextColor={theme.colors.secondary}
                           onChangeText={text => onChangeText(text)}
                />
            </View>

            {isDropdownVisible && species.length !== 0 && (
                <ScrollView style={{
                    position: "relative", top: 5, left: 0,
                    marginLeft: 0,
                    width: "100%",
                    backgroundColor: theme.colors.background,
                    borderColor: theme.colors.opaqueGrey,
                    maxHeight: 500,
                }}>
                    {species.map((item, index) => (
                        <PlantItem key={index} name={item.commonName} image={item.speciesPhoto}
                                   difficulty={item.difficulty} anonymous={anonymous} speciesID={item.id}
                                      scientificName={item.scientificName}
                        />
                    ))}

                </ScrollView>
            ) }
            {isDropdownVisible && species.length === 0 &&
                <Text variant={"bodyMedium"} style={{marginTop: 50, textAlign: "center", color: theme.colors.secondary}}>No results found</Text>
            }
        </View>
    )
}