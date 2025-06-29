import React, {useEffect, useRef, useState} from "react";
import {View, Dimensions, TouchableOpacity} from "react-native";
import BottomMenu from "../../components/BottomMenu";
import SearchBar from "../../components/SearchBar";
import WelcomeHeader from "./components/WelcomeHeader"
import PlantCards from "./components/PlantCards";
import {ActivityIndicator, IconButton, Text, useTheme} from "react-native-paper";
import PlusButton from "./components/PlusButton";
import { Tabs, TabScreen } from 'react-native-paper-tabs';
import Divisions from "./components/Divisions";
import {userFirstName, userID, userType} from "../../user";
import SensorsTab from "./components/SensorsTab";

// API Calls
import { getPlants, getDivisionsAndAssociatedPlants, getSensors } from "../../service/HomeScreenService";
import {useNavigation} from "@react-navigation/native";

export default function HomeScreen({route}) {
    const {reload, variance} = route.params || {reload: false, variance: ""};
    const [userPlants, setUserPlants] = useState([]);
    const [queriedUserPlants, setQueriedUserPlants] = useState([]);
    const [userDivisions, setUserDivisions] = useState([]);
    const [updateCount, setUpdateCount] = useState(0);
    const [sensors, setSensors] = useState(null);
    const navigation = useNavigation();

    useEffect(() => {
        if (reload) {
            setUpdateCount(updateCount + 1);
        }
        if (variance.split("_")[0] === "division" ){
            setSelectedTab(1);
        }
        else if (variance.split("_")[0] === "sensor" ){
            setSelectedTab(2);
        }
    }, [variance])

    const handleUpdate = () => {
        setUpdateCount(updateCount + 1);
    }

    const [loadingPlants, setLoadingPlants] = useState(true);
    useEffect( () => {
        getPlants(userID).then((plants) => {
            setUserPlants(plants);
            setQueriedUserPlants(plants);
            setLoadingPlants(false);
        })
    }, [updateCount]);

    const [loadingDivisions, setLoadingDivisions] = useState(true);
    useEffect( () => {
        getDivisionsAndAssociatedPlants(userID).then(
            (divisions) => {
                setUserDivisions(divisions)
                setLoadingDivisions(false);
            }
        );
    }, [updateCount]);

    const [loadingSensors, setLoadingSensors] = useState(true);
    useEffect(() => {
        getSensors(userID).then(
            (sensors) => {
                setSensors(sensors)
                setLoadingSensors(false);
            }
        )
    }, [updateCount])

    const screenHeight = Dimensions.get('screen').height;
    const theme = useTheme()
    const [selectedTab, setSelectedTab] = useState(0)

    const filterPlants = (query) => {
        const queriedPlants = userPlants.filter((plant) => {
            return plant.name.toLowerCase().includes(query.toLowerCase()) || plant.species.commonName.toLowerCase().includes(query.toLowerCase())
        })
        setQueriedUserPlants(queriedPlants)
    }

    const scrollViewRef = useRef(null);
    const [scrollOffset, setScrollOffset] = useState(0);

    const handleScroll = (event) => {
        const offsetY = event.nativeEvent.contentOffset.y;
        setScrollOffset(offsetY);
    };
    const handleScrollToBottom = () => {
        const newOffset = scrollOffset + 100;
        // Access the ScrollView component and scroll to the bottom
        scrollViewRef.current?.scrollTo({ y: newOffset, animated: true });
        setScrollOffset(newOffset);
    };

    if (loadingPlants || loadingDivisions || loadingSensors) {
        return (
            <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
                <ActivityIndicator size="large" />
            </View>
        );
    }
    else {
        return (
            <View style={{height: screenHeight, backgroundColor: theme.colors.background}}>
                <View style={{position: 'relative', zIndex: 1}}>
                    <WelcomeHeader premium={userType === "PREMIUM"} name={userFirstName}/>
                </View>
                {userType === "PREMIUM" ?
                    <Tabs
                        defaultIndex={0} // default = 0
                        style={{backgroundColor: '#fff'}} // works the same as AppBar in react-native-paper
                        disableSwipe={true} // (default=false) disable swipe to left/right gestures
                        onChangeIndex={(newIndex) => {
                            setSelectedTab(newIndex)
                        }}
                    >
                        <TabScreen label="Inventory">
                            <View>
                                {userPlants.length > 0 &&
                                    <SearchBar filterPlants={filterPlants}/>
                                }
                                {userPlants.length === 0 &&
                                    <TouchableOpacity
                                        style={{width: "100%", justifyContent: "center", alignItems: "center"}}
                                        onPress={() => navigation.navigate("AddPlant", {
                                            speciesID: null,
                                            scientificName: null
                                        })}
                                    >
                                        <View style={{flexDirection: "column"}}>
                                            <Text variant={"bodyMedium"} style={{
                                                textAlign: "center",
                                                marginTop: 200,
                                                width: 250,
                                                textAlignVertical: "center"
                                            }}>You have no plants in your inventory.</Text>
                                            <Text variant={"bodyMedium"} style={{
                                                textAlign: "center",
                                                marginTop: 5,
                                                width: 250,
                                                textAlignVertical: "center"
                                            }}>Add some plants to get started!</Text>
                                        </View>
                                    </TouchableOpacity>
                                }
                                <PlantCards plants={queriedUserPlants}/>
                            </View>
                        </TabScreen>
                        <TabScreen label="Divisions">
                            <View>
                                {userDivisions.length === 0 &&
                                    <TouchableOpacity
                                        style={{width: "100%", justifyContent: "center", alignItems: "center"}}
                                        onPress={() => navigation.navigate("AddDivision", {destiny: "Home"})}
                                    >
                                        <View style={{flexDirection: "column"}}>
                                            <Text variant={"bodyMedium"} style={{
                                                textAlign: "center",
                                                marginTop: 200,
                                                width: 250,
                                                textAlignVertical: "center"
                                            }}>You have no divisions.</Text>
                                            <Text variant={"bodyMedium"} style={{
                                                textAlign: "center",
                                                marginTop: 5,
                                                width: 250,
                                                textAlignVertical: "center"
                                            }}>Add some divisions to get started!</Text>
                                        </View>
                                    </TouchableOpacity>
                                }
                                <Divisions divisions={userDivisions} plants={userPlants} handleUpdate={handleUpdate}
                                           handleScroll={handleScroll} scrollViewRef={scrollViewRef}/>
                                {userDivisions.length > 2 &&
                                    <IconButton icon={"chevron-down"} iconColor={theme.colors.primary} size={35}
                                                style={{margin: 0, alignSelf: "center"}}
                                                onPress={handleScrollToBottom}
                                    />
                                }
                            </View>
                        </TabScreen>
                        <TabScreen
                            label="Sensors"
                        >
                            <View>
                                {sensors === null || sensors.length === 0 ?
                                    <TouchableOpacity
                                        style={{width: "100%", justifyContent: "center", alignItems: "center"}}
                                        onPress={() => navigation.navigate("AddSensor")}
                                    >
                                        <View style={{flexDirection: "column"}}>
                                            <Text variant={"bodyMedium"} style={{
                                                textAlign: "center",
                                                marginTop: 200,
                                                width: 250,
                                                textAlignVertical: "center"
                                            }}>You have no sensors.</Text>
                                            <Text variant={"bodyMedium"} style={{
                                                textAlign: "center",
                                                marginTop: 5,
                                                width: 250,
                                                textAlignVertical: "center"
                                            }}>Add some sensors to get started!</Text>
                                        </View>
                                    </TouchableOpacity>
                                    :
                                    <View>
                                        {userDivisions && userPlants && sensors && sensors.length !== 0 &&
                                            <SensorsTab userDivisions={userDivisions} sensors={sensors}
                                                        userPlants={userPlants} handleUpdate={handleUpdate}/>}
                                    </View>
                                }
                            </View>
                        </TabScreen>
                    </Tabs>
                    :
                    <Tabs
                        defaultIndex={0} // default = 0
                        style={{backgroundColor: '#fff'}} // works the same as AppBar in react-native-paper
                        disableSwipe={true} // (default=false) disable swipe to left/right gestures
                        onChangeIndex={(newIndex) => {
                            setSelectedTab(newIndex)
                        }}
                    >
                        <TabScreen label="Inventory">
                            <View>
                                {userPlants.length > 0 &&
                                    <SearchBar filterPlants={filterPlants}/>
                                }
                                {userPlants.length === 0 &&
                                    <TouchableOpacity
                                        style={{width: "100%", justifyContent: "center", alignItems: "center"}}
                                        onPress={() => navigation.navigate("AddPlant", {
                                            speciesID: null,
                                            scientificName: null
                                        })}
                                    >
                                        <View style={{flexDirection: "column"}}>
                                            <Text variant={"bodyMedium"} style={{
                                                textAlign: "center",
                                                marginTop: 200,
                                                width: 250,
                                                textAlignVertical: "center"
                                            }}>You have no plants in your inventory.</Text>
                                            <Text variant={"bodyMedium"} style={{
                                                textAlign: "center",
                                                marginTop: 5,
                                                width: 250,
                                                textAlignVertical: "center"
                                            }}>Add some plants to get started!</Text>
                                        </View>
                                    </TouchableOpacity>
                                }
                                <PlantCards plants={queriedUserPlants}/>
                            </View>
                        </TabScreen>
                        <TabScreen label="Divisions">
                            <View>
                                {userDivisions.length === 0 &&
                                    <TouchableOpacity
                                        style={{width: "100%", justifyContent: "center", alignItems: "center"}}
                                        onPress={() => navigation.navigate("AddDivision", {destiny: "Home"})}
                                    >
                                        <View style={{flexDirection: "column"}}>
                                            <Text variant={"bodyMedium"} style={{
                                                textAlign: "center",
                                                marginTop: 200,
                                                width: 250,
                                                textAlignVertical: "center"
                                            }}>You have no divisions.</Text>
                                            <Text variant={"bodyMedium"} style={{
                                                textAlign: "center",
                                                marginTop: 5,
                                                width: 250,
                                                textAlignVertical: "center"
                                            }}>Add some divisions to get started!</Text>
                                        </View>
                                    </TouchableOpacity>
                                }
                                <Divisions divisions={userDivisions} plants={userPlants} handleUpdate={handleUpdate}
                                           handleScroll={handleScroll} scrollViewRef={scrollViewRef}/>
                                {userDivisions.length > 2 &&
                                    <IconButton icon={"chevron-down"} iconColor={theme.colors.primary} size={35}
                                                style={{margin: 0, alignSelf: "center"}}
                                                onPress={handleScrollToBottom}
                                    />
                                }
                            </View>
                        </TabScreen>
                    </Tabs>
                }
                <PlusButton index={selectedTab}/>
                <BottomMenu screenHeight={screenHeight} active={"leaf"}/>
            </View>
        )
    }
}