import {View} from "react-native";
import {IconButton, Text, useTheme} from "react-native-paper";

export default function AddDivisionDescription(){
    const theme = useTheme();
    return (
        <View style={{
            marginTop: 20,
            marginLeft: 50,
            width: 300,
            height: 50,
            borderRadius: 10,
            borderWidth: 1,
            borderColor: theme.colors.placeholderImageBackground,
            alignItems: "center",
            justifyContent: "center",
            backgroundColor: theme.colors.placeholderImageBackground,
            flexDirection: "row"
        }}>
            <IconButton icon={"information"}/>
            <Text variant={"bodyMedium"} style={{color: theme.colors.onBackground, width: 250}}>
                Associate a division to your home in order to better manage your plants.
            </Text>
        </View>
    )
}