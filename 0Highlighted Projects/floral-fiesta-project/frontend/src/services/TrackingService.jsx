import axios from 'axios';

const orderURI = import.meta.env.VITE_FLORALFIESTA_API_URI + '/order';

const getOngoingOrders = async (userId) => {
    const response = await axios.get(`${orderURI}/ongoing/${userId}`);
    return response.data;
};

const getDeliveredOrders = async (userId) => {
    const response = await axios.get(`${orderURI}/delivered/${userId}`);
    return response.data;
};

export {
    getOngoingOrders,
    getDeliveredOrders,
}