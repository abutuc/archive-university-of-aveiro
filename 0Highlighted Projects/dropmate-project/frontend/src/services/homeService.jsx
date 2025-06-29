import axios from "axios";

const operatorURI = import.meta.env.VITE_DROPMATE_API_URI + '/acp_api';
const adminURI = import.meta.env.VITE_DROPMATE_API_URI + '/admin';

const loginOperator = async (email, password) => {
    const response = await axios.post(`${operatorURI}/login?email=${email}&password=${password}`)
        .catch((error) => {
            return error.response;
        })
        .then((response) => {
            return response;
        }
    );
    return response;
};

const loginAdmin = async (email, password) => {
    const response = await axios.post(`${adminURI}/login?email=${email}&password=${password}`)
        .catch((error) => {
            return error.response;
        })
        .then((response) => {
            return response;
        }
    );
    return response;
};

const acpRequest = async (request) => {
    const response = await axios.post(`${adminURI}/acp/pending?name=${request.name}&email=${request.email}&telephoneNumber=${request.phone}&city=${request.city}&address=${request.address}&description=${request.description}`)
        .catch((error) => {
            return error.response;
        })
        .then((response) => {
            return response;
        }
    );
    return response;
};

export {
    loginOperator,
    loginAdmin,
    acpRequest,
}