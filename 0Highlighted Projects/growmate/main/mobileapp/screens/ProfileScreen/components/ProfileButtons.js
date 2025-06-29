import {TouchableOpacity, View} from "react-native";
import {Text, useTheme} from "react-native-paper";


export default function ProfileButtons({selected, setSelected}){
    const theme = useTheme();
    return (
        <View style={{flexDirection: "row", justifyContent: "center", alignItems: "center", marginTop: 10}}>
            <TouchableOpacity style={{backgroundColor: selected==="Details" ? theme.colors.primary : theme.colors.background, borderRadius: 20, width: 100, height: 30,
                justifyContent: "center", marginRight: 20, borderColor: theme.colors.primary, borderWidth: 1}} onPress={() => {setSelected("Details")}}>
                <Text variant={"bodyLarge"} style={{color: selected==="Details" ? theme.colors.background : theme.colors.primary, textAlign:"center"}}>Details</Text>
            </TouchableOpacity>
            <TouchableOpacity style={{backgroundColor: selected==="Tasks Log" ? theme.colors.primary : theme.colors.background, borderRadius: 20, width: 100, height: 30,
                justifyContent: "center", borderColor: theme.colors.primary, borderWidth: 1, marginRight: 20}} onPress={() => {setSelected("Tasks Log")}}>
                <Text variant={"bodyLarge"} style={{color: selected==="Tasks Log" ? theme.colors.background : theme.colors.primary, textAlign: "center"}}>Tasks Log</Text>
            </TouchableOpacity>
            <TouchableOpacity style={{backgroundColor: selected==="Password" ? theme.colors.primary : theme.colors.background, borderRadius: 20, width: 100, height: 30,
                justifyContent: "center", borderColor: theme.colors.primary, borderWidth: 1}} onPress={() => {setSelected("Password")}}>
                <Text variant={"bodyLarge"} style={{color: selected==="Password" ? theme.colors.background : theme.colors.primary, textAlign: "center"}}>Password</Text>
            </TouchableOpacity>
        </View>
    )
}