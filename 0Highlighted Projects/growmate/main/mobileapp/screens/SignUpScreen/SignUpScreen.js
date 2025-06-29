import {Dimensions, View} from "react-native";
import React, {useState} from "react";
import {useTheme} from "react-native-paper";
import SignUpInfo from "./components/SingUpInfo";
import {useNavigation} from "@react-navigation/native";
import SignUpNextButton from "./components/SignUpNextButton";
import LogInText from "./components/LogInText";
import LoginSignUpHeader from "../../components/LoginSignUpHeader";


export default function SignUpScreen(){
    const screenHeight = Dimensions.get('screen').height;
    const theme = useTheme()
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigation = useNavigation();
    const [emailIsValid, setEmailIsValid] = useState(true);
    const [passwordIsValid, setPasswordIsValid] = useState(true);
    const [reEnterPasswordIsValid, setReEnterPasswordIsValid] = useState(true);
    const next = () => {
        navigation.navigate("SignUpTwo", {password: password, email: email});
    }
    return (
        <View style={{ height: screenHeight, backgroundColor: theme.colors.background }}>
            <LoginSignUpHeader title={"Sign Up On Our App!"} subtitle={"Become one our many plant enthusiasts!"}/>
            <SignUpInfo setPassword={setPassword} setEmail={setEmail} password={password}
                        setEmailIsValid={setEmailIsValid} setPasswordIsValid={setPasswordIsValid}
                        setReEnterPasswordIsValid={setReEnterPasswordIsValid} emailIsValid={emailIsValid}
                        passwordIsValid={passwordIsValid} reEnterPasswordIsValid={reEnterPasswordIsValid}
            />
            <SignUpNextButton next={next} disabled={!(emailIsValid && passwordIsValid && reEnterPasswordIsValid) || email==="" || password===""}/>
            <LogInText/>
        </View>
    )
}