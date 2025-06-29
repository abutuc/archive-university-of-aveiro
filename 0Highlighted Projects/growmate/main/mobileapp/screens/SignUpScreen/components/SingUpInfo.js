import {Text, TextInput, useTheme} from "react-native-paper";
import {View} from "react-native";
import React, {useState} from "react";


export default function SignUpInfo({setEmail, setPassword, password, emailIsValid, setEmailIsValid,
                                       passwordIsValid, setPasswordIsValid,
                                       reEnterPasswordIsValid, setReEnterPasswordIsValid}){
    const theme = useTheme();
    const [hidePassword, setHidePassword] = useState(true);
    const [hideReEnterPassword, setHideReEnterPassword] = useState(true);
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;



    const emailValidate = (email) => {
        if (emailRegex.test(email)){
            setEmailIsValid(true);
            setEmail(email);
        }
        else {
            setEmailIsValid(false);
        }
    }

    const passwordValidate = (password) => {
        if (passwordRegex.test(password)){
            setPasswordIsValid(true);
            setPassword(password);
        }
        else {
            setPasswordIsValid(false);
        }
    }

    const reEnterPasswordValidate = (reEnterPassword) => {
        if (reEnterPassword === password && reEnterPassword!==""){
            setReEnterPasswordIsValid(true);
        }
        else {
            setReEnterPasswordIsValid(false);
        }
    }


    return (
        <View style={{marginLeft: 70, marginTop: 70}}>
            <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14, marginBottom: 10}}
                       underlineColor={emailIsValid ? theme.colors.primaryContainer : theme.colors.error}
                       activeUnderlineColor={emailIsValid ? theme.colors.primary : theme.colors.error}
                       textColor={theme.colors.onBackground}
                       placeholder={"E-mail"} placeholderTextColor={theme.colors.tertiary}
                       onChangeText={text => emailValidate(text)}
            />
            {!emailIsValid &&
                <Text variant={"bodySmall"} style={{color: theme.colors.error}}>
                    Email is invalid.
                </Text>
            }
            <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14, marginBottom: 10}}
                       underlineColor={passwordIsValid ? theme.colors.primaryContainer : theme.colors.error}
                       activeUnderlineColor={passwordIsValid ? theme.colors.primary : theme.colors.error}
                       textColor={theme.colors.onBackground}
                       placeholder={"Password"} placeholderTextColor={theme.colors.tertiary}
                       secureTextEntry={hidePassword}
                       right={<TextInput.Icon icon={hidePassword ? "eye" : "eye-off"} onPress={() => {setHidePassword(!hidePassword)}} forceTextInputFocus={false}/>}
                       onChangeText={text => passwordValidate(text)}

            />
            {!passwordIsValid &&
                <Text variant={"bodySmall"} style={{color: theme.colors.error}}>
                    Password is invalid (should be at least 8 characters long, 1 uppercase, 1 lowercase and 1 digit.
                </Text>
            }

            <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14, marginBottom: 10}}
                       underlineColor={(reEnterPasswordIsValid)  ? theme.colors.primaryContainer : theme.colors.error}
                       activeUnderlineColor={(reEnterPasswordIsValid) ? theme.colors.primary : theme.colors.error}
                       textColor={theme.colors.onBackground}
                       placeholder={"Re-enter Password"} placeholderTextColor={theme.colors.tertiary}
                       secureTextEntry={hideReEnterPassword}
                       right={<TextInput.Icon icon={hideReEnterPassword ? "eye" : "eye-off"} onPress={() => {setHideReEnterPassword(!hideReEnterPassword)}} forceTextInputFocus={false}/>}
                       onChangeText={text => reEnterPasswordValidate(text)}

            />
            {!(reEnterPasswordIsValid) &&
                <Text variant={"bodySmall"} style={{color: theme.colors.error}}>
                    Passwords don't match.
                </Text>
            }
        </View>
    )
}