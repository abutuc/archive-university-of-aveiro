import {Text, useTheme} from "react-native-paper";
import {TouchableOpacity} from "react-native";


export default function SignUpButton({signup, disabled}){
    const theme = useTheme();
    return (
        <TouchableOpacity disabled={disabled} style={{backgroundColor: disabled ? theme.colors.outline : theme.colors.primaryContainer, borderRadius: 15, justifyContent: "center",
            alignItems: "center", marginTop: 10, width: 145, height: 35, alignSelf: "center"}}
                          onPress={() => {signup()}}>
            <Text variant={"bodyLarge"} style={{color: theme.colors.background}}>
                Sign Up
            </Text>
        </TouchableOpacity>
    )
}