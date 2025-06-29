import { Avatar, Card, useTheme } from "react-native-paper";
import React from "react";
import { TouchableOpacity } from "react-native";
import { useNavigation } from '@react-navigation/native';

export default function PlantCard({id, name, image, state }) {
    const navigation = useNavigation();
    const theme = useTheme();
    let good = false;
    let okay = false;
    let bad = false;

    if (state === "GREAT" ){
        good = true;
    }
    else if (state === "NORMAL"){
        okay = true;
    }
    else if (state === "BAD"){
        bad = true;
    }

    return (
        <TouchableOpacity
            style={{ width: 150, flex: 1, margin: 6, backgroundColor: theme.colors.background }}
            onPress={() => {
                navigation.navigate("Plant", {
                    plantID: id
                });
            }}
        >
            <Card>
                <Card.Cover style={{ height: 100, borderRadius: 0, backgroundColor: theme.colors.background }} source={ {uri : image} } />
                {good &&
                    <Card.Title
                        title={name}
                        style={{ backgroundColor: theme.colors.background, borderRadius: 0 }}
                        right={(props) => <Avatar.Icon {...props} icon="emoticon-happy-outline"
                                                       size={40}
                                                       style={{ backgroundColor: 'white'}}
                                                       color={theme.colors.primary}/>}
                    />
                }

                {okay &&
                    <Card.Title
                        title={name}
                        style={{ backgroundColor: theme.colors.background, borderRadius: 0 }}
                        right={(props) => <Avatar.Icon {...props} icon="emoticon-confused-outline"
                                                       size={40}
                                                       style={{ backgroundColor: 'white'}}
                                                       color={theme.colors.golden}/>}
                    />
                }

                {bad &&
                    <Card.Title
                        title={name}
                        style={{ backgroundColor: theme.colors.background, borderRadius: 0 }}
                        right={(props) => <Avatar.Icon {...props} icon="emoticon-sad-outline"
                                                       size={40}
                                                       style={{ backgroundColor: 'white'}}
                                                       color={theme.colors.darkRed}/>}
                    />
                }


            </Card>
        </TouchableOpacity>
    )
}