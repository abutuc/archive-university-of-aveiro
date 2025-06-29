import React, {useEffect, useState} from "react";
import { View, Dimensions } from "react-native";
import BottomMenu from "../../components/BottomMenu";
import {useTheme} from "react-native-paper";
import AddPlantHeader from "../AddPlantScreen/components/AddPlantHeader";
import NextButton from "../AddPlantScreen/components/NextButton";
import AssociateDivision from "./components/AssociateDivision";
import AddDivisionButton from "./components/AddDivisionButton";
import {createNewPlant, getDivisions, getPlantSensors} from "../../service/AssociatePlantScreenService";
import {useFocusEffect, useNavigation} from "@react-navigation/native";
import { uploadImage } from "../../service/FirebaseService";
import {userID, userType} from "../../user";


export default function AssociatePlantScreen({route}) {
    const {image, date, specie, name} = route.params;
    const navigation = useNavigation();
    const screenHeight = Dimensions.get('screen').height;
    const theme = useTheme()

    const [sensors, setSensors] = useState({});
    const [divisions, setDivisions] = useState([]);

    const [chosenSensor, setChosenSensor] = useState(null);

    const [showDivisionDropDown, setShowDivisionDropDown] = useState(false);
    const [chosenDivision, setChosenDivision] = useState(null);

    const [loadAgain, setLoadAgain] = useState(0);


    useEffect(() => {
        if (userType === "PREMIUM") {
            getPlantSensors(userID).then((response) => {
                response["None"] = [{id: null, sensorCode: "None"}];
                setSensors(response);
            })
        }

        getDivisions(userID).then((response) => {
            response.push({id: null, name: "None"});
            setDivisions(response);
        })

    }, [loadAgain])

    useFocusEffect(
        React.useCallback(() => {
            setLoadAgain((prevLoadAgain) =>  prevLoadAgain + 1);
        }, [])
    );

    const onPressNext = async () => {
        const imageURL = await uploadImage(image, userID, name);

        createNewPlant(userID, name, imageURL, specie, chosenDivision, chosenSensor ,date).then(() => {
            setChosenSensor(null);
            setChosenSensor(null);
            navigation.navigate("Home", {reload: true, variance: name+imageURL+specie+chosenDivision+date});
        });
    }


    // API call that onPress it adds a Plant;
    return (
        <View style={{ height: screenHeight, backgroundColor: theme.colors.background }}>
            <AddPlantHeader text={"Just a few more steps!"} division={"back"}/>
            <AssociateDivision divisions={divisions} divisionsProps={[showDivisionDropDown, setShowDivisionDropDown, chosenDivision, setChosenDivision ]} sensors={sensors}/>
            <AddDivisionButton/>
            <NextButton text={"CREATE"} reverse={true} page={"Home"} onPress={onPressNext}/>
            <BottomMenu screenHeight={screenHeight} active={"leaf"} />
        </View>
    )
}