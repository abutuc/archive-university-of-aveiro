import axios from 'axios';

const userURI = import.meta.env.VITE_FLORALFIESTA_API_URI + '/user';

const register = async (user) => {
    const { name, email, password, phoneNumber, address } = user;
    const response = await axios.post(userURI + '/register?name=' + name + '&email=' + email + '&password=' + password + '&phoneNumber=' + phoneNumber + '&address=' + address)
    .catch((error) => {
        return error.response;
    })
    .then((response) => {
        return response;
    });
    return response;
}

const login = async (user) => {
    const { email, password } = user;
    const response = await axios.post(userURI + '/login?email=' + email + '&password=' + password)
        .catch((error) => {
            return error.response;
        })
        .then((response) => {
            return response;
        });
    return response;
}

export {
    register,
    login,
}