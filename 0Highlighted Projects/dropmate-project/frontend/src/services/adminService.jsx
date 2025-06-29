import axios from "axios";

const adminUri = import.meta.env.VITE_DROPMATE_API_URI + '/admin';

// ACP RELATED METHODS

const getAllAcp = async () => {
    const response = await axios.get(adminUri + "/acp");
    return response.data;
};

const getAcp = async (id) => {
    const response = await axios.get(adminUri + "/acp/" + id);
    return response.data;
};

const deleteAcp = async (id) => {
    const response = await axios.delete(adminUri + "/acp/" + id);
    return response.data;
};

const getAllACPStatistics = async () => {
    const response = await axios.get(adminUri + "/acp/statistics");
    const data = response.data;
    let stats = [];
    Object.keys(data).forEach(key => {
        stats.push({
            acpId: key,
            parcels: data[key]
        });
    });
    return stats;
};

const getAllAcpRequests = async () => {
    const response = await axios.get(adminUri + "/acp/pending");
    return response.data;
};

const changeAcpRequestStatus = async (id, status) => {
    const response = await axios.put(adminUri + "/acp/pending/" + id + "/status" + "?newStatus=" + status);
    return response.data;
};

// PARCEL RELATED METHODS

const getParcelsInDelivery = async (id) => {
    const response = await axios.get(adminUri + "/parcels/" + id + "/delivery");
    return response.data;
};

const getParcelsWaitingPickup = async (id) => {
    const response = await axios.get(adminUri + "/parcels/" + id + "/pickup");
    return response.data;
};

const getAllParcelsInDelivery = async () => {
    const response = await axios.get(adminUri + "/parcels/all/delivery");
    return response.data;
};

const getAllParcelsWaitingPickup = async () => {
    const response = await axios.get(adminUri + "/parcels/all/pickup");
    return response.data;
};

// Estores

const getAllEstores = async () => {
    const response = await axios.get(adminUri + "/estores");
    return response.data;
};

export { 
    getAllAcp, 
    getAcp, 
    getParcelsInDelivery, 
    getParcelsWaitingPickup, 
    getAllACPStatistics,
    deleteAcp,
    getAllParcelsInDelivery,
    getAllParcelsWaitingPickup,
    getAllEstores,
    getAllAcpRequests,
    changeAcpRequestStatus
};
