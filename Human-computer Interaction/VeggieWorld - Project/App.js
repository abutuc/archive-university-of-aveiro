import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import * as Pages from './Pages';



const Stack = createNativeStackNavigator();
 
function App (){
  return (
    <NavigationContainer>
      <Stack.Navigator screenOptions={{headerShown: false}}>
        <Stack.Screen name="Home" component={Pages.HomeScreen}/>
        <Stack.Screen name="Veggie Eaters" component={Pages.VeggieEaters}/>
        <Stack.Screen name="Veggie Eater" component={Pages.VeggieEater}/>
        <Stack.Screen name="Recipes" component={Pages.Recipes}/>
      </Stack.Navigator>
    </NavigationContainer>
  )
}
export default App;