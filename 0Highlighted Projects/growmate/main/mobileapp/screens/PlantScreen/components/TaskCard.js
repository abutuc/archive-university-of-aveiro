import { useState } from "react";
import { View, Dimensions } from "react-native"
import { Card, useTheme, Text } from "react-native-paper"

export default function TaskCard({ name }) {
    const width = Dimensions.get("window").width
    const theme = useTheme();

    const [checked, setChecked] = useState(false);

    return (
        <View style={{ width: width / 2 }}>
            <Card>
                <Card.Content>
                    <View style={{ flexDirection: 'row', alignItems: 'center', justifyContent: "center" }}>
                        <Text variant="titleMedium">{name}</Text>
                    </View>
                </Card.Content>
            </Card>
        </View>
    )
}