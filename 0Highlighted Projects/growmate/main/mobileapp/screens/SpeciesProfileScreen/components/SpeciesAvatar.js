import { View } from "react-native";
import { Avatar, Text, useTheme } from "react-native-paper";

export default function SpeciesAvatar({ image, species, speciesFamily }) {
    const theme = useTheme();

    return (
        <View style={{ alignItems: 'center', marginBottom: 10 }}>
            <Text variant={"headlineSmall"} style={{ color: theme.colors.tertiary, fontWeight: '700', marginBottom: 10 }}>
                {species}
            </Text>
            <Avatar.Image
                size={150}
                source={ {uri: image}}
            />
            <Text variant={"titleMedium"} style={{ color: theme.colors.secondary, fontWeight: '400', marginTop: 10 }}>
                {speciesFamily}
            </Text>
        </View>
    )
}