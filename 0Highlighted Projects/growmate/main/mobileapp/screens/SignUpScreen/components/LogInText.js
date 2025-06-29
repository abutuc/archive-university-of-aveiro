import {Text, useTheme} from "react-native-paper";
import {TouchableOpacity, View} from "react-native";
import {useNavigation} from "@react-navigation/native";

export default function LogInText(){
    const theme = useTheme();
    const navigation = useNavigation();
    return (
        <View style={{alignItems: "center", marginTop: 20, height: 30}}>
            <Text variant={"bodySmall"} style={{color: theme.colors.primary}}>
                Already have an account?{' '}
                <TouchableOpacity onPress={() => {navigation.navigate("Login")}}>
                    <Text variant={"bodySmall"} style={{textDecorationLine: 'underline', color: theme.colors.primary}}
                    >
                        Log-in
                    </Text>
                </TouchableOpacity>
                {' '}instead!
            </Text>
        </View>
    )
}