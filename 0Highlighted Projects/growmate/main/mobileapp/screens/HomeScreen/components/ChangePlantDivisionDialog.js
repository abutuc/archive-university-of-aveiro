import { useState } from 'react';
import { View, ScrollView } from 'react-native';
import { Button, Dialog, Portal, Text, useTheme, Checkbox, Avatar, Searchbar } from 'react-native-paper';

export default function ChangePlantDivision({ visibleChange, hideChange, userPlants, divisionID, addPlant }) {
    const theme = useTheme();

    const [checks, setChecks] = useState([]);

    const handleCheckboxChange = (plantId) => {
        setChecks((prevChecks) => {
            if (prevChecks.includes(plantId)) {
                return prevChecks.filter((id) => id !== plantId);
            } else {
                return [...prevChecks, plantId];
            }
        });
    };

    const [searchQuery, setSearchQuery] = useState('');

    const handleAddPlant = () => {
        const checkedPlants = userPlants.filter((plant) => checks.includes(plant.id));
        addPlant(checkedPlants, divisionID);
    }

    return (
        <View>
            <Portal>
                <Dialog visible={visibleChange} onDismiss={hideChange} style={{ backgroundColor: theme.colors.background }}>
                    <Dialog.Title>Add plants to this division</Dialog.Title>
                    <Dialog.Content>
                        <Searchbar
                            placeholder="Search"
                            onChangeText={setSearchQuery}
                            value={searchQuery}
                            style={{ marginVertical: 15 }}
                        />
                        <Dialog.ScrollArea>
                            <ScrollView contentContainerStyle={{ marginVertical: 15 }}>
                                {userPlants
                                    .filter((plant) => !plant.division || plant.division["id"] != divisionID)
                                    .filter((plant) => searchQuery === '' || plant.name.toLowerCase().includes(searchQuery.toLowerCase()))
                                    .map((plant) => {
                                        const isChecked = checks.includes(plant.id);
                                        return (
                                            <View style={{ flexDirection: "row", alignItems: "center", justifyContent: "space-between", marginVertical: 10 }} key={plant.id}>
                                                <View style={{ flexDirection: "row", alignItems: "center" }}>
                                                    <Avatar.Image source={ {uri: plant.plantPhoto}} size={35} style={{ marginRight: 10 }} />
                                                    <Text variant='bodyLarge'>{plant.name}</Text>
                                                </View>
                                                <Checkbox
                                                    status={isChecked ? 'checked' : 'unchecked'}
                                                    onPress={() => handleCheckboxChange(plant.id)}
                                                />
                                            </View>
                                        )
                                    })}
                            </ScrollView>
                        </Dialog.ScrollArea>
                    </Dialog.Content>
                    <Dialog.Actions>
                        <Button onPress={hideChange} buttonColor={theme.colors.darkRed} textColor={theme.colors.background}>Cancel</Button>
                        <Button onPress={handleAddPlant} buttonColor={theme.colors.primary} textColor={theme.colors.background}>Add</Button>
                    </Dialog.Actions>
                </Dialog>
            </Portal>
        </View>
    )
}  