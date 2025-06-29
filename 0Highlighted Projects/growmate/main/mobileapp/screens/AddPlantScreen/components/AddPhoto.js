import {TouchableOpacity, View} from "react-native";
import {useTheme, Text, Avatar} from "react-native-paper";
import React from "react";
import * as ImagePicker from "expo-image-picker";
import { uploadImage } from "../../../service/FirebaseService";

export default function AddPhoto({image, setImage, plant}) {
    const theme = useTheme();
    const plantPlaceHolderAvatar = require("../../../assets/placeholder_avatar.png");
    const userPlaceHolderAvatar = require("../../../assets/placeholder_user_avatar.png" );


    const pickImage = async () => {
        let result = await ImagePicker.launchImageLibraryAsync({
            mediaTypes: ImagePicker.MediaTypeOptions.All,
            allowsEditing: true,
            aspect: [4, 3],
            quality: 1,
        });

        if (!result.canceled) {
            setImage(result.assets[0].uri)
        }
    };

    const removePhoto = () => {
        setImage(null);
    }

    return (
        <View style={{ marginTop: 10, marginHorizontal: 35, marginBottom: plant ? 10 : 0}}>
            <View>
                <Text variant={"titleMedium"} style={{color: theme.colors.primary, fontWeight: "600", marginLeft: 30}}>
                    Add a photo:
                </Text>
                <View style={{alignItems: "center", marginTop: plant ? 10 : 5}}>
                    <TouchableOpacity onPress={pickImage}>
                        {image ?
                            <Avatar.Image
                                size={plant ? 100 : 80}
                                source={{ uri: image }}
                            />
                            :
                            <Avatar.Image
                                size={plant ? 100 : 80}
                                style={{backgroundColor: theme.colors.placeholderImageBackground, borderWidth: 1, borderColor: "rgba(87, 98, 74, 0.3)"}}
                                source={plant ? plantPlaceHolderAvatar : userPlaceHolderAvatar}
                            />
                        }

                    </TouchableOpacity>
                    {image &&
                        <View style={{borderRadius: 20, height: 35, width: 120,
                            backgroundColor: theme.colors.tertiary, alignItems: "center",
                            justifyContent: "center", marginTop: 15}}>
                            <TouchableOpacity onPress={removePhoto}>
                            <Text variant={"bodyMedium"} style={{color: theme.colors.background}}>Remove Photo</Text>
                            </TouchableOpacity>
                        </View>
                    }
                </View>
            </View>
        </View>
    )
}