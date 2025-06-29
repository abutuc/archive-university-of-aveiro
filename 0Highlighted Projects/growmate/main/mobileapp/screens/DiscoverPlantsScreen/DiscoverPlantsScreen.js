import BottomMenu from "../../components/BottomMenu";
import {Dimensions, View} from "react-native";
import React, {useState, useEffect} from "react";
import {ActivityIndicator, useTheme} from "react-native-paper";
import DiscoverNewPlantsHeader from "./components/DiscoverNewPlantsHeader";
import DiscoverNewPlantsSearchBar from "./components/DiscoverNewPlantsSearchBar";
import Categories from "./components/Categories";
import {getCategories} from "../../service/DiscoverPlantsScreenService";

export default function DiscoverPlantsScreen({route}){
    const {anonymous} = route.params;
    const [categories, setCategories] = useState([]);

    const [loadingCategories, setLoadingCategories] = useState(true);
    useEffect( () => {
        getCategories().then((categories) => {
            if (!anonymous){
                categories.unshift({name: "Suggested", photo: require("../../assets/plant.png"), id: "suggested"});
            }
            setCategories(categories);
            setLoadingCategories(false);
        });
    }, []);

    const screenHeight = Dimensions.get('screen').height;
    const theme = useTheme()
    const [hideCategories, setHideCategories] = useState(false)

    if (loadingCategories) {
        return (
            <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
                <ActivityIndicator size="large" />
            </View>
        );
    }
    else {
        return (
            <View style={{height: screenHeight, backgroundColor: theme.colors.background}}>
                <DiscoverNewPlantsHeader/>
                <DiscoverNewPlantsSearchBar anonymous={anonymous} setHideCategories={setHideCategories}/>
                {!hideCategories &&
                    <Categories categories={categories} anonymous={anonymous}/>
                }
                <BottomMenu screenHeight={screenHeight} active={"magnify"} anonymous={anonymous}/>
            </View>
        )
    }
}