import {View} from "react-native";
import {Avatar, IconButton, Text, useTheme} from "react-native-paper";
import {userPhoto, userRating, userType} from "../../../user";
import React from "react";

export default function UserHeader({stats, name, logout}){
    const theme = useTheme();
    const staticAvatar = require("../../../assets/placeholder_user_avatar.png");
    const stars = [];
    for (let i = 0; i < 5; i++) {
        const isFilled = i < userRating;
        stars.push(
            <IconButton
                key={i}
                icon={isFilled ? "star" : "star-outline"}
                size={20}
                style={{ margin: 0 }}
            />
        );
    }
    return (
        <View>
            <IconButton icon={"logout"} iconColor={theme.colors.primary} size={35} style={{position: "absolute",
                right: 0}} onPress={logout}/>
            {userType === "PREMIUM" &&
                <IconButton icon={"leaf"} iconColor={theme.colors.golden} size={35} style={{position: "absolute",
                left: 0}} />
            }
            <View style={{alignItems: "center", marginTop: 40}}>
            {userPhoto !== "null" && userPhoto !== null ?
                <Avatar.Image
                size={120}
                source={ {uri: userPhoto}}
                style={{backgroundColor: theme.colors.primaryContainer}}
                />
                :
                <Avatar.Image
                size={120}
                source={staticAvatar}
                style={{backgroundColor: theme.colors.primaryContainer}}
                />
            }
            </View>

            <View style={{marginTop: 10, alignItems: "center"}}>
                <Text variant={"headlineSmall"} style={{color: theme.colors.primary, fontWeight: "bold"}}>
                        {name}
                </Text>
            </View>


            <View style={{flexDirection: "row", justifyContent: "center", alignItems: "center"}}>
                {stars}
            </View>

            <View style={{flexDirection: "row", justifyContent: "center"}}>
                <View style={{flexDirection: "row", justifyContent: "center", alignItems: "center", marginRight: 40}}>
                    <Text variant={"bodyLarge"}>{stats["plants"]}</Text>
                    <IconButton icon={"sprout-outline"} iconColor={theme.colors.tertiary} style={{margin: 0}} size={30}/>
                </View>
                <View style={{flexDirection: "row", justifyContent: "center", alignItems: "center"}}>
                    <Text variant={"bodyLarge"}>{stats["divisions"]}</Text>
                    <IconButton icon={"home-outline"} iconColor={theme.colors.tertiary} style={{margin: 0}} size={30}/>
                </View>
            </View>

        </View>
    )
}