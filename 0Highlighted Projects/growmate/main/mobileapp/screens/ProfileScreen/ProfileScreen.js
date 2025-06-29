import {Dimensions, View} from "react-native";
import {ActivityIndicator, useTheme} from "react-native-paper";
import React, {useEffect, useState} from "react";
import BottomMenu from "../../components/BottomMenu";
import UserHeader from "./components/UserHeader";
import ThinDivider from "../../components/ThinDivider";
import ProfileButtons from "./components/ProfileButtons";
import ProfileDetails from "./components/ProfileDetails";
import TaskLog from "./components/TaskLog";
import {changePassword, editProfile, getTasksLog, getUserStats} from "../../service/ProfileScreenService";
import {
    setUserAddress,
    setUserDateOfBirth, setUserDeadPlants,
    setUserEmail, setUserExperience, setUserFirstName, setUserID,
    setUserName, setUserPhoto, setUserRating, setUserType,
    userAddress,
    userDateOfBirth,
    userEmail,
    userID,
    userName
} from "../../user";
import {CommonActions, useNavigation} from "@react-navigation/native";
import ChangePassword from "./components/ChangePassword";

import { clear } from "../../cache";


export default function ProfileScreen(){
    const screenHeight = Dimensions.get('screen').height;
    const theme = useTheme();
    const navigation = useNavigation();
    const [userStats, setUserStats] = useState({});
    const [tasksLog, setTasksLog] = useState([]);
    const [loadingStats, setLoadingStats] = useState(true);
    const [loadingLog, setLoadingLog] = useState(true);

    useEffect(() => {
        getUserStats(userID).then((stats) => setUserStats(stats)).then(() => setLoadingStats(false));
        getTasksLog(userID).then((log) => setTasksLog(log)).then(() => setLoadingLog(false));
    }, [])

    const [selected, setSelected] = useState("Details");

    const [nameIsValid, setNameIsValid] = useState(true);
    const [emailIsValid, setEmailIsValid] = useState(true);
    const [dateOfBirthIsValid, setDateOfBirthIsValid] = useState(true);
    const [addressIsValid, setAddressIsValid] = useState(true);
    const [initialName, setInitialName] = useState(userName);
    const [initialEmail, setInitialEmail] = useState(userEmail);
    const [initialDateOfBirth, setInitialDateOfBirth] = useState(userDateOfBirth);
    const [initialAddress, setInitialAddress] = useState(userAddress);
    const [name, setName] = useState(userName);

    const editProfileOnPress = (userName, userEmail, userDateOfBirth, userAddress) => {
        setUserName(userName);
        setUserFirstName(userName.split(" ")[0]);
        setUserEmail(userEmail);
        setUserDateOfBirth(userDateOfBirth);
        setUserAddress(userAddress);
        setName(userName);
        editProfile(userID, userName, userEmail, userDateOfBirth, userAddress);
    }

    const logout = () => {
        setUserID(0)
        setUserName("");
        setUserFirstName("");
        setUserEmail("");
        setUserDateOfBirth("");
        setUserAddress("");
        setUserRating(0);
        setUserDeadPlants(0);
        setUserPhoto("");
        setUserType("");
        setUserExperience(0);
        clear();
        navigation.dispatch(CommonActions.reset({
            index: 0,
            routes: [{ name: "Initializer" }],
        }));
    }

    const [oldPasswordIsValid, setOldPasswordIsValid] = useState(true);
    const [newPasswordIsValid, setNewPasswordIsValid] = useState(true);
    const [confirmNewPasswordIsValid, setConfirmNewPasswordIsValid] = useState(true);
    const [oldPassword, setOldPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [confirmNewPassword, setConfirmNewPassword] = useState("");
    const [incorrectOldPassword, setIncorrectOldPassword] = useState(false);

    const onPressChangePassword = (oldPassword, newPassword) => {
        changePassword(userID, oldPassword, newPassword).then((response) =>
        {
            if (response === false){
                setIncorrectOldPassword(true);
            }
            else {
                setIncorrectOldPassword(false);
                setOldPassword("");
                setNewPassword("");
                setConfirmNewPassword("");
                setConfirmNewPasswordIsValid(true);
                setOldPasswordIsValid(true);
                setNewPasswordIsValid(true);
            }
        })
    }

    if (loadingStats || loadingLog){
        return (
            <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
                <ActivityIndicator size="large" color={theme.colors.primary}/>
            </View>
        )
    }
    else {
        return (
            <View style={{height: screenHeight, backgroundColor: theme.colors.background}}>
                <UserHeader stats={userStats} name={name} logout={logout}/>
                <ThinDivider/>
                <ProfileButtons selected={selected} setSelected={setSelected}/>
                {selected === "Details" &&
                    <ProfileDetails setNameIsValid={setNameIsValid} nameIsValid={nameIsValid}
                                    setAddressIsValid={setAddressIsValid} setDateOfBirthIsValid={setDateOfBirthIsValid}
                                    setEmailIsValid={setEmailIsValid} dateOfBirthIsValid={dateOfBirthIsValid}
                                    addressIsValid={addressIsValid} emailIsValid={emailIsValid}
                                    setUserName={setInitialName} setUserAddress={setInitialAddress}
                                    setUserEmail={setInitialEmail}
                                    setUserDateOfBirth={setInitialDateOfBirth} userAddress={initialAddress}
                                    userDateOfBirth={initialDateOfBirth}
                                    userName={initialName} userEmail={initialEmail} onSavePress={editProfileOnPress}/>
                }
                {selected === "Tasks Log" &&
                    <TaskLog log={tasksLog}/>
                }
                {selected === "Password" &&
                    <ChangePassword passwordIsValid={newPasswordIsValid} setPasswordIsValid={setNewPasswordIsValid}
                                    oldPasswordIsValid={oldPasswordIsValid}
                                    setOldPasswordIsValid={setOldPasswordIsValid}
                                    setPassword={setNewPassword} confirmNewPasswordIsValid={confirmNewPasswordIsValid}
                                    newPassword={newPassword}
                                    setConfirmNewPasswordIsValid={setConfirmNewPasswordIsValid}
                                    setOldPassword={setOldPassword} oldPassword={oldPassword}
                                    onPress={onPressChangePassword} incorrectOldPassword={incorrectOldPassword}
                                    setConfirmNewPassword={setConfirmNewPassword} confirmNewPassword={confirmNewPassword}
                    />
                }
                <BottomMenu screenHeight={screenHeight} active={"account"}/>
            </View>
        )
    }
}