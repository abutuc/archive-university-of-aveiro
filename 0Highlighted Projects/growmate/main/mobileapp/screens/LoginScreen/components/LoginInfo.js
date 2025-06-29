import {View} from "react-native";
import {TextInput, useTheme} from "react-native-paper";
import React, {useState} from "react";


export default function LoginInfo({setEmail, setPassword}){
    const theme = useTheme();
    const [hidePassword, setHidePassword] = useState(true);
    return (
        <View style={{marginLeft: 70, marginTop: 105}}>
            <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14, marginBottom: 20}}
                       underlineColor={theme.colors.primaryContainer}
                       activeUnderlineColor={theme.colors.primary}
                       textColor={theme.colors.onBackground}
                       placeholder={"E-mail"} placeholderTextColor={theme.colors.tertiary}
                       onChangeText={text => setEmail(text)}
            />
            <TextInput style={{ width: 260, backgroundColor: theme.colors.background, fontSize: 14}}
                       underlineColor={theme.colors.primaryContainer}
                       activeUnderlineColor={theme.colors.primary}
                       textColor={theme.colors.onBackground}
                       placeholder={"Password"} placeholderTextColor={theme.colors.tertiary}
                       secureTextEntry={hidePassword}
                       right={<TextInput.Icon icon={hidePassword ? "eye" : "eye-off"} onPress={() => {setHidePassword(!hidePassword)}} forceTextInputFocus={false}/>}
                       onChangeText={text => setPassword(text)}

            />
        </View>
    )
}