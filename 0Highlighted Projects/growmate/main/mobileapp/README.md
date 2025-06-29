# Mobile App

For development purposes:
- install Node.js
- afterwards, install the expo-cli package globally:

```npm install --global expo-cli```

To run the project, it is possible to do so as:

```
expo start --android
```
```
expo start --ios
```

```
expo start --web
```

The first two require some sort of emulator, whereas the third one 
can be ran in any browser.

## Log of "npm install" dependencies
For better management and tracking of all manually installed dependencies, 
we have registered a log of all the react native modules installed.

- react-native-web@~0.18.11
- @react-navigation/native
- @react-navigation/native-stack
- react-dom
- react-native-paper
- react-native-safe-area-context
- react-native-vector-icons
- react-native-community/blur 
- depcheck -g

## Build APK

```
eas build -p android --profile preview
```