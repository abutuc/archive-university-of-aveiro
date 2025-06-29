import {TouchableOpacity, View} from "react-native";
import {useTheme, Text} from "react-native-paper";


export default function GoBackButton({onPress}){
    const theme = useTheme();
    return (
        <TouchableOpacity style={{width: "100%", alignItems:"center", justifyContent: "center" }} onPress={onPress}>
            <View style={{width: 200, height: 40, backgroundColor: theme.colors.background, borderWidth: 1,
            borderColor: theme.colors.primary, alignItems:"center", justifyContent: "center", marginHorizontal: 30,
            borderRadius: 20}}>
                <Text variant={"bodyMedium"} style={{color: theme.colors.primary}}>
                    Go back to general tasks
                </Text>
            </View>
        </TouchableOpacity>

    )
}