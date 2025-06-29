import { View } from "react-native";
import { Button, useTheme } from "react-native-paper";
import { useNavigation } from '@react-navigation/native';

export default function CheckSpeciesButton({plantId}) {
    const theme = useTheme();
    const navigation = useNavigation();

    return (
        <View style={{ alignItems: 'center', marginBottom: 30 }}>
            <Button
                textColor={theme.colors.background}
                buttonColor={theme.colors.primary}
                onPress={() =>  {navigation.navigate("SpeciesProfile", {plantId: plantId, specieID: -1, anonymous: false});}}
            >
                Check Species Info
            </Button>
        </View>
    )
}