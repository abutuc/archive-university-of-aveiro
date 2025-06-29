import { useEffect } from "react";
import { Dimensions, View } from "react-native";
import { useTheme } from "react-native-paper";
import GreenBar from "../../components/GreenBar";
import React from "react";
import Logo from "./components/Logo";
import SignUp from "./components/SignUp";
import LogIn from "./components/LogIn";
import NoAccount from "./components/NoAccount";
import { useNavigation } from "@react-navigation/native";
import { getItem } from "../../cache";

import {
    setUserAddress,
    setUserDateOfBirth,
    setUserDeadPlants,
    setUserEmail,
    setUserFirstName,
    setUserID,
    setUserName,
    setUserPhoto, setUserRating,
    setUserType
} from "../../user";

export default function InitializerScreen() {
    const screenHeight = Dimensions.get('screen').height;
    const theme = useTheme()
    const navigation = useNavigation();

    // If cache has user data, then go to HomeScreen
    // Check if there is cached user data and go to HomeScreen if so
    useEffect(() => {
        const cacheKey = "login";

        const loadCachedUserData = async () => {
            const cacheData = await getItem(cacheKey);

            if (cacheData) {
                console.log("Cached user data found.");

                const {
                    id,
                    name,
                    userType,
                    profilePhoto,
                    email,
                    dead_plants,
                    dateOfBirth,
                    rating,
                    address,
                } = cacheData;

                setUserID(id);
                setUserFirstName(name.split(" ")[0]);
                setUserType(userType);
                setUserPhoto(profilePhoto);
                setUserName(name);
                setUserEmail(email);
                setUserDeadPlants(dead_plants);
                setUserDateOfBirth(dateOfBirth);
                setUserRating(rating);
                setUserAddress(address);

                navigation.navigate("Home");
            } else {
                console.log("No cached user data found.");
            }
        };

        loadCachedUserData();
    }, []);


    return (
        <View style={{ height: screenHeight, backgroundColor: theme.colors.background }}>
            <Logo />
            <LogIn />
            <SignUp />
            <NoAccount />
        </View>
    )
}