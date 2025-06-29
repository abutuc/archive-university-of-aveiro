import { Dimensions } from 'react-native'
import { useTheme, Surface } from 'react-native-paper'
import { LineChart } from 'react-native-chart-kit'

export default function SensorGraph({ data }) {
    const screenWidth = Dimensions.get("window").width;
    const screenHeigth = Dimensions.get("window").height;
    const theme = useTheme();

    const chartConfig = {
        backgroundColor: theme.colors.background,
        backgroundGradientFrom: theme.colors.background,
        backgroundGradientTo: theme.colors.background,
        decimalPlaces: 1, // optional, defaults to 2dp
        color: (opacity = 1) => theme.colors.primary,
        labelColor: (opacity = 1) => "black",
        style: {
            borderRadius: 16
        },
        propsForDots: {
            r: "5",
            strokeWidth: "1",
            stroke: theme.colors.primary
        }
    };

    return (
        <Surface style={{marginVertical: 5}}>
            <LineChart
                data={data}
                width={screenWidth}
                height={screenHeigth/4}
                chartConfig={chartConfig}
                bezier
                withShadow={false}
                fromZero={true}
            />
        </Surface>
    )
}