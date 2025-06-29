import { View } from "react-native";
import { useTheme, Text, Avatar } from "react-native-paper";

export default function PlantStatus({ name, status}) {
    const theme = useTheme();

    const getEmote = (status) => {
        switch (status) {
            case "GREAT":
                return "emoticon-happy-outline"
            case "NORMAL":
                return "emoticon-confused-outline"
            case "BAD":
                return "emoticon-sad-outline"
        }
    }

    const getColor = (status) => {
        switch (status) {
            case "GREAT":
                return theme.colors.primary
            case "NORMAL":
                return theme.colors.golden
            case "BAD":
                return theme.colors.darkRed
        }
    }

    const getText = (status) => {
        switch (status) {
            case "GREAT":
                return "No need to add anything"
            case "NORMAL":
                return "You should check your tasks"
            case "BAD":
                return "Stop neglecting me"
        }
    }

    return (
        <View style={{ paddingBottom: 30, flexDirection: "row", alignItems: "center", justifyContent: "space-between", paddingHorizontal: 15 }}>
            <View style={{ flex: 1 }}>
                <Avatar.Icon size={120} icon={getEmote(status)} style={{ backgroundColor:  "white"}} color={getColor(status)}/>
            </View>
            <View style={{ flex: 2 }}>
                <Text variant="headlineSmall" >{name} is feeling {status.toLowerCase()}</Text>
                <Text variant="bodyLarge" >{getText(status)}</Text>
            </View>
        </View>
    )
}