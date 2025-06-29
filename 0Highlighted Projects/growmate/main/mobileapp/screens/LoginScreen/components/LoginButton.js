import {TouchableOpacity} from "react-native";
import {Text, useTheme} from "react-native-paper";


export default function LoginButton({login}){
    const theme = useTheme();
    return (
        <TouchableOpacity style={{backgroundColor: theme.colors.primaryContainer, borderRadius: 15, justifyContent: "center",
            alignItems: "center", marginTop: 85, width: 145, height: 35, alignSelf: "center"}}
                          onPress={() => {login()}}>
            <Text variant={"bodyLarge"} style={{color: theme.colors.background}}>
                LOG IN
            </Text>
        </TouchableOpacity>
    )
}