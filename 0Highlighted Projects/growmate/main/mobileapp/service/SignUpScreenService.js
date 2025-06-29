import axios from "axios";
const baseURL = "http://13.80.159.172/growmate/public/register?"

const signUp = async (name, email, password, profilePhoto, dateOfBirth, experience, address, userType) => {
    const response = await axios.post(baseURL + "name=" + name +
                                        "&email=" +email + "&password=" + password +
                                        "&profilePhoto=" + profilePhoto + "&dateOfBirth=" + dateOfBirth +
                                        "&experience=" + experience + "&address=" + address + "&userType=" + userType)
        .catch(error => {
            return 0;
        });

    if (response.status === 200){
        return 1;
    }
    else {
        return 0;
    }
}

export {signUp}

