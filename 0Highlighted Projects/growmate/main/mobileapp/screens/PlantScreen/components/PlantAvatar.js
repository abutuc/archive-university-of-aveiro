import { View } from "react-native";
import { Avatar, Text, useTheme } from "react-native-paper";

export default function PlantAvatar({ image, species }) {
    const theme = useTheme();

    return (
        <View style={{ alignItems: 'center', paddingBottom: 25 }}>
            <Avatar.Image
                size={150}
                source={{uri : image}}
            />
            <Text variant={"headlineSmall"} style={{ color: theme.colors.primary, fontWeight: '700' }}>
                {species}
            </Text>
        </View>
    )
}