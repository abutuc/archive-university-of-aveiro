import {TouchableOpacity} from "react-native";
import {Text, useTheme} from "react-native-paper";
import {useNavigation} from "@react-navigation/native";


export default function LogIn(){
    const theme = useTheme();
    const navigation = useNavigation();
    return (
        <TouchableOpacity style={{backgroundColor: theme.colors.primaryContainer, borderRadius: 15, justifyContent: "center",
            alignItems: "center", marginTop: 35, width: 135, height: 35, alignSelf: "center"}}
                          onPress={() => {navigation.navigate("Login")}}
        >
            <Text variant={"bodyLarge"} style={{color: theme.colors.background}}>
                LOG IN
            </Text>
        </TouchableOpacity>
    )
}