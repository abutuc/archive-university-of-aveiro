import { View, Dimensions } from "react-native";
import { Card, Text, Avatar, useTheme } from 'react-native-paper';

export default function PlantSensorCard({ sensor }) {
    const cardSize = Dimensions.get('screen').width / 2;
    const theme = useTheme();

    const { id, name, sensorCode, type, value } = sensor;

    const selectIcon = (type) => {
        switch (type) {
            case "soilq":
                return "water"
            case "airq":
                return "weather-windy"
            case "airtemp":
                return "thermometer"
        }
    }

    const selectLabel = (type) => {
        switch (type) {
            case "soilq":
                return "Soil Moisture"
            case "airq":
                return "Air Humidity"
            case "airtemp":
                return "Air Temperature"
        }
    }

    return (
        <View style={{ width: cardSize }}>
            <Card>
                <Card.Content>
                    <View style={{ flexDirection: 'row', alignItems: 'center' }}>
                        <Avatar.Icon
                            size={40}
                            icon={selectIcon(value.type)}
                            style={{ backgroundColor: theme.colors.primary }}
                            color={theme.colors.background}
                        />
                        <View style={{ marginHorizontal: 15 }}>
                            <Text variant="bodyLarge">{value.value}{value.type === 'airtemp' ? "ºC" : "%"}</Text>
                            <Text variant="bodyMedium">{selectLabel(value.type)}</Text>
                        </View>
                    </View>
                </Card.Content>
            </Card>
        </View>
    )
}