import {Dimensions, View} from "react-native";
import {useTheme} from "react-native-paper";
import React, {useState} from "react";
import LoginInfo from "./components/LoginInfo";
import LoginButton from "./components/LoginButton";
import SignUpButton from "./components/SignUpButton";
import {login} from "../../service/LoginScreenService";
import {useNavigation} from "@react-navigation/native";
import WarningMessage from "./components/WarningMessage";
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
import LoginSignUpHeader from "../../components/LoginSignUpHeader";

export default function LoginScreen(){
    const screenHeight = Dimensions.get('screen').height;
    const theme = useTheme()
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigation = useNavigation();
    const [incorrectCredentials, setIncorrectCredentials] = useState(false);

    const pressLogin = () => {
        login(email, password).then((response) => {
            if (response !== false) {
                setIncorrectCredentials(false);
                setUserID(response.id);
                setUserFirstName(response.name.split(" ")[0]);
                setUserType(response.userType);
                setUserPhoto(response.profilePhoto);
                setUserName(response.name);
                setUserEmail(response.email);
                setUserDeadPlants(response.dead_plants);
                setUserDateOfBirth(response.dateOfBirth);
                setUserRating(response.rating);
                setUserAddress(response.address);
                navigation.navigate("Home", {reload: false, variance: email});
            }
            else {
                setIncorrectCredentials(true);
            }

        } )
    }
    return (
        <View style={{ height: screenHeight, backgroundColor: theme.colors.background }}>
            <LoginSignUpHeader title={"Login Into Our App!"} subtitle={"Don't waste more time...go check on your plants!"}/>
            <LoginInfo setPassword={setPassword} setEmail={setEmail}/>
            {incorrectCredentials &&
                <WarningMessage/>
            }
            <LoginButton login={pressLogin}/>
            <SignUpButton/>
        </View>
    )
}