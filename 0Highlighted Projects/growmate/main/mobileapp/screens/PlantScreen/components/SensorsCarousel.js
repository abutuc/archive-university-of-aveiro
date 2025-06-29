import React, { useState, useEffect } from 'react';
import Carousel from "react-native-reanimated-carousel";
import PlantSensorCard from './PlantSensorCard';
import { View, Dimensions } from 'react-native';

const { width: screenWidth } = Dimensions.get('window');
const { height: screenHeight } = Dimensions.get('window');

export default function SensorsCarousel({ sensors }) {

    const [entries, setEntries] = useState([]);

    useEffect(() => {
        // Only set entries if the value in not "No data"
        for (let i = 0; i < sensors.length; i++) {
            if (sensors[i].value !== "No data") {
                setEntries((prev) => [...prev, sensors[i]]);
            }
        }
    }, []);

    const renderItem = ({ item, index }) => {
        return (
            <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
                <PlantSensorCard key={index} sensor={item} />
            </View>
        );
    };

    if (entries.length === 0) return (
        <></>
    );

    return (
        <View style={{ height: screenHeight / 6 }}>
            <Carousel
                loop={false}
                width={screenWidth}
                data={entries}
                renderItem={renderItem}
                mode="parallax"
                modeConfig={{
                    parallaxScrollingScale: 0.9,
                    parallaxScrollingOffset: 200,
                }}
            />
        </View>
    );
}