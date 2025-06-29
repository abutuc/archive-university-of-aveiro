import {TouchableOpacity, View} from "react-native";
import {IconButton, Text, useTheme} from "react-native-paper";


export default function NextButton({text, reverse, onPress, disabled}){
    const theme = useTheme();
    return (
        <View style={{alignItems: "flex-end", marginRight: 60}}>
            <TouchableOpacity onPress={onPress} disabled={disabled}>

                {reverse ?
                    <View style={{width: 120, height: 35, borderRadius: 20,
                        backgroundColor: disabled ? theme.colors.outline : theme.colors.background, flexDirection: "row",
                        alignItems:"center", justifyContent: "center", borderWidth: 1, borderColor: disabled ? theme.colors.outline : theme.colors.primary
                    }}>
                        <Text variant={"bodyMedium"} style={{color: disabled ? theme.colors.background : theme.colors.primary, marginLeft: 25}}>
                            {text}
                        </Text>
                        <IconButton icon={"arrow-right-thin"} size={20} iconColor={disabled ? theme.colors.background : theme.colors.primary} style={{marginLeft: 5}}/>
                    </View>
                    :
                    <View style={{width: 120, height: 35, borderRadius: 20,
                        backgroundColor: disabled ? theme.colors.outline : theme.colors.primary, flexDirection: "row",
                        alignItems:"center", justifyContent: "center"
                    }}>
                        <Text variant={"bodyMedium"} style={{color: theme.colors.background, marginLeft: 25}}>
                            {text}
                        </Text>
                        <IconButton icon={"arrow-right-thin"} size={20} iconColor={theme.colors.background} style={{marginLeft: 5}}/>
                    </View>
                }

            </TouchableOpacity>
        </View>
    )
}