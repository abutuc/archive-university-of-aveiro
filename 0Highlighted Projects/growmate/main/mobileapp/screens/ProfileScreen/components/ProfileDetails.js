import {ScrollView, TouchableOpacity, View} from "react-native";
import {Text, TextInput, useTheme} from "react-native-paper";
import React, {useState} from "react";


export default function ProfileDetails({setNameIsValid, nameIsValid, setEmailIsValid, emailIsValid,
                                       setDateOfBirthIsValid, dateOfBirthIsValid, setAddressIsValid, addressIsValid,
                                           setUserName, setUserEmail, setUserDateOfBirth, setUserAddress, userName,
                                           userEmail, userDateOfBirth, userAddress, onSavePress}){
    const theme = useTheme();
    const dateRegex = /^(19|20)\d{2}-(0[1-9]|1[0-2])-(0[1-9]|1\d|2\d|3[01])$/;
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    const nameValidate = (name) => {
        if (name.length > 0){
            setNameIsValid(true);
        }
        else {
            setNameIsValid(false);
        }
        setUserName(name);
    }
    const emailValidate = (email) => {
        if (emailRegex.test(email)){
            setEmailIsValid(true);
        }
        else {
            setEmailIsValid(false);
        }
        setUserEmail(email);

    }
    const dateOfBirthValidate = (date) => {
        if (dateRegex.test(date)){
            setDateOfBirthIsValid(true);
            setUserDateOfBirth(date);
        }
        else {
            setDateOfBirthIsValid(false);
        }
        setUserDateOfBirth(date);
    }

    const addressValidate = (address) => {
        if (address.length > 0){
            setAddressIsValid(true);
        }
        else {
            setAddressIsValid(false);
        }
        setUserAddress(address);

    }

    const [edit, setEdit] = useState(false);

    return (
        <View>
        <View style={{marginTop: 20, marginHorizontal: 90}}>
            <ScrollView style={{maxHeight: 270}}>
            <View>
                <Text variant={"bodyLarge"} style={{color: theme.colors.primary}}>
                    Name
                </Text>
                <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14, marginBottom: 5}}
                           underlineColor={nameIsValid ? theme.colors.primaryContainer : theme.colors.error}
                           activeUnderlineColor={nameIsValid ? theme.colors.primary : theme.colors.error}
                           textColor={theme.colors.onBackground}
                           placeholder={"Name"} placeholderTextColor={theme.colors.tertiary}
                           value={userName}
                           onChangeText={text => nameValidate(text)} disabled={!edit}
                />
            </View>
            <View style={{marginTop: 10}}>
                <Text variant={"bodyLarge"} style={{color: theme.colors.primary}}>
                    Email
                </Text>
                <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14, marginBottom: 5}}
                           underlineColor={emailIsValid ? theme.colors.primaryContainer : theme.colors.error}
                           activeUnderlineColor={emailIsValid ? theme.colors.primary : theme.colors.error}
                           textColor={theme.colors.onBackground}
                           placeholder={"Email"} placeholderTextColor={theme.colors.tertiary}
                           value={userEmail}
                           onChangeText={text => emailValidate(text)} disabled={!edit}
                />
            </View>
            <View style={{marginTop: 10}}>
                <Text variant={"bodyLarge"} style={{color: theme.colors.primary}}>
                    Date of Birth
                </Text>
                <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14, marginBottom: 5}}
                           underlineColor={dateOfBirthIsValid ? theme.colors.primaryContainer : theme.colors.error}
                           activeUnderlineColor={dateOfBirthIsValid ? theme.colors.primary : theme.colors.error}
                           textColor={theme.colors.onBackground}
                           placeholder={"Date of Birth"} placeholderTextColor={theme.colors.tertiary}
                           value={userDateOfBirth}
                           onChangeText={text => dateOfBirthValidate(text)} disabled={!edit}
                />
            </View>
            <View style={{marginTop: 10}}>
                <Text variant={"bodyLarge"} style={{color: theme.colors.primary}}>
                    Address
                </Text>
                <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14, marginBottom: 5}}
                           underlineColor={addressIsValid ? theme.colors.primaryContainer : theme.colors.error}
                           activeUnderlineColor={addressIsValid ? theme.colors.primary : theme.colors.error}
                           textColor={theme.colors.onBackground}
                           placeholder={"Address"} placeholderTextColor={theme.colors.tertiary}
                           value={userAddress}
                           onChangeText={text => addressValidate(text)} disabled={!edit}
                />
            </View>
            </ScrollView>
            </View>
            <View  style={{width: "100%", justifyContent: "center", alignItems: "center", marginTop: 10}}>
                {edit ?
                    <TouchableOpacity style={{backgroundColor: !(nameIsValid && emailIsValid && dateOfBirthIsValid && addressIsValid) ? theme.colors.error : theme.colors.primary, borderRadius: 25, width: 60, height: 30,
                        justifyContent: "center", alignItems: "center"}} onPress={() => {setEdit(false); onSavePress(userName, userEmail, userDateOfBirth, userAddress)}}
                                      disabled={!(nameIsValid && emailIsValid && dateOfBirthIsValid && addressIsValid)}>
                        <Text variant={"bodyLarge"} style={{color: theme.colors.background, textAlign: "center"}}
                        >Save</Text>
                    </TouchableOpacity>
                    :
                    <TouchableOpacity style={{backgroundColor: theme.colors.secondary, borderRadius: 25, width: 60, height: 30,
                        justifyContent: "center", alignItems: "center", borderColor: theme.colors.secondary, borderWidth: 1}} onPress={() => {setEdit(true)}}>
                        <Text variant={"bodyLarge"} style={{color: theme.colors.background, textAlign: "center"}}>Edit</Text>
                    </TouchableOpacity>

                }
            </View>
        </View>
    )
}