import {TouchableOpacity, View} from "react-native";
import {Text, useTheme} from "react-native-paper";
import {useNavigation} from "@react-navigation/native";

export default function SignUpButton(){
    const theme = useTheme();
    const navigation = useNavigation();
    return (
        <View style={{alignItems: "center", marginTop: 35, height: 30}}>
            <Text variant={"bodySmall"} style={{color: theme.colors.primary}}>
                Don't have an account?{' '}
                <TouchableOpacity onPress={() => {navigation.navigate("SignUp")}}>
                    <Text variant={"bodySmall"} style={{textDecorationLine: 'underline', color: theme.colors.primary}}
                    >
                        Sign-up
                    </Text>
                </TouchableOpacity>
                {' '}instead!
            </Text>
        </View>
    )
}