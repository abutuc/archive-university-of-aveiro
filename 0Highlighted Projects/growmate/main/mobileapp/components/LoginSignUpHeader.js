import {IconButton, Text, useTheme} from "react-native-paper";
import {useNavigation} from "@react-navigation/native";
import {View} from "react-native";

export default function LoginSignUpHeader({title, subtitle}){
    const theme = useTheme()
    const navigation = useNavigation();
    return (
        <View style={{flexDirection: "column", marginTop: 30}}>
            <View style={{flexDirection: "row", marginLeft: 10}}>
                <IconButton icon={"chevron-left"} iconColor={theme.colors.primary} onPress={() => {navigation.goBack()}}/>
                <Text variant={"headlineMedium"} style={{color: theme.colors.primary, fontWeight: "bold", width: 260, marginLeft: 20}}>
                    {title}
                </Text>
            </View>
            <Text variant={"bodyMedium"} style={{color: theme.colors.primary, width: 250, marginLeft: 80, marginTop: 5}}>
                {subtitle}
            </Text>
        </View>
    )
}