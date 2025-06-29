import { ScrollView } from "react-native";
import DivisionRow from "./DivisionRow";

export default function Divisions({ divisions, plants, handleUpdate, scrollViewRef, handleScroll }) {


    return (
        <ScrollView ref={scrollViewRef} style={{ maxHeight: 485 }} onScroll={handleScroll}>
            {divisions.map((division, index) => (
                <DivisionRow key={index} divisionName={division.name} plantCards={division.plants} userPlants={plants} divisionID={division.id} handleUpdate={handleUpdate}/>
            ))}
        </ScrollView>
    );

}