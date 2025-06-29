import { useState } from 'react';
import { View, ScrollView } from 'react-native';
import { Button, Dialog, Portal, Text, useTheme, Checkbox, Avatar, Searchbar } from 'react-native-paper';

export default function RemovePlantDivision ({ visibleRemove, hideRemove, userPlants, divisionID, addPlant, deleteDivision }) {
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
                <Dialog visible={visibleRemove} onDismiss={hideRemove} style={{ backgroundColor: theme.colors.background }}>
                    <Dialog.Title>Remove plants from this division</Dialog.Title>
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
                                    .filter((plant) => plant.division["id"] === divisionID)
                                    .filter((plant) => searchQuery === '' || plant.name.toLowerCase().includes(searchQuery.toLowerCase()))
                                    .map((plant) => {
                                        const isChecked = checks.includes(plant.id);
                                        return (
                                            <View style={{ flexDirection: "row", alignItems: "center", justifyContent: "space-between", marginVertical: 10 }} key={plant.id}>
                                                <View style={{ flexDirection: "row", alignItems: "center" }}>
                                                    <Avatar.Image source={ { uri: plant.plantPhoto} } size={35} style={{ marginRight: 10 }} />
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
                    <Dialog.Actions style={{ flexDirection: "row", justifyContent: "space-between"}}>
                        <Button onPress={deleteDivision} buttonColor={theme.colors.darkRed}  textColor={theme.colors.background}>Delete Division</Button>
                        <View style={{ flexDirection: "row" }}>
                            <Button onPress={hideRemove} buttonColor={theme.colors.outline} textColor={theme.colors.background} style={{marginRight: 10}}>Cancel</Button>
                            <Button onPress={handleAddPlant} buttonColor={theme.colors.primary} textColor={theme.colors.background}>Remove</Button>
                        </View>
                    </Dialog.Actions>
                </Dialog>
            </Portal>
        </View>
    )
}