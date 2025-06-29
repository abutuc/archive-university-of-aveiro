import { View } from "react-native";
import { IconButton, useTheme } from "react-native-paper";
import React from "react";
import { useNavigation } from "@react-navigation/native";

export default function BottomMenu({ screenHeight, active, anonymous }) {
    const theme = useTheme();
    const navigation = useNavigation();
    let leafColor = theme.colors.primary;
    let magnifyColor = theme.colors.primary;
    let calendarColor = theme.colors.primary;
    let accountColor = theme.colors.primary;

    switch (active) {
        case "leaf":
            leafColor = theme.colors.background;
            break
        case "magnify":
            magnifyColor = theme.colors.background;
            break
        case "calendar":
            calendarColor = theme.colors.background;
            break
        case "account":
            accountColor = theme.colors.background;
            break
    }

    return (
        <View style={{
            width: "100%", backgroundColor: theme.colors.primaryContainer, height: screenHeight / 13, position: 'absolute',
            bottom: screenHeight / 12, flexDirection: "row"
        }}>
            {anonymous ?
                <View style={{ position: 'relative', flex: 1, justifyContent: "center", alignItems: "center" }}>
                    <IconButton icon={"magnify"} iconColor={magnifyColor} size={35}
                        onPress={() => {
                            navigation.navigate("DiscoverPlants", { anonymous: true });
                        }} />
                </View>
                :
                <View style={{
                    width: "100%", backgroundColor: theme.colors.primaryContainer, height: screenHeight / 14, position: 'absolute',
                    bottom: 5, flexDirection: "row"
                }}>
                    <View style={{ position: 'relative', flex: 1, paddingLeft: 20, justifyContent: "center" }}>
                        <IconButton icon={"leaf"} iconColor={leafColor} size={35}
                            onPress={() => {
                                navigation.navigate("Home", { reload: false, variance: active});
                            }} />
                    </View>
                    <View style={{ position: 'relative', flex: 1, justifyContent: "center"  }}>
                        <IconButton icon={"magnify"} iconColor={magnifyColor} size={35}
                            onPress={() => {
                                navigation.navigate("DiscoverPlants", { anonymous: false });
                            }} />
                    </View>
                    <View style={{ position: 'relative', flex: 1, justifyContent: "center"  }}>
                        <IconButton icon={"calendar-month-outline"} iconColor={calendarColor} size={35}
                            onPress={() => {
                                navigation.navigate("Tasks");
                            }} />
                    </View>
                    <View style={{ position: 'relative', flex: 1, justifyContent: "center"  }}>
                        <IconButton icon={"account-outline"} iconColor={accountColor} size={35} onPress={() => {
                            navigation.navigate("Profile");
                        }} />
                    </View>
                </View>
            }
        </View>
    )
}