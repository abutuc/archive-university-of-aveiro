import { ScrollView } from "react-native";
import PlantCardRow from "./PlantCardRow";

export default function PlantCards({ plants }) {
    const cardsPerRow = 2;
    const numRows = Math.ceil(plants.length / cardsPerRow);
    const rows = [];
    for (let i = 0; i < numRows; i++) {
        const start = i * cardsPerRow;
        const end = start + cardsPerRow;
        rows.push(plants.slice(start, end));
    }

    return (
        <ScrollView style={{ maxHeight: 435 }}>
            {rows.map((row, index) => (
                <PlantCardRow key={index} plantCards={row} />
            ))}
        </ScrollView>
    );


}