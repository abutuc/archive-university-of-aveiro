import React from "react";
import { View } from "react-native";
import { Card, Avatar, Button, Text, useTheme } from "react-native-paper";

export default function SensorInfoCard({ sensor, onPress }) {
  const theme = useTheme();

  return (
    <View style={{ paddingVertical: 10 }}>
      <Card style={{ backgroundColor: theme.colors.background }}>
        <Card.Title
          title={sensor.name}
          subtitle={sensor.type === "plant" ? "Plant Sensor" : "Division Sensor"}
          left={(props) => (
            <Avatar.Icon
              {...props}
              icon={sensor.type === "plant" ? "flower" : "layers"}
              style={{ backgroundColor: theme.colors.primary }}
            />
          )}
        />
        <Card.Actions>
          <Button style={{backgroundColor: theme.colors.background}} onPress={onPress}>View Details</Button>
        </Card.Actions>
      </Card>
    </View>
  );
}