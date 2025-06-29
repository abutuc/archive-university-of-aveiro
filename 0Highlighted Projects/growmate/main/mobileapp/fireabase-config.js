// Import the functions you need from the SDKs you need
import { initializeApp } from 'firebase/app';
import { getStorage, ref } from "firebase/storage";

// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyCGXQOZd7YGCLf4t6IRWBIWMlwOEtAnx4g",
  authDomain: "growmate-59791.firebaseapp.com",
  projectId: "growmate-59791",
  storageBucket: "growmate-59791.appspot.com",
  messagingSenderId: "28075949794",
  appId: "1:28075949794:web:9e19ac29b54bd6e177a8bb",
  measurementId: "G-39NR2EFT59"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

const storage = getStorage(app);

export { storage };