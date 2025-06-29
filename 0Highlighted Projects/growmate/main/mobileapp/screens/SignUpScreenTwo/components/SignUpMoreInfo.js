import {Checkbox, IconButton, Text, TextInput, useTheme} from "react-native-paper";
import React, {useEffect, useState} from "react";
import {View} from "react-native";
import AddPhoto from "../../AddPlantScreen/components/AddPhoto";


export default function SignUpMoreInfo({photo, setPhoto, setNameIsValid, setName, nameIsValid,
                                           setDateOfBirthIsValid, setDate, dateOfBirthIsValid,
                                           setAddressIsValid, setAddress, addressIsValid,
                                           setUserType, userType, setExperience, experience}){
    const theme = useTheme();
    const dateRegex = /^(19|20)\d{2}-(0[1-9]|1[0-2])-(0[1-9]|1\d|2\d|3[01])$/;
    const nameValidate = (name) => {
        if (name.length > 0){
            setNameIsValid(true);
            setName(name);
        }
        else {
            setNameIsValid(false);
        }
    }

    const dateOfBirthValidate = (date) => {
        if (dateRegex.test(date)){
            setDateOfBirthIsValid(true);
            setDate(date);
        }
        else {
            setDateOfBirthIsValid(false);
        }
    }

    const addressValidate = (address) => {
        if (address.length > 0){
            setAddressIsValid(true);
            setAddress(address);
        }
        else {
            setAddressIsValid(false);
        }
    }

    const handlePremiumCheckBoxChange = () => {setUserType(!userType);}

    const [stars, setStars] = useState([]);

    useEffect( () => {
        const newStars = [];
        for (let i = 0; i < 5; i++){
            if (i < experience){
                newStars.push(
                    <IconButton key={i} onPress={() => {setExperience(i+1)}} icon={"star"} size={25} style={{ marginLeft: -6, marginRight: -6, marginTop: -5 }}/>
                )
            }
            else {
                newStars.push(
                    <IconButton key={i} onPress={() => {setExperience(i+1)}} icon={"star-outline"} size={25} style={{ marginLeft: -6, marginRight: -6, marginTop: -5 }}/>
                )
            }
        }
        setStars(newStars);
    }, [experience])


    return (
        <View style={{marginTop: 10}}>
            <AddPhoto image={photo} setImage={setPhoto} plant={false}/>
            <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14, marginBottom: 5,
                marginLeft: 60}}
                       underlineColor={nameIsValid ? theme.colors.primaryContainer : theme.colors.error}
                       activeUnderlineColor={nameIsValid ? theme.colors.primary : theme.colors.error}
                       textColor={theme.colors.onBackground}
                       placeholder={"Name"} placeholderTextColor={theme.colors.tertiary}
                       onChangeText={text => nameValidate(text)}
            />
            {!nameIsValid &&
                <Text variant={"bodySmall"} style={{color: theme.colors.error, marginLeft: 60}}>
                    Name must not be empty.
                </Text>
            }
            <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14, marginBottom: 5,
                marginLeft: 60}}
                       underlineColor={dateOfBirthIsValid ? theme.colors.primaryContainer : theme.colors.error}
                       activeUnderlineColor={dateOfBirthIsValid ? theme.colors.primary : theme.colors.error}
                       textColor={theme.colors.onBackground}
                       placeholder={"Date of Birth (yyyy-mm-dd)"} placeholderTextColor={theme.colors.tertiary}
                       onChangeText={text => dateOfBirthValidate(text)}
            />
            {!dateOfBirthIsValid &&
                <Text variant={"bodySmall"} style={{color: theme.colors.error, marginLeft: 60}}>
                    Date must in the format yyyy-mm-dd.
                </Text>
            }
            <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14, marginBottom: 5,
                marginLeft: 60}}
                       underlineColor={addressIsValid ? theme.colors.primaryContainer : theme.colors.error}
                       activeUnderlineColor={addressIsValid ? theme.colors.primary : theme.colors.error}
                       textColor={theme.colors.onBackground}
                       placeholder={"Address"} placeholderTextColor={theme.colors.tertiary}
                       onChangeText={text => addressValidate(text)}
            />
            {!addressIsValid &&
                <Text variant={"bodySmall"} style={{color: theme.colors.error, marginLeft: 60}}>
                    Address must not be empty.
                </Text>
            }
            <View style={{flexDirection: "column", marginBottom: 10, marginTop: 10}}>
                <Text variant={"bodyMedium"} style={{color: theme.colors.primary, marginLeft: 60}}>
                    Experience with plants
                </Text>
                <View style={{flexDirection: "row", alignItems: "center", justifyContent:"center", marginTop: 5}}>
                    {stars}
                </View>
            </View>
            <View style={{flexDirection: "row", marginLeft: 60, alignItems: "center", justifyContent: "space-between", width: 250}}>
                <Text variant={"bodyMedium"} style={{color: theme.colors.primary}}>
                    I am a premium user
                </Text>
                <Checkbox
                    status={userType ? 'checked' : 'unchecked'}
                    onPress={() => {handlePremiumCheckBoxChange();}}
                    color={theme.colors.primary}
                    uncheckedColor={theme.colors.primary}
                />
            </View>
        </View>

    )
}