import { View } from "react-native";
import { Card, Text, Avatar, useTheme } from 'react-native-paper';

export default function SensorMeasurement({ type, value }) {
    const theme = useTheme();

    const selectIcon = (type) => {
        switch (type) {
            case "soil":
                return "water"
            case "air":
                return "weather-windy"
            case "temperature":
                return "thermometer"
        }
    }

    const selectLabel = (type) => {
        switch (type) {
            case "soil":
                return "Soil Moisture"
            case "air":
                return "Air Humidity"
            case "temperature":
                return "Air Temperature"
        }
    }

    return (
        <View style={{ width: 200, flex: 1, margin: 6}}
        >
            <Card>
                <Card.Content>
                    <View style={{ flexDirection: 'row', alignItems: 'center' }}>
                        <Avatar.Icon
                            size={40}
                            icon={selectIcon(type)}
                            style={{ backgroundColor: theme.colors.primary }}
                            color={theme.colors.background}
                        />
                        <View style={{ marginHorizontal: 10 }}>
                            <Text variant="bodyLarge">{value}{type == 'temperature' ? "ºC" : "%"}</Text>
                            <Text variant="bodyMedium">{selectLabel(type)}</Text>
                        </View>
                    </View>
                </Card.Content>
            </Card>
        </View>
    )
}