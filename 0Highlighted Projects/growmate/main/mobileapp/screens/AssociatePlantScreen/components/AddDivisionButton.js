import {TouchableOpacity, View} from "react-native";
import {Text, useTheme} from "react-native-paper";
import {useNavigation} from "@react-navigation/native";

export default function AddDivisionButton(){
    const theme = useTheme();
    const navigation = useNavigation();

    return (
        <TouchableOpacity onPress={() => {navigation.navigate("AddDivision", {destiny: "AssociatePlant"})}}>
            <View style={{width: 130, height: 30 , backgroundColor: theme.colors.primary, borderRadius: 20,
                alignItems: "center", justifyContent: "center", marginTop: 30, marginLeft: 40, marginBottom: 100}}>
                <Text variant={"bodyMedium"} style={{color: theme.colors.background}}>
                    Add a division
                </Text>
            </View>
        </TouchableOpacity>

    )
}