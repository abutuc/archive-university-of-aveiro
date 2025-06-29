import axios from "axios";

const adminUri = import.meta.env.VITE_DROPMATE_API_URI + '/acp_api';

const getAllParcelsInDelivery = async (acpId) => {
    const response = await axios.get(`${adminUri}/parcel/all/delivery?acpID=${acpId}`);
    return response.data;
}

const getAllParcelsWaitingPickup = async (acpId) => {
    const response = await axios.get(`${adminUri}/parcel/all/pickup?acpID=${acpId}`);
    return response.data;
}

const getAllParcelsDelivered = async (acpId) => {
    const response = await axios.get(`${adminUri}/parcel/all/delivered?acpID=${acpId}`);
    return response.data;
}

const getACPLimit = async (acpId) => {
    const response = await axios.get(`${adminUri}/limit?acpID=${acpId}`);
    return response.data;
}

const updateACPLimit = async (acpId, limit) => {
    const response = await axios.put(`${adminUri}/limit?acpID=${acpId}&deliveryLimit=${limit}`);
    return response.data;
}

const updateParcelStatus = async (parcelID, code, status) => {
    let response;
    if (status === 'IN_DELIVERY') {
        response = await axios.put(`${adminUri}/parcel/${parcelID}/checkin?deliveryCode=${code}`)
        .catch((err) => {
            return err.response;
        })
        .then((res) => {
            return res;
        });
    } else if (status === 'WAITING_FOR_PICKUP') {
        response = await axios.put(`${adminUri}/parcel/${parcelID}/checkout?pickupCode=${code}`)
        .catch((err) => {
            return err.response;
        })
        .then((res) => {
            return res;
        });
    }
    return response;
}

export { 
    getAllParcelsInDelivery, 
    getAllParcelsWaitingPickup, 
    getAllParcelsDelivered,
    getACPLimit, 
    updateACPLimit,
    updateParcelStatus,
};