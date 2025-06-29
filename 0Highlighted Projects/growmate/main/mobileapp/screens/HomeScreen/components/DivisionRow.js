import { useState } from "react";
import PlantCard from "./PlantCard";
import {ScrollView, View} from "react-native";
import {Divider, IconButton, Text, useTheme} from "react-native-paper";
import ChangePlantDivision from "./ChangePlantDivisionDialog";
import RemovePlantDivision from "./RemovePlantDivisionDialog";
import {
    addPlantToDivision,
    changePlantDivision,
    deleteDivision,
    removePlantFromDivision
} from "../../../service/HomeScreenService";
import {userID} from "../../../user";

export default function DivisionRow({ plantCards, divisionName, userPlants, divisionID, handleUpdate }) {
    const theme = useTheme();

    // API call to remove Plant from Division
    const [visibleRemove, setVisibleRemove] = useState(false);
    const setRemove = () => setVisibleRemove(true);
    const hideRemove = () => setVisibleRemove(false);

    const handleRemovePlant = (plants, divisionID) => {
        plants.forEach((plant) => removePlantFromDivision(userID, plant.id, divisionID));
        hideRemove();
        handleUpdate();
    }

    // API calls to add or change Plant to Division
    const [visibleChange, setVisibleChange] = useState(false);
    const setChange = () => setVisibleChange(true);
    const hideChange = () => setVisibleChange(false);

    const handleAddPlant = (plants, divisionID) => {
        plants
          .filter((plant) => plant.division === null)
          .forEach((plant) => addPlantToDivision(userID, plant.id, divisionID))
      
        plants
          .filter((plant) => plant.division != null)
          .forEach((plant) => changePlantDivision(userID, plant.id, plant.division["id"], divisionID))

        hideChange();
        handleUpdate();
      }

    const handleDeleteDivision = () => {
        deleteDivision(userID, divisionID).then(() => {
            hideRemove();
            handleUpdate();
        });
    }

    return (
        <View>
            <View style={{flexDirection: "row", alignItems:"center", marginHorizontal: 30}}>
                <IconButton icon="plus-circle-outline" iconColor={theme.colors.secondary} size={22} style={{marginRight: 0}} onPress={setChange}/>
                <IconButton icon="delete" iconColor={theme.colors.secondary} size={22} style={{marginLeft: 0}} onPress={setRemove}/>
                <Text variant={"titleLarge"} style={{ color: theme.colors.primary, fontWeight: '700' }}>{divisionName}</Text>
                {plantCards.length > 1 ?
                    <Text variant={"bodyMedium"}
                          style={{ color: theme.colors.secondary, fontWeight: '500', marginLeft: 20 }}>
                        {plantCards.length} plants
                    </Text>
                    :
                    <Text variant={"bodyMedium"}
                          style={{ color: theme.colors.secondary, fontWeight: '500', marginLeft: 20 }}>
                        {plantCards.length} plant
                    </Text>
                }
            </View>
            <ScrollView horizontal style={{ marginTop: 10, marginHorizontal: 30}}>
                {plantCards.map((plantCard, index) => (
                    <PlantCard
                        key={index}
                        id={plantCard.id}
                        name={plantCard.name}
                        image={plantCard.plantPhoto}
                        state={plantCard.plantCondition}
                    />
                ))}
            </ScrollView>
            <ChangePlantDivision 
                visibleChange={visibleChange}
                hideChange={hideChange}
                userPlants={userPlants}
                divisionID={divisionID}
                addPlant={handleAddPlant}
            />
            <RemovePlantDivision 
                visibleRemove={visibleRemove}
                hideRemove={hideRemove}
                userPlants={plantCards}
                divisionID={divisionID}
                addPlant={handleRemovePlant}
                deleteDivision={handleDeleteDivision}
            />
            <Divider bold={true}/>
        </View>
    )
}