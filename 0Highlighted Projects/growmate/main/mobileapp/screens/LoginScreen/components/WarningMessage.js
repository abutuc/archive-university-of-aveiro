import {View} from "react-native";
import {Text, useTheme} from "react-native-paper";

export default function WarningMessage(){
    const theme = useTheme();
    return (
        <View style={{alignItems: "center", marginTop: 15}}>
            <Text variant={"bodySmall"} style={{color: theme.colors.error}}>
                Either the email or password are incorrect.
            </Text>
        </View>
    )
}