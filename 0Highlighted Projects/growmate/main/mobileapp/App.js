import { Provider as PaperProvider, MD3LightTheme as DefaultTheme, } from 'react-native-paper'
import HomeScreen from "./screens/HomeScreen/HomeScreen"
import PlantScreen from "./screens/PlantScreen/PlantScreen";
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import myColors from "./colors"
import TasksScreen from "./screens/TasksScreen/TasksScreen";
import SpeciesProfileScreen from "./screens/SpeciesProfileScreen/SpeciesProfileScreen";

import AddPlantScreen from "./screens/AddPlantScreen/AddPlantScreen";
import AssociatePlantScreen from "./screens/AssociatePlantScreen/AssociatePlantScreen";

import 'react-native-gesture-handler';
import { gestureHandlerRootHOC } from 'react-native-gesture-handler';
import DiscoverPlantsScreen from "./screens/DiscoverPlantsScreen/DiscoverPlantsScreen";
import CategoryScreen from "./screens/CategoryScreen/CategoryScreen";

import { enGB, registerTranslation } from 'react-native-paper-dates'
import AddDivisionScreen from "./screens/AddDivisionScreen/AddDivisionScreen";
import AddSensorScreen from "./screens/AddSensorScreen/AddSensorScreen";
import InitializerScreen from "./screens/InitializerScreen/InitializerScreen";
import LoginScreen from "./screens/LoginScreen/LoginScreen";
import SignUpScreen from "./screens/SignUpScreen/SignUpScreen";
import SignUpScreenTwo from "./screens/SignUpScreenTwo/SignUpScreenTwo";
import ProfileScreen from "./screens/ProfileScreen/ProfileScreen";
registerTranslation('en-GB', enGB)

import GreenBar from './components/GreenBar';


const Stack = createNativeStackNavigator();

export default function App() {
  const theme = {
    ...DefaultTheme,
    colors: myColors
  }

  return (
    <PaperProvider theme={theme}>
      <GreenBar />
      <NavigationContainer>
        <Stack.Navigator
          screenOptions={{
            headerShown: false
          }}
        >
          <Stack.Screen
              name="Initializer"
              component={InitializerScreen}
          />
          <Stack.Screen
              name="SignUp"
              component={SignUpScreen}
          />
          <Stack.Screen
              name="SignUpTwo"
              component={SignUpScreenTwo}
          />
          <Stack.Screen
              name="Login"
              component={LoginScreen}
          />
          <Stack.Screen
            name="Home"
            component={gestureHandlerRootHOC(HomeScreen)}
          />
          <Stack.Screen
            name="Plant"
            component={gestureHandlerRootHOC(PlantScreen)}
          />
          <Stack.Screen
              name="Tasks"
              component={gestureHandlerRootHOC(TasksScreen)}
          />
          <Stack.Screen
              name="SpeciesProfile"
              component={gestureHandlerRootHOC(SpeciesProfileScreen)}
          />
          <Stack.Screen
              name="AddPlant"
              component={AddPlantScreen}
          />
          <Stack.Screen
              name="AssociatePlant"
              component={AssociatePlantScreen}
          />
          <Stack.Screen
              name="DiscoverPlants"
              component={DiscoverPlantsScreen}
          />
          <Stack.Screen
              name="Category"
              component={CategoryScreen}
          />
          <Stack.Screen name="AddDivision"
                        component={AddDivisionScreen}
          />
          <Stack.Screen name="AddSensor"
                        component={AddSensorScreen}
          />
          <Stack.Screen name="Profile"
                        component={ProfileScreen}
          />

        </Stack.Navigator>
      </NavigationContainer>
    </PaperProvider>
  );
}
