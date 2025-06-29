import React, {useEffect, useState} from "react";
import { View, Dimensions } from "react-native";
import BottomMenu from "../../components/BottomMenu";
import AddPlantHeader from "./components/AddPlantHeader";
import { useTheme } from "react-native-paper";
import SearchBarSpecies from "./components/SearchBarSpecies";
import AddPhoto from "./components/AddPhoto";
import PlantName from "./components/PlantName";
import NextButton from "./components/NextButton";
import AddPlantationDate from "./components/AddPlantationDate";
import { useNavigation } from "@react-navigation/native";


export default function AddPlantScreen({route}) {
    const screenHeight = Dimensions.get('screen').height;
    const {speciesID, scientificName} = route.params;
    const navigation = useNavigation();
    const theme = useTheme()
    const [image, setImage] = useState(null);
    const [date, setDate] = useState("")
    const [validDate, setValidDate] = useState(false);
    const [specie, setSpecie] = useState(scientificName ? scientificName : "");
    const [specieId, setSpecieId] = useState(speciesID ? speciesID : "");
    const [name, setName] = useState("");
    const [goBack, setGoBack] = useState(false);

    useEffect(() => {
        if (speciesID !== null) {
            setSpecieId(speciesID);
            setGoBack(true);
        }
        else {
            setSpecieId("");
        }

        if (scientificName !== null) {
            setSpecie(scientificName);
        }
        else {
            setSpecie("");
        }

        setImage(null);
        setDate("");
        setValidDate(false);
        setName("");

    }, [])
    const onPressNext = () => {
        navigation.navigate("AssociatePlant", {
            image: image,
            date: date,
            specie: specieId,
            name: name
        });
    }

    return (
        <View style={{ height: screenHeight, backgroundColor: theme.colors.background }}>
            <AddPlantHeader text={"Let us know the plant's details"} division={goBack}/>
            <SearchBarSpecies inputValue={specie} setInputValue={setSpecie} setSpecieId={setSpecieId} />
            <AddPhoto image={image} setImage={setImage} plant={true}/>
            <AddPlantationDate inputDate={date} setInputDate={setDate} setValidDate={setValidDate}/>
            <PlantName isImage={image} setName={setName} />
            <NextButton text={"Next"} page={"AssociatePlant"} reverse={false} onPress={onPressNext}
                        disabled={!(specieId !== "" && image !== null && validDate && name.length > 0)} />
            <BottomMenu screenHeight={screenHeight} active={"leaf"} />
        </View>
    )
}