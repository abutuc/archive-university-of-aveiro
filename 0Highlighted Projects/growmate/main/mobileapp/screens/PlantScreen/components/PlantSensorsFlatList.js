import React, { useState, useEffect } from 'react';
import { View, Dimensions, FlatList } from "react-native";
import PlantSensorCard from "./PlantSensorCard";

export default function PlantSensorFlatList({ sensors }) {
    const screenWidht = Dimensions.get('screen').width;

    const [entries, setEntries] = useState([]);

    useEffect(() => {
        // Only set entries if the value in not "No data"
        for (let i = 0; i < sensors.length; i++) {
            if (sensors[i].value !== "No data") {
                setEntries((prev) => [...prev, sensors[i]]);
            }
        }
    }, []);

    const renderItem = ({ item }) => {
        return (
            <PlantSensorCard
                key={item.sensorCode}
                sensor={item}
            />
        );
    };

    return (
        <FlatList
            horizontal
            pagingEnabled
            contentContainerStyle={{ paddingBottom: 25 }}
            data={entries}
            renderItem={renderItem}
        />
    )
}