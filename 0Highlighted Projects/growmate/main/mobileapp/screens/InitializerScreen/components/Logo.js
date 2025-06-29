import {View, Image} from "react-native";

export default function Logo(){
    return (
        <View style={{width: "100%",height:200,alignItems: "center", marginTop: 180}}>
            <Image source={require('../../../assets/logotipo.png')}
                   resizeMode="contain"
                   style={{ width: "80%", height: "100%"}}
            />
        </View>
    )
}