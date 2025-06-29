import BottomMenu from "../../components/BottomMenu";
import { Dimensions, View, FlatList } from "react-native";
import React, { useState, useEffect } from "react";
import { useTheme, ActivityIndicator } from "react-native-paper";
import AddPlantHeader from "../AddPlantScreen/components/AddPlantHeader";
import PlantItem from "./components/PlantItem";
import {getCategorySpecies, getSuggestedSpecies} from "../../service/CategoryScreenService";
import {userID} from "../../user";

export default function CategoryScreen({ route }) {
  const screenHeight = Dimensions.get("screen").height;
  const { name, id, anonymous } = route.params;
  const theme = useTheme();

  const [species, setSpecies] = useState([]);
  const [paginatedSpecies, setPaginatedSpecies] = useState([]); // This is the species that will be rendered on the screen
  const [loading, setLoading] = useState(true);
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(10); // Number of species to render per page

  const loadMoreSpecies = () => {
    setPage(page + 1);
  };

  // This gets all the species for a category
  useEffect(() => {
    setLoading(true);
    if (id === "suggested") {
      getSuggestedSpecies(userID).then((species) => {
        setSpecies(species);
        setLoading(false);
      });
    }

    else {
      getCategorySpecies(id).then((species) => {
        setSpecies(species);
        setLoading(false);
      });
    }

  }, []);

  // This paginates the species
  useEffect(() => {
    setPaginatedSpecies(species.slice(0, page * pageSize));
  }, [page, species]);

  const renderPlantItem = ({ item }) => (
    <PlantItem
      key={item.id}
      name={item.commonName}
      image={item.speciesPhoto}
      difficulty={item.difficulty}
      anonymous={anonymous}
      speciesID={item.id}
      scientificName={item.scientificName}
    />
  );

  return (
    <View style={{ height: screenHeight, backgroundColor: theme.colors.background }}>
      <AddPlantHeader text={name} division={true}/>
      <FlatList
        data={paginatedSpecies}
        renderItem={renderPlantItem}
        keyExtractor={(item) => item.id.toString()}
        onEndReached={loadMoreSpecies}
        onEndReachedThreshold={0.5}
        ListFooterComponent={() => (loading ? <ActivityIndicator size="small" color={theme.colors.primary} /> : null)}
        maxToRenderPerBatch={10}
        initialNumToRender={10}
        removeClippedSubviews
      />
      <BottomMenu screenHeight={screenHeight} active={"magnify"} anonymous={anonymous} />
    </View>
  );
}
