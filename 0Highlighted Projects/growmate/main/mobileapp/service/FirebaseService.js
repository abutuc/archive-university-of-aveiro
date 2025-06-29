import { uploadBytes, ref, getDownloadURL, deleteObject } from "firebase/storage";
import { storage } from "../fireabase-config";


const uploadImage = async (imageUri, userID, plantName) => {
    const img = await fetch(imageUri);
    const bytes = await img.blob();

    const storageRef = ref(storage, userID + plantName);

    await uploadBytes(storageRef, bytes);

    const downloadUrl = await getDownloadURL(storageRef);

    return downloadUrl;
}

const deleteImage = async (userID, plantName) => {
    const storageRef = ref(storage, userID + plantName);

    deleteObject(storageRef).then(() => {
        console.log("DELETED " + plantName);
    }).catch((error) => {
        console.log(error);
    })
}
  

export { uploadImage, deleteImage };