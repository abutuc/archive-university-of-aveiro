import {Dimensions, View} from "react-native";
import {useNavigation} from "@react-navigation/native";
import {useTheme} from "react-native-paper";
import React, {useEffect, useState} from "react";
import AddPlantHeader from "../AddPlantScreen/components/AddPlantHeader";
import BottomMenu from "../../components/BottomMenu";
import NextButton from "../AddPlantScreen/components/NextButton";
import {createNewSensor, getDivisions} from "../../service/AddSensorService";
import AddSensorDescription from "./components/AddSensorDescription";
import {getPlants} from "../../service/HomeScreenService";
import SensorInfo from "./components/SensorInfo";
import {userID} from "../../user";


export default function AddSensorScreen(){
    const screenHeight = Dimensions.get('screen').height;
    const navigation = useNavigation();
    const theme = useTheme()
    const [sensorName, setSensorName] = useState("");
    const [sensorCode, setSensorCode] = useState("");
    const [sensorType, setSensorType] = useState(0);
    const [ownerID, setOwnerID] = useState(null);
    const [userPlants, setUserPlants] = useState([]);
    const [userDivisions, setUserDivisions] = useState([]);
    const [codeIsValid, setCodeIsValid] = useState(true);

    useEffect( () => {
        getPlants(userID).then((plants) => {setUserPlants(plants)})
    }, [])

    useEffect( () => {
        getDivisions(userID).then((divisions) => {setUserDivisions(divisions)})
    }, [])

    const onPressNext = () => {
        createNewSensor(userID, sensorType, sensorName, sensorCode, ownerID).then(() =>
            navigation.navigate("Home", {reload: true, variance: "sensor_" + sensorName+sensorCode})
        );
    }

    return (
        <View style={{ height: screenHeight, backgroundColor: theme.colors.background }}>
            <AddPlantHeader text={"Add a Sensor to your home!"}/>
            <AddSensorDescription/>
            <SensorInfo sensorType={sensorType} plants={userPlants} divisions={userDivisions}
                        setSensorType={setSensorType} ownerID={ownerID} setOwnerID={setOwnerID}
                        setSensorCode={setSensorCode} setSensorName={setSensorName}
                        codeIsValid={codeIsValid} setCodeIsValid={setCodeIsValid}/>
            <NextButton text={"CREATE"} reverse={true} onPress={onPressNext} disabled={!(sensorCode.length > 0 && codeIsValid && sensorType !== null && ownerID !== null)}/>
            <BottomMenu screenHeight={screenHeight} active={"leaf"} />
        </View>
    )
}