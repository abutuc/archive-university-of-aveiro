let userID = "";
let userFirstName = "";
let userType = "";
let userPhoto = null;
let userName = "";
let userRating = 0;
let userEmail = "";
let userDateOfBirth = "";
let userExperience = "";
let userDeadPlants = "";
let userAddress = "";

const setUserID = (id) => {
    userID = id;
};

const setUserFirstName = (firstName) => {
    userFirstName = firstName;
};

const setUserName = (name) => {
    userName = name;
}

const setUserType = (type) => {
    userType = type;
};

const setUserPhoto = (photo) => {
    userPhoto = photo;
}

const setUserRating = (rating) => {
    userRating = rating;
}

const setUserEmail = (email) => {
    userEmail = email;
}

const setUserDateOfBirth = (dateOfBirth) => {
    userDateOfBirth = dateOfBirth;
}

const setUserExperience = (experience) => {
    userExperience = experience;
}

const setUserDeadPlants = (deadPlants) => {
    userDeadPlants = deadPlants;
}

const setUserAddress = (address) => {
    userAddress = address;
}


export { userID, setUserID, userFirstName, setUserFirstName, userType, setUserType, userPhoto, setUserPhoto,
    userName, setUserName, setUserAddress, setUserRating, setUserDateOfBirth, setUserEmail, setUserDeadPlants,
    setUserExperience, userRating, userEmail, userDateOfBirth, userExperience, userDeadPlants, userAddress};
