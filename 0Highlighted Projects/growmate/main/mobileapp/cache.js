import AsyncStorage from "@react-native-async-storage/async-storage";

const setItem = async (key, value) => {
    // ttl one day
    const ttlInSeconds = 86400;
     
    try {
        const item = {
            value,
            expiresAt: Date.now() + ttlInSeconds * 1000
        };

        const jsonValue = JSON.stringify(item);
        await AsyncStorage.setItem(key, jsonValue);
    } catch (e) {
        console.log(e);
    }
};

const getItem = async (key) => {
    try {
        const jsonValue = await AsyncStorage.getItem(key);
        if (jsonValue != null) {
            const item = JSON.parse(jsonValue);
            if (item.expiresAt > Date.now()) {
                return item.value;
            } else {
                await AsyncStorage.removeItem(key);
            }
        }
        return null;
    } catch (e) {
        console.log(e);
    }
};

const removeItem = async (key) => {
    try {
        await AsyncStorage.removeItem(key);
    } catch (e) {
        console.log(e);
    }
};

const clear = async () => {
    try {
        await AsyncStorage.clear();
    } catch (e) {
        console.log(e);
    }
};              

export { setItem, getItem, removeItem, clear };