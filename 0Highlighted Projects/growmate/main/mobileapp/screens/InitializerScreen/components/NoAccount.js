import {TouchableOpacity, View} from "react-native";
import {IconButton, Text, useTheme} from "react-native-paper";
import {useNavigation} from "@react-navigation/native";


export default function NoAccount(){
    const theme = useTheme();
    const navigation = useNavigation();
    return (

        <View style={{backgroundColor: theme.colors.background, borderWidth: 1, borderRadius: 20, justifyContent: "center",
            alignItems: "center", marginTop: 60, borderColor: theme.colors.primaryContainer,
            width: 135, height: 30, alignSelf: "center"
        }}>
            <TouchableOpacity onPress={() => {navigation.navigate("DiscoverPlants", {anonymous: true})}}>
            <View style={{flexDirection: "row", justifyContent: "center", alignItems: "center"}} >
                <Text variant={"bodyMedium"} style={{color: theme.colors.primaryContainer, marginLeft: 20}}>
                    No Account
                </Text>
                <IconButton icon={"arrow-right-thin"} iconColor={theme.colors.primaryContainer} size={20}/>
            </View>
            </TouchableOpacity>
        </View>
    )
}