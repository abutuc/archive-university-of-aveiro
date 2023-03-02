import React, { useState } from 'react';

import {
  Text,
  Image,
  ImageBackground,
  View,
  ScrollView,
  Pressable,
  Modal,
  TextInput,
} from 'react-native';

import FontAwesome from 'react-native-vector-icons/Ionicons';
import styles from './Styles';
import * as Constants from './Constants';

//import { Searchbar } from 'react-native-paper';
import SearchBar from 'react-native-dynamic-search-bar';
import { FlatList } from 'react-native-gesture-handler';
import FlashMessage from 'react-native-flash-message';
import  {showMessage, hideMessage} from "react-native-flash-message";
import SelectDropdown from 'react-native-select-dropdown';
import RadioGroup from 'react-native-radio-buttons-group';

export const HomeScreen = ( { navigation }) => {
    return (
      <View style={{backgroundColor: '#FFFFFF',flex:1}}>
      <ImageBackground source={require('./assets/veg_back.jpg')} resizeMode="cover" style={{width:'100%', height:'100%', justifyContent: 'center'}}>

      <ScrollView style={{flex: 1,  paddingBottom:540, paddingTop: 30}}>

      <View style={{justifyContent: 'center', alignItems:'center', paddingBottom: 90}}>
      <Image source={require('./assets/VeggieWorld.png')} />
      </View>


      <View style={{width: '100%', justifyContent: 'center', alignItems:'center'}}>
      <Image style={styles.circle} source={require('./assets/beautifulwoman.jpg')} />
      </View>

      <View>
      <Text style={styles.paragraph}>
        {Constants.Username}
      </Text>
      </View>

      <View style={styles.container_button1}>
        <Pressable style={styles.button} onPress={() => navigation.navigate('Veggie Eaters')}>
          <Text style={styles.text}>Veggie Eaters</Text>
        </Pressable>
      </View>

      <View style={styles.container_button2}>
        <Pressable style={styles.button} onPress={() => navigation.navigate('Recipes')}>
          <Text style={styles.text}>Recipes</Text>
        </Pressable>
      </View>
      </ScrollView>
      </ImageBackground>
      </View>
    )
  }

export const VeggieEaters = ( { navigation }) => {
  const veggieEaterImages = {
    arla: require('./assets/child1.jpg'),
    carlos: require('./assets/child2.jpg'),
  }

  const goToEspecificVeggieEater = (name, photoPath, veggieList) => {
    Constants.SelectedVeggieEater.name = name;
    Constants.SelectedVeggieEater.veggieEaterImagePath = photoPath;
    Constants.SelectedVeggieEater.VegetableList = veggieList;
    navigation.navigate('Veggie Eater');
  }
  return (
    <View style={{backgroundColor: '#FFFFFF',flex:1}}>
    <ImageBackground source={require('./assets/veg_back.jpg')} resizeMode="cover" style={{width:'100%', height:'100%', justifyContent: 'center'}}>
    <ScrollView style={{flex: 1, paddingBottom:540}}>
      
    <View style={styles.topbar}>
      <Pressable style={styles.backButton} onPress={() => navigation.navigate('Home')}>
      <FontAwesome name="md-arrow-back" size={25}/>
      </Pressable>
    </View>


    <View style={{justifyContent: 'center', alignItems:'center'}}>
          <Image source={require('./assets/VeggieEaters.png')} />
    </View>

    <View style={styles.veggieEaterButtonContainer}>
      <View style={styles.container_button1}>
        <Pressable style={styles.veggieEaterButton} onPress={() => goToEspecificVeggieEater('Arla', veggieEaterImages.arla, Constants.VegetableListArla)}>
          <Image style={styles.squareVeggieEater} source={veggieEaterImages.arla} />
        </Pressable>
        <Text style={styles.veggieEaterName}>Arla</Text>
      </View>
      <View style={{width:50}}>

      </View>
      <View style={styles.container_button2}>
        <Pressable style={styles.veggieEaterButton} onPress={() => goToEspecificVeggieEater('Carlos', veggieEaterImages.carlos, Constants.VegetableListCarlos)}>
          <Image style={styles.squareVeggieEater} source={veggieEaterImages.carlos} />
        </Pressable>
        <Text style={styles.veggieEaterName}>Carlos</Text>
      </View>
    </View>

    </ScrollView>
    <View style={styles.container_iconh}></View>
          <View style={styles.container_icon_home}>
            <View style={{paddingBottom: 15}}>
                    <Pressable style={styles.backButton} onPress={() => navigation.navigate('Home')}>
                          <FontAwesome name="md-home" size={25}/>
                    </Pressable>
                </View>
          </View>
    </ImageBackground>
    </View>
  )
  }

export const VeggieEater = ( { navigation }) => {
    const [modalOpen, setModalOpen] = useState(false);
    const [list, setList] = useState(Constants.SelectedVeggieEater.VegetableList);
    const [veggieUnits, setUnits] = useState(0);
    const [modalRecipeOpen, setModalRecipeOpen] = useState(false);


    const vegetableImages = {
        tomato: require('./assets/tomate-1.png'),
        carrot: require('./assets/carrot.png'),
        lettuce: require('./assets/lettuce.png'),
        potato: require('./assets/potato.png'),
        onion: require('./assets/cebola.png'),
        eggplant: require('./assets/eggplant.png'),
        beetroot: require('./assets/beetroot.png'),
        broccoli: require('./assets/broccoli.png'),
        chive: require('./assets/chive.png'),
        green_cab: require('./assets/green_cab.png'),
        beans: require('./assets/beans.png'),
        corn: require('./assets/corn.png'),
        raddish: require('./assets/radish.png'),
        kale: require('./assets/kale.png'),
        cucu: require('./assets/cucu.png'),
        garlic: require('./assets/garlic.png'),
        sweetpotato: require('./assets/sweetpotato.png'),
        pepper: require('./assets/pepper.png'),
        avocado: require('./assets/avocado.png'),
        olives: require('./assets/olives.png'),

    };

    const vegetableNames = {
        tomato: "Tomato",
        carrot: "Carrot",
        lettuce: "Lettuce",
        potato: "Potato",
        onion: "Onion",
        eggplant: "Eggplant",
        beetroot: "Beetroot",
        broccoli: 'Broccoli',
        chive: 'Chive',
        green_cab: 'Green cabbage',
        bean: 'Bean',
        corn: 'Corn',
        raddish: 'Radish',
        kale: 'Kale',
        cucu: 'Cucumber',
        garlic: 'Garlic',
        sweetpotato: 'Sweet Potato',
        peper: 'Pepper',
        avocado: 'Avocado',
        olives: 'Olives',
    };

    const vegetables = [
        {name: vegetableNames.tomato, imagePath: vegetableImages.tomato},
        {name: vegetableNames.carrot, imagePath: vegetableImages.carrot},
        {name: vegetableNames.lettuce, imagePath: vegetableImages.lettuce},
        {name: vegetableNames.potato, imagePath: vegetableImages.potato},
        {name: vegetableNames.onion, imagePath: vegetableImages.onion},
        {name: vegetableNames.eggplant, imagePath: vegetableImages.eggplant},
        {name: vegetableNames.beetroot, imagePath: vegetableImages.beetroot},
        {name: vegetableNames.broccoli, imagePath: vegetableImages.broccoli},
        {name: vegetableNames.chive, imagePath: vegetableImages.chive},
        {name: vegetableNames.green_cab, imagePath: vegetableImages.green_cab},
        {name: vegetableNames.bean, imagePath: vegetableImages.beans},
        {name: vegetableNames.corn, imagePath: vegetableImages.corn},
        {name: vegetableNames.raddish, imagePath: vegetableImages.raddish},
        {name: vegetableNames.kale, imagePath: vegetableImages.kale},
        {name: vegetableNames.cucu, imagePath: vegetableImages.cucu},
        {name: vegetableNames.garlic, imagePath: vegetableImages.garlic},
        {name: vegetableNames.sweetpotato, imagePath: vegetableImages.sweetpotato},
        {name: vegetableNames.peper, imagePath: vegetableImages.pepper},
        {name: vegetableNames.avocado, imagePath: vegetableImages.avocado},
        {name: vegetableNames.olives, imagePath: vegetableImages.olives},
    ];

    const [selectedVeggies, setSelectedVeggies] = useState(vegetables);

    const updateVegetable = (nam, val) => {
      // Find the item to update and store it in new list
      const op = [];
      let eli = list.map((item) => {if(item.name === nam){op.push(1)} return item});

      if (op.find(e => e == 1) == undefined){
        list.push({name: nam, value: val});
      }
      else {
        let el = list.map((item) => {if(item.name === nam){item.value=item.value + val} return item});
        setList(el)
      }
    };

    const addVegetable = (name, units) => {
      updateVegetable(name, parseInt(units));
      setModalOpen(false);
      showMessage({
        message: units + " units of " + name + " added sucessfully!",
        type: 'success',
      })
    }

    const openModalVegetable = (vegetableName, vegetableImagePath) => {
      Constants.SelectedVegetable.vegetableName = vegetableName;
      Constants.SelectedVegetable.vegetableImagePath = vegetableImagePath;
      setModalOpen(true);
    }

    const onSearchVeggie = (veggie) => {
      let newList = [];
      vegetables.map((item) => {if((item.name.toLowerCase()).includes(veggie.toLowerCase())){newList.push(item)}});
      setSelectedVeggies(newList);
    }

    const openRecipeModal = () => {
      setModalRecipeOpen(true);
    }
    const addRecipe = (recipe) => {
      recipe.recipeIngredients.map((item) => {addVegetable(item.name, item.quantity)});
      setModalRecipeOpen(false);
    }

    const removeItem = (itemName) => {
      let newList = [];
      list.map((item) => {newList.push(item)});
      list.map((item) => {if(item.name == itemName){
        newList.splice(newList.indexOf(item), 1);
        if (Constants.SelectedVeggieEater.name == 'Arla'){
          Constants.VegetableListArla.splice(newList.indexOf(item), 1);
        }
        else if (Constants.SelectedVeggieEater.name == 'Carlos'){
          Constants.VegetableListCarlos.splice(newList.indexOf(item), 1);
        }
      }})
      setList(newList);
    }

    const radioButtonsData = [{
      id: '1', // acts as primary key, should be unique and non-empty string
      label: 'Raw',
      value: 'raw'
    }, {
      id: '2',
      label: 'Cooked',
      value: 'cooked'
    }]

    const [radioButtons, setRadioButtons] = useState(radioButtonsData)

    const onPressRadioButton = (radioButtonsArray) => {
      setRadioButtons(radioButtonsArray);
    }


    return (
      <View style={{backgroundColor: '#FFFFFF',flex:1}}>
      <ImageBackground source={require('./assets/veg_back.jpg')} resizeMode="cover" style={{width:'100%', height:'100%', justifyContent: 'center'}}>
        <View style={styles.topbar}>
          <Pressable style={styles.backButton} onPress={() => navigation.navigate('Veggie Eaters')}>
          <FontAwesome name="md-arrow-back" size={25}/>
          </Pressable>
        </View>

        <View style={styles.singleVeggieEaterContainerImage}>
          <View style={{alignItems: 'center'}}>
            <View style={{width: '100%', justifyContent: 'center', alignItems:'center'}}>
              <Image style={styles.circleVeggie} source={Constants.SelectedVeggieEater.veggieEaterImagePath} />
            </View>
            <Text style={{paddingTop:16, fontSize: 18, color: 'black'}}>{Constants.SelectedVeggieEater.name}</Text>
          </View>
          <View style={{width:30}}>
          </View>
          <View style={{height: 105, flexShrink:1, borderLeftColor: 'gray', borderLeftWidth: 2, paddingLeft: 10}}>
          <Text style={{fontSize: 16, color: 'black'}}>Daily Progress:</Text>
          <FlatList data={list} persistentScrollbar={true} renderItem={({item}) => <View style={{flexDirection: 'row'}}><Text style={{fontSize: 15, color: 'black'}}> - {item.name + ' ' +  item.value}</Text><Pressable style={{paddingLeft: 25}} onPress={() => removeItem(item.name)}><FontAwesome name="trash-bin-outline" color={'black'} size={22}/></Pressable></View>}/>
          </View>
        </View>
        

        <View>
        <View style={{flexDirection: 'row', paddingTop: 6}}>
          <View style={styles.searchBarContainer}>
            <View>
              <Text style={{paddingBottom: 2, fontSize: 16, paddingLeft: 5, fontWeight: 'bold', color: 'white', backgroundColor: 'rgba(0, 0, 0, 0.22)', width: 165, borderWidth: 1, borderRadius: 6, borderColor: 'rgba(0, 0, 0, 0.22)' }}>Search for vegetable</Text>
            </View>
            <View style={{paddingLeft: 140}}>
            <SearchBar style={{width: 350, height: 50, borderColor: 'rgba(0, 0, 0, 0.12)', borderWidth: 1}} placeholder="Search here" fontSize={16} onChangeText={(text) => onSearchVeggie(text)} onClearPress={() => onSearchVeggie("")} />
            </View>
          </View>
          <View>
          </View>
        </View>

        <View style={{paddingTop: 25, height: 240}}>
        <View style={{alignItems: 'center'}}>
        <Text style={{paddingBottom: 10, fontSize: 18}}>Pick a vegetable</Text>
        </View>
        <View>
        <View style={{borderWidth: 1, borderColor: 'rgba(0, 0, 0, 0.12)', width: '100%', transparent: true, backgroundColor: 'rgba(80, 80, 80, 0.22)' ,height: 225, borderColor: 'rgba(80, 80, 80, 0.22)', borderWidth: 1, borderRadius: 20, paddingLeft: 10 }}>
        <FlatList data={selectedVeggies} persistentScrollbar={true} numColumns={4} style={{paddingTop: 10}} renderItem={({item}) => <View style={{width:90, alignItems:'center'}}>
              <Pressable style={styles.veggetableButton} onPress={() => openModalVegetable(item.name, item.imagePath)}>
                <Image style={styles.veggetableImages} source={item.imagePath} />
              </Pressable>
              <View>
              <Text style={{fontSize: 16, fontWeight: 'bold', color: 'white'}}>{item.name}</Text>
              </View>
            </View>}
        />
        </View>
        </View>
      </View>

      <Modal visible={modalOpen} transparent={true}>
        <View style={styles.modalContent}>
            <View style={styles.innerModal}>
              <Pressable onPress={() => setModalOpen(false)} style={{paddingLeft: 265}}>
                <FontAwesome name="md-close" size={25}/>
              </Pressable>
              <View style={{paddingLeft:20, flexDirection: 'row'}}>
              <Image style={styles.veggetableImage} source={Constants.SelectedVegetable.vegetableImagePath} />
              <Text style={{fontSize: 18, fontWeight: 'bold', paddingTop: 52, paddingLeft: 25}}>{Constants.SelectedVegetable.vegetableName}</Text>
              </View>

              <View style={{paddingLeft: 20, flexDirection: 'row', paddingTop: 30}}>
                <Text style={{paddingTop: 20, fontSize: 16, fontWeight: 'bold', paddingRight: 15}}>Weight</Text>
                <TextInput
                  style={{height: 40, width: 150,margin: 12, borderWidth: 1, padding: 10, backgroundColor: '#D3D3D3'}}
                  placeholder="Insert weight"
                  keyboardType="numeric"
                  onChangeText={(text) => setUnits(text)}/>
              </View>
              <View style={{paddingLeft: 20, flexDirection: 'row', paddingTop: 0}}>
                <Text style={{paddingTop: 20, fontSize: 16, fontWeight: 'bold', paddingRight: 40}}>State</Text>
                <RadioGroup radioButtons={radioButtons} containerStyle={{flexDirection: 'row', paddingTop: 18}} onPress={onPressRadioButton}/>
              </View>
              <View style={{paddingTop:30, paddingLeft: 90}}>
                <Pressable style={styles.buttonAddVegetable} onPress={() => addVegetable(Constants.SelectedVegetable.vegetableName,veggieUnits)}>
                  <Text style={{color: 'gray', fontSize: 14, fontWeight: 'bold'}}>Add Vegetable</Text>
                </Pressable>
              </View>
            </View>
        </View>
      </Modal>
      <Modal visible={modalRecipeOpen} transparent={true}>
        <View style={styles.modalContent}>
            <View style={styles.innerModal}>
              <Pressable onPress={() => setModalRecipeOpen(false)} style={{paddingLeft: 265}}>
                <FontAwesome name="md-close" size={25}/>
              </Pressable>
              <View style={{alignItems: 'center'}}>
                <Text style={{color: 'black', fontSize: 20}}>Favourite Recipes</Text>
              </View>
              <View style={{width: 300, height: 300}}>
                <FlatList data={Constants.FavouriteRecipes} persistentScrollbar={true} renderItem={({item}) => <View style={{paddingTop: 15}}>
                <View style={{flexDirection: 'row', paddingLeft: 20}}>
                  <Image style={styles.recipeImage} source={item.recipeImagePath} />
                  <Text style={{width: 130,paddingLeft: 5, color: 'black'}}>{item.recipeName}</Text>
                  <Pressable style={{paddingTop: 40}} onPress={() => addRecipe(item)}>
                    <FontAwesome name="md-add-circle-outline" size={30}/>
                  </Pressable>
                </View>
            </View>}
        />
        </View>
            </View>
        </View>
      </Modal>
      <FlashMessage position="bottom" titleStyle={{fontSize: 16}}/>

      <View style={styles.container_button1}>
        <Text style={{paddingBottom: 20, fontSize: 18, fontWeight: 'bold', paddingTop: 30}}>OR</Text>
        <Pressable style={styles.buttonAddRecipe} onPress={() => openRecipeModal()}>
          <Text style={styles.text}>Add Recipe</Text>
        </Pressable>
      </View>
      </View>
      <View style={styles.container_iconh}></View>
        <View style={styles.container_icon_home}>
          <View style={{paddingBottom: 15}}>
              <Pressable style={styles.backButton} onPress={() => navigation.navigate('Home')}>
                    <FontAwesome name="md-home" size={25}/>
              </Pressable>
          </View>
        </View>
      </ImageBackground>
      </View>
    )
  
  }

export const Recipes = ( { navigation }) => {
  const [modalOpen, setModalOpen] = useState(false);
  const [buttonName, setButtonName] = useState('md-heart-outline');
  const [favPipis, setFavPipis] = useState(Constants.favPipis);
  const [favGreen, setFavGreen] = useState(Constants.favGreen);
  const [favSesame, setFavSesame] = useState(Constants.favSesame);
  const [favSilver, setFavSilver] = useState(Constants.favSilver);
  const filters = ["All", "Most Recent", "Most Popular", "Most Rated", "Favourite Recipes"]

  const recipeTitles = {
    pipis: 'Pipis in spicy broth',
    green_noodes: 'Green Tea Noodles with sicky salmon',
    sesame_beef: 'Sesame beef with gochujang udon noodles',
    silver: "Silverbeet fatteh with sumac yoghurt and chickpeas"
  }
  const recipeImages = {
    pipis: require('./assets/recipe1.jpeg'),
    green_noodles: require('./assets/recipe2.jpeg'),
    sesame_beef: require('./assets/recipe3.jpeg'),
    silver: require('./assets/recipe4.jpeg'),
  }

  const recipesDescriptions = {
    pipis: 'Pipis in spicy broth\n\nMake the most of tomatoes with this summer pipi dish.',
    green_noodes: 'Green Tea Noddles with sticky salmon\n\nBring a pop of colour to this easy midweek dinner.',
    sesame_beef: 'Sesame beef with gochujang udon noodles\n\nMove over 2-minute noodles, this beef and udon dish will make your evening.',
    silver: 'Try this dish with roast cauliflower, eggplant or roast pumpkin instead of silverbeet for a variation, says Tom Walton. Begin this recipe 1 day ahead.'
  }

  const recipesContent = {
    pipis: "Ingredients:\n - 1/3 cup (80ml) vegetable oil\n - 4 eschalots, thinly sliced\n - 4 garlic cloves, thinly sliced\n - 1 lemongrass stalk, halved and bruised\n - 2 kaffir lime leaves\n - 5cm piece (25g) ginger, thinly sliced\n - 5cm piece (25g) galangal, finely grated\n - 1/2 bunch coriander, stems finely chopped, leaves picked and finely chopped\n - 1 long red chilli, finely chopped, plus extra to serve\n - 2 tbs fish sauce\n - 2 tbs sambal oelek\n - 500g ripe tomatoes, finely chopped\n - 200g grape tomatoes\n - 1kg pot-ready pipis, purged\n - Lime wedges, to serve \n\nMethod:\n1. Heat oil in a wok over high heat. Add eschalot, garlic, lemongrass, lime leaves, ginger, galangal, coriander stems and chilli, and cook for 5-6 minutes or until caramelised and fragrant. Add fish sauce, sambal oelek and tomatoes, and cook for 6-8 minutes until softened and juicy. Add the pipis, cover with a lid and cook for 6-7 minutes until the pipis have opened. Discard any unopened pipis. Serve topped with extra chilli, coriander leaves and lime wedges.",
    green_noodles: "Ingredients:\n - 1/2 cup (125ml) peanut oil\n - 1 tbs finely chopped ginger\n - 3 long green shallots, thinly sliced\n - 1 lemongrass stalk (white part only), finely grated\n - 1 1/2 tbs runny honey\n - 2 tbs extra virgin olive oil\n - 80g chilli paste in soybean oil\n - 600g whole skinless salmon fillet, pin-boned\n - 240g packet dried green tea noodles\n - 1/3 cup (80ml) lime juice\n - 2 1/2 tsp caster sugar\n - 2 tsp fish sauce\n - 1/2 tsp chilli flakes, plus extra to serve\n\nMethod:\n1. Preheat oven to 220°C. Heat peanut oil in a small saucepan over low heat. Add ginger, long green shallot, lemongrass and a pinch of salt. Cook, stirring occasionally, for 6-8 minutes until long green shallot is very soft but not coloured. Remove from heat and cool.\n2. Meanwhile, combine honey, olive oil and chilli paste in a bowl. Stir to combine. Line a baking tray with baking paper and add salmon. Rub honey mixture over salmon to coat, then season. Roast for 12-15 minutes for medium. Set aside, loosely covered with foil, to rest for 5 minutes.\n3. Cook noodles according to packet instructions. Drain and rinse briefly with warm water.\n4. Whisk lime juice, sugar, fish sauce and chilli flakes into the shallot oil mixture. Place noodles in a large bowl with three quarters of the shallot oil, season and toss to combine. Arrange on a serving platter and flake salmon over the top. Drizzle over remaining shallot oil and scatter with extra chilli flakes, toasted sesame seeds and shiso leaves. Serve at room temperature or chilled.",
    sesame_beef: "Ingredients:\n - 400g skirt beef steak\n - 2 tsp sesame oil\n - 400g udon noodles\n - 50g unsalted butter, chopped\n - 150g mixed Asian mushrooms, sliced\n - 1/4 cup gochujang (Korean fermented chilli paste)\n - 1 tbs tomato sauce\n - 120g baby spinach leaves\n - 1 bunch broccolini, cut in half on the diagonal, blanched\n - Chopped nori & toasted sesame seeds, to serve\n\nMethod:\n1. Place the skirt steak on a large oven tray, season with salt flakes and drizzle with the sesame oil. Leave steak to stand at room temperature for 30 minutes or until the fridge chill has gone.\n2.Cook the udon noodles according to packet instructions, reserving 1 cup (250ml) of noodle water.\n3.Heat a lightly greased barbecue or chargrill pan over high heat. Grill the steak for 3-4 minutes on each side until cooked to your liking. Remove from the heat and rest, loosely covered with foil, for 10-12 minutes.\n4.Meanwhile, heat half the butter in a large non-stick frypan over medium-high heat. Add the mushroom and cook for 2-3 minutes until golden. Remove and set aside. Add the gochujang and tomato sauce to the pan and cook, stirring, for 1-2 minutes until the gochujang is lightly caramelised. Add the noodle water and bring to a simmer, cooking for 2-3 minutes until the liquid is slightly reduced.\n5.Add the noodles, spinach and remaining butter, and stir until spinach is wilted and noodles are coated in the sauce.\n6.Divide noodles among serving plates, top with sliced steak, broccolini, mushroom and nori. Sprinkle with sesame seeds to serve.",
    silver: "Ingredientes:\n - 2 cups (400g) dried chickpeas, soaked overnight\n - 3 large pieces Lebanese bread, cut into 4 large triangles\n - 1/4 cup (60ml) extra virgin olive oil, plus extra to drizzle\n - 1/2 bunch silverbeet, stalks removed\n - 1 cup each mint leaves & flat-leaf parsley leaves, roughly chopped\n\nMethod:\n1. Place chickpeas in a medium saucepan and cover with cold water. Bring to the boil over high heat, reduce to medium and simmer for 40-45 minutes until cooked. Keep warm.\n2. Preheat oven to 180°C. Place Lebanese bread on a baking tray, drizzle with olive oil and sprinkle with extra za’atar. Bake for 10-12 minutes until golden and crisp.\n3. For the yoghurt dressing, place yoghurt, za’atar, garlic, sumac and lemon in a bowl. Season to taste and mix to combine.\n4. For the pomegranate dressing, place all the ingredients in a bowl, season to taste and stir to combine.\n5. Preheat a chargrill pan or barbecue to high. Drizzle silverbeet with extra oil, season and chargrill for 4-5 minutes until wilted and a little charred. Roughly chop and place in a bowl with chickpeas and herbs. Drizzle over pomegranate dressing, season and toss to combine.\n6. Place pita on a serving plate, spoon over yoghurt dressing and layer with silverbeet salad. Scatter over pine nuts and smoked trout to serve."
  }

  const recipesIngredients = {
    pipis: [{name: "Eschalot", quantity: 4}, {name: "Garlic", quantity: 4}, {name: "Tomato", quantity: 5}],
    green_noodes: [{name: "Shallot", quantity: 3}, {name: "Lemongrass", quantity: 1}, {name: "Chilli", quantity: 5}],
    sesame_beef: [{name: "Sesame Oil", quantity: 2}, {name: "Udon Noodles", quantity: 400}, {name: "Asian mushrooms", quantity: 150}, {name: "Gochujang", quantity: 0.25}, {name: "Tomato Sauce", quantity: 1}, {name: "Baby Spinach", quantity: 120}, {name: "Broccolini", quantity: 1}, {name: "Sesame Seeds", quantity: 10}],
    silver: [{name: "Chickpeas", quantity: 400}, {name: "Virgin Oil", quantity: 60}, {name: "Silverbeet", quantity: 200}, {name: "Mint leaves", quantity: 10}]
  }

  const MainList = {
    pipis: {recipeNumber: 1, recipeName: recipeTitles.pipis, recipeImagePath: recipeImages.pipis, recipeDescription: recipesDescriptions.pipis, recipeContent: recipesContent.pipis, recipeIngredients: recipesIngredients.pipis},
    green_noodes: {recipeNumber: 2, recipeName: recipeTitles.green_noodes, recipeImagePath: recipeImages.green_noodles, recipeDescription: recipesDescriptions.green_noodes, recipeContent: recipesContent.green_noodles, recipeIngredients: recipesIngredients.green_noodes},
    sesame_beef: {recipeNumber: 3, recipeName: recipeTitles.sesame_beef, recipeImagePath: recipeImages.sesame_beef, recipeDescription: recipesDescriptions.sesame_beef, recipeContent: recipesContent.sesame_beef, recipeIngredients: recipesIngredients.sesame_beef},
    silver: {recipeNumber: 4, recipeName: recipeTitles.silver, recipeImagePath: recipeImages.silver, recipeDescription:recipesDescriptions.silver, recipeContent: recipesContent.silver, recipeIngredients: recipesIngredients.silver}
  }

  const RecipesList = [MainList.pipis, MainList.green_noodes, MainList.sesame_beef, MainList.silver];

  const MostRecentList = [MainList.silver, MainList.sesame_beef, MainList.green_noodes, MainList.pipis];

  const MostPopularList = [MainList.green_noodes, MainList.pipis, MainList.sesame_beef, MainList.silver];

  const [favouritesList, setFavouritesList] = useState(Constants.FavouriteRecipes);

  const [list, setList] = useState(RecipesList);

  const openModalRecipe = (recipeNum, recipeName, recipeImagePath, recipeContent) => {
    Constants.SelectedRecipe.recipeName = recipeName;
    Constants.SelectedRecipe.recipeImagePath = recipeImagePath;
    Constants.SelectedRecipe.recipeContent = recipeContent;
    Constants.SelectedRecipe.recipeNumber = recipeNum;
    switch (recipeNum){
      case 1:
        setButtonName(favPipis);
        break;
      case 2:
        setButtonName(favGreen);
        break;
      case 3:
        setButtonName(favSesame);
        break;
      case 4:
        setButtonName(favSilver);
        break;
    }
    setModalOpen(true);
  }

  const onSelectFilter = (selectedItem, index) => {
    switch(selectedItem){
      case 'All':
        setList(RecipesList);
        break;

      case 'Most Recent':
        setList(MostRecentList);
        break;

      case 'Most Popular':
        setList(MostPopularList);
        break;

      case 'Most Rated':
        setList(RecipesList);
        break;
      
      case "Favourite Recipes":
        setList(favouritesList);
        break;
    }

  }

  const onSearchRecipe = (recipe) => {
    let newList = [];
    RecipesList.map((item) => {if((item.recipeName.toLowerCase()).includes(recipe.toLowerCase())){newList.push(item)}});
    setList(newList);
  }


  const toFavourites = () => {
    switch(Constants.SelectedRecipe.recipeNumber){
      case 1:
        if (favPipis == "md-heart-outline"){
          setFavPipis("md-heart")
          setButtonName("md-heart");
          favouritesList.push(MainList.pipis);
          setFavouritesList(favouritesList);
        }
        else {
          setFavPipis("md-heart-outline")
          setButtonName("md-heart-outline");
          favouritesList.splice(favouritesList.indexOf(MainList.pipis), 1);
          setFavouritesList(favouritesList);
        }
        break;

      case 2:
        if (favGreen == "md-heart-outline"){
          setFavGreen("md-heart")
          setButtonName("md-heart");
          favouritesList.push(MainList.green_noodes);
          setFavouritesList(favouritesList);
        }
        else {
          setFavGreen("md-heart-outline")
          setButtonName("md-heart-outline");
          favouritesList.splice(favouritesList.indexOf(MainList.green_noodes), 1);
          setFavouritesList(favouritesList);
        }
        break;
        
      case 3:
        if (favSesame == "md-heart-outline"){
          setFavSesame("md-heart")
          setButtonName("md-heart");
          favouritesList.push(MainList.sesame_beef);
          setFavouritesList(favouritesList);
        }
        else {
          setFavSesame("md-heart-outline")
          setButtonName("md-heart-outline");
          favouritesList.splice(favouritesList.indexOf(MainList.sesame_beef), 1);
          setFavouritesList(favouritesList);
        }
        break;
      case 4:
        if (favSesame == "md-heart-outline"){
          setFavSilver("md-heart")
          setButtonName("md-heart");
          favouritesList.push(MainList.silver);
          setFavouritesList(favouritesList);
          }
        else {
            setFavSilver("md-heart-outline")
            setButtonName("md-heart-outline");
            favouritesList.splice(favouritesList.indexOf(MainList.silver), 1);
            setFavouritesList(favouritesList);
          }

    }
  }

  const modalClose = () => {
  
    setModalOpen(false)
  }

  const [dropdownIconName, setDropDownIconName] = useState("chevron-down-outline")

  const dropdownButton = () => {
    return (<FontAwesome name={dropdownIconName} size={20}/>)
  }

  const onSelectOpen = () => {
    setDropDownIconName('chevron-up-outline')
  }
  const onSelectClose = () => {
    setDropDownIconName('chevron-down-outline')
  }

  const goBack = (screen) => {
    Constants.FavouriteRecipes = favouritesList;
    Constants.favPipis = favPipis;
    Constants.favGreen = favGreen;
    Constants.favSesame = favSesame;
    Constants.favSilver = favSilver;
    navigation.navigate(screen);
  }





  return (
    <View style={{backgroundColor: '#FFFFFF',flex:1}}>
    <ImageBackground source={require('./assets/veg_back.jpg')} resizeMode="cover" style={{width:'100%', height:'100%', justifyContent: 'center'}}>
        <View style={styles.topbar}>
          <Pressable style={styles.backButton} onPress={() => goBack("Home")}>
            <FontAwesome name="md-arrow-back" size={25}/>
          </Pressable>
        </View>
        <View style={{justifyContent: 'center', alignItems:'center'}}>
              <Image source={require('./assets/Recipes.png')} />
        </View>

        <View style={{flexDirection: 'row', paddingTop: 6}}>
          <View style={styles.searchBarContainer}>
            <View>
              <Text style={{paddingBottom: 2, fontSize: 16, paddingLeft: 5, fontWeight: 'bold', color: 'white', backgroundColor: 'rgba(0, 0, 0, 0.25)', width: 140, borderWidth: 1, borderRadius: 6, borderColor: 'rgba(0, 0, 0, 0.22)' }}>Search for recipe</Text>
            </View>
            <View style={{paddingLeft:140}}>
            <SearchBar style={{width: 350, height: 50, borderColor: 'rgba(0, 0, 0, 0.12)', borderWidth: 1}} placeholder="Search here" fontSize={16} onChangeText={(text) => onSearchRecipe(text)} onClearPress={() => onSearchRecipe("")} />
            </View>
          </View>
        </View>

        <View style={{flexDirection: 'row'}}>
          <View style={styles.searchBarContainer}>
            <View>
            <Text style={{paddingBottom: 2, fontSize: 16, paddingLeft: 5, fontWeight: 'bold', color: 'white', backgroundColor: 'rgba(0, 0, 0, 0.25)', width: 100, borderWidth: 1, borderRadius: 6, borderColor: 'rgba(0, 0, 0, 0.22)' }}>Filter recipe:</Text>
            </View>
            <SelectDropdown buttonStyle={{width: 350, borderWidth:1, borderRadius: 8, borderColor: 'rgba(0, 0, 0, 0.12)'}} data={filters} dropdownIconPosition="right" renderDropdownIcon={dropdownButton} onFocus={onSelectOpen} onBlur={onSelectClose} buttonTextStyle={{fontSize: 16}} onSelect={(selectedItem, index) => onSelectFilter(selectedItem, index)}/>
          </View>
        </View>

        <View style={{paddingLeft: 30, paddingTop: 30, height: 370}}>
        <Text style={{paddingBottom: 2, fontSize: 16, paddingLeft: 5, fontWeight: 'bold', color: 'white', backgroundColor: 'rgba(0, 0, 0, 0.25)', width: 110, borderWidth: 1, borderRadius: 6, borderColor: 'rgba(0, 0, 0, 0.22)' }}>Pick a recipe:</Text>
          <View style={{height: 320}}>
          <FlatList data={list} renderItem={({item}) => <View style={{paddingTop: 15}}>
                <Pressable style={styles.recipesButton} onPress={() => openModalRecipe(item.recipeNumber, item.recipeName, item.recipeImagePath, item.recipeContent)}>
                <View style={{flexDirection: 'row'}}>
                <View style={{borderWidth:1, borderColor: 'rgba(0,0,0,0.2)'}}>
                <Image style={styles.recipeImage} source={item.recipeImagePath} />
                </View>
                <View style={{width: 250}}>
                <Text style={{flexShrink:1, paddingLeft: 5, color: 'black', fontWeight: 'bold'}}>{item.recipeDescription}</Text>
                </View>
                </View>
                </Pressable>
            </View>}/>
          </View>
        </View>

      <Modal visible={modalOpen} transparent={true}>
        <View style={styles.modalContentRecipe}>
            <View style={styles.innerModalRecipe}>
            <ScrollView>
              <View style={{flexDirection:'row'}}>
                <Pressable style={{paddingTop:5, paddingLeft:5}} onPress={() => toFavourites()}>
                  <FontAwesome name={buttonName} size={25} color='gray'/>
                </Pressable>
                <Pressable onPress={() => modalClose()} style={{paddingLeft: 280, paddingTop: 5}}>
                  <FontAwesome name="md-close" size={25}/>
                </Pressable>
              </View>
              <View style={{alignItems: 'center'}}>
              <Image style={styles.recipeFullImage} source={Constants.SelectedRecipe.recipeImagePath} />
              </View>
              <View style={{alignItems: 'center', paddingTop: 15}}>
                <Text style={{fontSize: 18, fontWeight: 'bold'}}>{Constants.SelectedRecipe.recipeName}</Text>
                <Text>Difficulty: Easy | 30 minutes</Text>
              </View>
                <View style={{paddingTop: 15, paddingLeft: 15}}>
                  <Text>
                    {Constants.SelectedRecipe.recipeContent}
                  </Text>
                </View>
              </ScrollView>
            </View>
        </View>
      </Modal>

       <View style={styles.container_iconh}>
          <View style={styles.container_icon_home}>
            <View style={{paddingBottom: 10}}>
                <Pressable style={styles.backButton} onPress={() => navigation.navigate('Home')}>
                      <FontAwesome name="md-home" size={25}/>
                </Pressable>
            </View>
          </View>
        </View>

    </ImageBackground>
    </View>
  )
}