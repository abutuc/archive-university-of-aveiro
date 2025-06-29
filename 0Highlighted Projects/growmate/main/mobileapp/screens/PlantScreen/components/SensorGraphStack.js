import { useState, useEffect } from 'react'
import { View, Dimensions, ScrollView } from 'react-native';
import { SegmentedButtons, Text } from 'react-native-paper';
import SensorGraph from './SensorGraph'
import 'react-native-reanimated';

export default function SensorGraphStack({ measurements }) {
    const { width: screenWidth } = Dimensions.get('window');
    const screenHeigth = Dimensions.get("window").height;

    const [data, setData] = useState(measurements);
    const [value, setValue] = useState("temperature")

    const [sensorValuesList, setSensorValuesList] = useState([]);

    const processSensorData = (sensorData) => {
        return sensorData.map((date) => {
          const hourlyData = date.reduce((acc, item) => {
            const hour = item.postDate.slice(11, 13);
            acc[hour] = item;
            return acc;
          }, {});
      
          const labels = Object.keys(hourlyData).sort((a, b) => a.localeCompare(b));
          const lastHourlyValues = labels.map((hour) => hourlyData[hour].measurement);
      
          return {
            labels,
            datasets: [{
              data: lastHourlyValues
            }],
            legend: [date[0].postDate.slice(0, 10)]
          };
        });
      };
      
      useEffect(() => {
        let sensorData = [];
      
        switch (value) {
          case 'temperature':
            if (data.airtemp && Object.keys(data.airtemp).length > 0) {
              sensorData = Object.values(data.airtemp);
            }
            break;
          case 'air_humidity':
            if (data.airq && Object.keys(data.airq).length > 0) {
              sensorData = Object.values(data.airq);
            }
            break;
          case 'soil_humidity':
            if (data.soilq && Object.keys(data.soilq).length > 0) {
              sensorData = Object.values(data.soilq);
            }
            break;
          default:
            break;
        }
      
        const processedSensorData = processSensorData(sensorData);
        setSensorValuesList(processedSensorData);
      }, [value, data]);
            

    return (
        <View style={{ paddingBottom: 200, paddingTop: 30 }}>
            <View style={{marginBottom: 15, paddingHorizontal: 15}}>
                <SegmentedButtons 
                    value={value}
                    onValueChange={setValue}
                    buttons={[
                        {
                            value: 'temperature',
                            label: 'Temperature'
                        },
                        {
                            value: 'air_humidity',
                            label: 'Air Humidity'
                        },
                        {
                            value: 'soil_humidity',
                            label: 'Soil Moisture'
                        },
                    ]}
                />
            </View>
            <ScrollView>
                {sensorValuesList.length > 0 ? sensorValuesList.map((item, index) => {
                    return(
                        <SensorGraph key={index} data={item} /> 
                    )
                }): (
                    <View style={{height: screenHeigth, justifyContent: 'center', alignItems: 'center'}}>
                        <Text>No data available</Text>
                    </View>
                )}
            </ScrollView>
        </View>
        
    )
}