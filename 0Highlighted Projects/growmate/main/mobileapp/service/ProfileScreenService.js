import axios from "axios";
import {getItem, setItem} from "../cache";
const baseURL = "http://13.80.159.172/growmate"

const getUserStats = async (userID) => {
    const stats = {};
    stats["divisions"] = await axios.get(baseURL + "/user/" + userID + "/divisions").then((response) => {return response.data.length});
    stats["plants"] = await axios.get(baseURL + "/user/" + userID + "/plants").then((response) => {return response.data.length});
    return stats;

}

const editProfile = async (userID, name, email, dateOfBirth, address) => {
    await axios.put(baseURL + "/user/" + userID + "/editProfile?name=" + name +
    "&email=" + email + "&dateOfBirth=" + dateOfBirth + "&address=" + address);

    // Update cache
    const cacheKey = "login";
    const cacheData = await getItem(cacheKey);
    if (cacheData !== null) {
        cacheData["name"] = name;
        cacheData["email"] = email;
        cacheData["dateOfBirth"] = dateOfBirth;
        cacheData["address"] = address;
        await setItem(cacheKey, cacheData);
    }
}

const getTasksLog = async (userID) => {
    const response = await axios.get(baseURL + "/user/tasks/" + userID + "/done");
    return response.data;
}

const changePassword = async (userID, oldPassword, newPassword) => {
    const response = await axios.put(baseURL + "/user/" + userID + "/changePassword?oldPassword=" + oldPassword
    + "&newPassword=" + newPassword).catch(() => {return false});

    if (response.status === 200){
        return response.data;
    }
    else {
        return false;
    }
}

export {getUserStats, editProfile, getTasksLog, changePassword}