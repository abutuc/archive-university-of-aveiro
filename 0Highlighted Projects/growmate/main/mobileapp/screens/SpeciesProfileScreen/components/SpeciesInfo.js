import {View, StyleSheet} from "react-native";
import {IconButton, Text, useTheme} from "react-native-paper";


export default function SpeciesInfo({info}){
    const theme = useTheme();
    const stars = [];
    for (let i = 0; i < 5; i++){
        if (i < info.difficulty){
            stars.push(
                <IconButton key={i} icon={"star"} size={12} style={{ marginLeft: -6, marginRight: -6, marginTop: -5 }}/>
            )
        }
        else {
            stars.push(
            <IconButton key={i} icon={"star-outline"} size={12} style={{ marginLeft: -6, marginRight: -6, marginTop: -5 }}/>
            )
        }
    }

    const optimalTemperature = () => {
        switch (info.optimalTemperature){
            case "COOL":
                return "10ºC - 18ºC"
            case "AVERAGE":
                return "18ºC - 24ºC"
            case "WARM":
                return "24ºC - 30ºC"
        }
    }

    const optimalLuminosity = () => {
        switch (info.optimalLuminosity){
            case "LOW":
                return "Low-light areas (25 ft-c to 75 ft-c)"
            case "MEDIUM":
                return "Medium-light areas: 75 ft-c to 200 ft-c"
            case "HIGH":
                return "High-light areas: Over 200 ft-c, but not direct sun"
            case "SUNNY":
                return "High-light areas: Over 200 ft-c, but not direct sun"
        }
    }

    const optimalHumidity = () => {
        switch (info.optimalHumidity){
            case "LOW":
                return "5% to 24%"
            case "MEDIUM":
                return "25% to 49%"
            case "HIGH":
                return "50% or higher"
        }
    }

    const seasonality = () => {
        switch (info.season){
            case "FALL":
                return "Fall"
            case "WINTER":
                return "Winter"
            case "SPRING":
                return "Spring"
            case "SUMMER":
                return "Summer"
        }
    }

    const wateringFrequency = () => {
        switch (info.wateringFrequency){
            case "INFREQUENT":
                return "Soil mix can become moderately dry before re-watering"
            case "AVERAGE":
                return "Surface of soil mix should dry before re-watering"
            case "FREQUENT":
                return "Keep soil mix moist"

        }
    }


    return (
        <View style={styles.container}>
            <View style={styles.infoRow}>
                <View style={styles.info}>
                    <IconButton icon={"thermometer"}/>
                    <View style={styles.infoText}>
                        <Text variant={"bodyMedium"} style={{color: theme.colors.primary}}>Opt. Temperature</Text>
                        <Text variant={"bodySmall"} style={{color: theme.colors.secondary}}>{optimalTemperature()}</Text>
                    </View>
                </View>
                <View style={styles.infoRow}>
                    <IconButton icon={"recycle-variant"}/>
                    <View style={styles.infoText}>
                        <Text variant={"bodyMedium"} style={{color: theme.colors.primary}}>Cycle</Text>
                        <Text variant={"bodySmall"} style={{color: theme.colors.secondary}}>{info.cycle}</Text>
                    </View>
                </View>
            </View>
            <View style={styles.infoRow}>
                <View style={styles.info}>
                    <IconButton icon={"white-balance-sunny"}/>
                    <View style={styles.infoText}>
                        <Text variant={"bodyMedium"} style={{color: theme.colors.primary}}>Opt. Luminosity</Text>
                        <Text variant={"bodySmall"} style={{color: theme.colors.secondary}}>{optimalLuminosity()}</Text>
                    </View>
                </View>
                <View style={styles.info}>
                    <IconButton icon={"scale-unbalanced"}/>
                    <View style={styles.infoText}>
                        <Text variant={"bodyMedium"} style={{color: theme.colors.primary}}>Difficulty</Text>
                        <View style={{flexDirection: "row", alignItems: "center"}}>
                            {stars}
                        </View>
                    </View>
                </View>
            </View>
            <View style={styles.infoRow}>
                <View style={styles.info}>
                    <IconButton icon={"water-outline"}/>
                    <View style={styles.infoText}>
                        <Text variant={"bodyMedium"} style={{color: theme.colors.primary}}>Opt. Humidity</Text>
                        <Text variant={"bodySmall"} style={{color: theme.colors.secondary}}>{optimalHumidity()}</Text>
                    </View>
                </View>
                <View style={styles.info}>
                    <IconButton icon={"clock-outline"}/>
                    <View style={styles.infoText}>
                        <Text variant={"bodyMedium"} style={{color: theme.colors.primary}}>Season</Text>
                        <Text variant={"bodySmall"} style={{color: theme.colors.secondary}}>{seasonality()}</Text>
                    </View>
                </View>
            </View>
            <View style={styles.infoRow}>
                <View style={styles.info}>
                    <IconButton icon={"watering-can-outline"}/>
                    <View style={styles.infoText}>
                        <Text variant={"bodyMedium"} style={{color: theme.colors.primary}}>Water freq.</Text>
                        <Text variant={"bodySmall"} style={{color: theme.colors.secondary}}>{wateringFrequency()}</Text>
                    </View>
                </View>
                <View style={styles.info}>
                    <IconButton icon={"sprout-outline"}/>
                    <View style={styles.infoText}>
                        <Text variant={"bodyMedium"} style={{color: theme.colors.primary}}>Usual Size</Text>
                        <Text variant={"bodySmall"} style={{color: theme.colors.secondary}}>{info.usualSize}</Text>
                    </View>
                </View>
            </View>
            <View style={styles.infoRow}>
                <View style={styles.info}>
                    <IconButton icon={"flower"}/>
                    <View style={styles.infoText}>
                        <Text variant={"bodyMedium"} style={{color: theme.colors.primary}}>Flowering</Text>
                        <Text variant={"bodySmall"} style={{color: theme.colors.secondary}}>{info.flowering ? "Yes" : "No"}</Text>
                    </View>
                </View>
                <View style={styles.info}>
                    <IconButton icon={"leaf-maple"}/>
                    <View style={styles.infoText}>
                        <Text variant={"bodyMedium"} style={{color: theme.colors.primary}}>Leaf Color</Text>
                        <Text variant={"bodySmall"} style={{color: theme.colors.secondary}}>{info.leafColor}</Text>
                    </View>
                </View>
            </View>
        </View>

    )
}

const styles = StyleSheet.create({
    container: {
        flexDirection: "column", marginHorizontal: 35
    },
    infoRow: {
        flexDirection: "row", alignItems: "center", marginBottom: 8
    },
    info: {
        flexDirection: "row", alignItems: "center", width: 175
    },

    infoText: {
        flexDirection: "column",
        width: 120
    }
})