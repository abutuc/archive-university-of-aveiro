import {TouchableOpacity, View} from "react-native";
import {Text, TextInput, useTheme} from "react-native-paper";
import React, {useState} from "react";


export default function ChangePassword({setOldPasswordIsValid, setOldPassword, oldPasswordIsValid,
                                        setPasswordIsValid, setPassword, passwordIsValid,
                                        confirmNewPasswordIsValid, setConfirmNewPasswordIsValid,
                                        newPassword, oldPassword, onPress, incorrectOldPassword,
                                        confirmNewPassword, setConfirmNewPassword}){
    const theme = useTheme();
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
    const [hideOldPassword, setHideOldPassword] = useState(true);
    const [hideNewPassword, setHideNewPassword] = useState(true);
    const [hideConfirmNewPassword, setHideConfirmNewPassword] = useState(true);



    const oldPasswordValidate = (password) => {
        if (password.length > 0){
            setOldPasswordIsValid(true);
        }
        else {
            setOldPasswordIsValid(false);
        }
        setOldPassword(password);
    }

    const passwordValidate = (password) => {
        if (passwordRegex.test(password)){
            setPasswordIsValid(true);
        }
        else {
            setPasswordIsValid(false);
        }
        setPassword(password);
    }

    const confirmNewPasswordValidate = (reEnterPassword) => {
        if (reEnterPassword === newPassword && reEnterPassword!==""){
            setConfirmNewPasswordIsValid(true);
        }
        else {
            setConfirmNewPasswordIsValid(false);
        }
        setConfirmNewPassword(reEnterPassword);
    }
    return (
        <View style={{justifyContent: "center", alignItems: "center", marginTop: 20}}>
            <View>
                <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14, marginBottom: 10}}
                           underlineColor={oldPasswordIsValid ? theme.colors.primaryContainer : theme.colors.error}
                           activeUnderlineColor={oldPasswordIsValid ? theme.colors.primary : theme.colors.error}
                           textColor={theme.colors.onBackground}
                           placeholder={"Old Password"} placeholderTextColor={theme.colors.tertiary}
                           secureTextEntry={hideOldPassword}
                           right={<TextInput.Icon icon={hideOldPassword ? "eye" : "eye-off"} onPress={() => {setHideOldPassword(!hideOldPassword)}} forceTextInputFocus={false}/>}
                           onChangeText={text => oldPasswordValidate(text)}
                           value={oldPassword}

                />
                {!oldPasswordIsValid &&
                    <Text variant={"bodySmall"} style={{color: theme.colors.error}}>
                        Old password should not be empty.
                    </Text>
                }
            </View>
            <View>
                <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14, marginBottom: 10}}
                           underlineColor={passwordIsValid? theme.colors.primaryContainer : theme.colors.error}
                           activeUnderlineColor={passwordIsValid ? theme.colors.primary : theme.colors.error}
                           textColor={theme.colors.onBackground}
                           placeholder={"New Password"} placeholderTextColor={theme.colors.tertiary}
                           secureTextEntry={hideNewPassword}
                           right={<TextInput.Icon icon={hideNewPassword ? "eye" : "eye-off"} onPress={() => {setHideNewPassword(!hideNewPassword)}} forceTextInputFocus={false}/>}
                           onChangeText={text => passwordValidate(text)}
                           value={newPassword}

                />
                {!passwordIsValid &&
                    <Text variant={"bodySmall"} style={{color: theme.colors.error, width: 250}}>
                        Password is invalid (should be at least 8 characters long, 1 uppercase, 1 lowercase and 1 digit.
                    </Text>
                }
            </View>
            <View>
                <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14, marginBottom: 10}}
                           underlineColor={confirmNewPasswordIsValid? theme.colors.primaryContainer : theme.colors.error}
                           activeUnderlineColor={confirmNewPasswordIsValid ? theme.colors.primary : theme.colors.error}
                           textColor={theme.colors.onBackground}
                           placeholder={"Confirm New Password"} placeholderTextColor={theme.colors.tertiary}
                           secureTextEntry={hideConfirmNewPassword}
                           right={<TextInput.Icon icon={hideConfirmNewPassword ? "eye" : "eye-off"} onPress={() => {setHideConfirmNewPassword(!hideConfirmNewPassword)}} forceTextInputFocus={false}/>}
                           onChangeText={text => confirmNewPasswordValidate(text)}
                           value={confirmNewPassword}

                />
                {!passwordIsValid &&
                    <Text variant={"bodySmall"} style={{color: theme.colors.error}}>
                        Passwords don't match.
                    </Text>
                }
            </View>
            <View style={{width: "100%", justifyContent: "center", alignItems: "center", marginTop: 10}}>
                {incorrectOldPassword &&
                    <Text variant={"bodyMedium"} style={{color: theme.colors.error, marginBottom: 20}}>
                        Old password is incorrect.
                    </Text>
                }
                <TouchableOpacity style={{backgroundColor: (!(oldPasswordIsValid && passwordIsValid && confirmNewPasswordIsValid) || oldPassword==="" || newPassword==="" || confirmNewPassword==="") ? theme.colors.outline : theme.colors.primary,
                    borderRadius: 25, width: 160, height: 30,
                    justifyContent: "center", alignItems: "center"}} onPress={() => {onPress(oldPassword, newPassword)}}
                                  disabled={(!(oldPasswordIsValid && passwordIsValid && confirmNewPasswordIsValid) || oldPassword==="" || newPassword==="" || confirmNewPassword==="")}>
                    <Text variant={"bodyLarge"} style={{color: theme.colors.background, textAlign: "center"}}
                    >Change Password</Text>
                </TouchableOpacity>
            </View>
        </View>
    )
}