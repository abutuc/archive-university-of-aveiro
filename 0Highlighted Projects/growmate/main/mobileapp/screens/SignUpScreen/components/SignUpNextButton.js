import {Text, useTheme} from "react-native-paper";
import {TouchableOpacity} from "react-native";


export default function SignUpNextButton({next, disabled}){
    const theme = useTheme();
    return (
        <TouchableOpacity disabled={disabled} style={{backgroundColor: disabled ? theme.colors.outline : theme.colors.primaryContainer, borderRadius: 15, justifyContent: "center",
            alignItems: "center", marginTop: 85, width: 145, height: 35, alignSelf: "center"}}
                          onPress={() => {next()}}>
            <Text variant={"bodyLarge"} style={{color: theme.colors.background}}>
                Next
            </Text>
        </TouchableOpacity>
    )
}