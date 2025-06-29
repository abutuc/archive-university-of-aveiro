import { View, ScrollView, TouchableOpacity } from "react-native";
import { IconButton, TextInput, useTheme, Text } from "react-native-paper";
import React, {useState} from "react";
import {getSpeciesFromQuery} from "../../../service/AddPlantScreenService";

export default function SearchBarSpecies({inputValue, setInputValue, setSpecieId}) {
    const theme = useTheme();
    const [isDropdownVisible, setIsDropDownVisible] = useState(false);
    const [species, setSpecies] = useState([]);

    const onChangeText = (text) => {
        if (text.length > 2){
            setIsDropDownVisible(true);
            setInputValue(text);
            getSpeciesFromQuery(text).then((species) => {setSpecies(species)});
            setSpecieId("");

        }

        else if (text.length === 0) {
            setInputValue("")
            setSpecieId("");
            setIsDropDownVisible(false);
        }

        else {
            setSpecieId("");
            setInputValue(text);
        }
    }

    const onSelectOption = (commonName, scientificName, id) => {
        if (commonName.includes(inputValue)) {
            setInputValue(commonName);
        }
        else {
            setInputValue(scientificName);
        }
        setSpecieId(id);
        setIsDropDownVisible(false);
    }

    return (
        <View>
            <View style={{ marginTop: 5, marginHorizontal: 35, marginBottom: 5, flexDirection: 'row' }}>
                <View style={{ borderRadius: 50 }}>
                    <IconButton icon={"magnify"} iconColor={theme.colors.primary} size={25} />
                </View>
                <TextInput style={{ width: 260, backgroundColor: theme.colors.background }}
                    underlineColor={theme.colors.primaryContainer}
                    activeUnderlineColor={theme.colors.primary}
                    textColor={theme.colors.onBackground}
                    placeholder={"What is your plant's species?"} placeholderTextColor={theme.colors.secondary}
                    onChangeText={text => onChangeText(text)} value={inputValue}
                />
            </View>
            {isDropdownVisible && (
                <ScrollView style={{
                    marginLeft: 85, width: 250,
                    backgroundColor: theme.colors.background,
                    borderRadius: 10,
                    borderWidth: 1,
                    borderColor: theme.colors.opaqueGrey,
                    maxHeight: 200,
                }} contentContainerStyle={{justifyContent: "center",
                    alignItems: "center"}} >

                    {species.map((item, index) => (
                        <TouchableOpacity onPress={() => onSelectOption(item.commonName, item.scientificName, item.id)} key={index} style={{
                            height: 30,
                            borderWidth: 1,
                            width: 200,
                            borderRadius: 10,
                            borderColor: theme.colors.primary,
                            margin: 5
                        }}>
                            {item.commonName.includes(inputValue) ?
                                <Text style={{textAlign: "center"}}>{item.commonName}</Text>
                                :
                                <Text style={{textAlign: "center"}}>{item.scientificName}</Text>
                            }
                        </TouchableOpacity>

                    ))}

                </ScrollView>
            ) }
        </View>
    )
}