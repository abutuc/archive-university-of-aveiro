import React, { useState, useEffect } from "react";

// Components
import ACPDetails from "./components/ACPDetails"
import BasicExample from "../../components/Navbar";
import ParcelsWaitingDeliveryTable from "./components/ParcelsWaitingDeliveryTable";
import ParcelsWaitingPickupTable from "./components/ParcelsWaitingPickupTable";
import ParcelsDeliveredTable from "./components/ParcelsDeliveredTable";
import EditableStatCard from "./components/EditableStatCard";
import ErrorModal from "../../components/ErrorModal";

// Services
import {
  getAllParcelsInDelivery,
  getAllParcelsWaitingPickup,
  getAllParcelsDelivered,
  getACPLimit,
  updateACPLimit,
  updateParcelStatus as updateParcelStatusService,
} from "../../services/operatorService";

import {
  getAcp,
} from "../../services/adminService";

export default function ACPOperator() {
  // Parcels
  const [parcelsWaitingDelivery, setParcelsWaitingDelivery] = useState([]);
  const [parcelsWaitingPickup, setParcelsWaitingPickup] = useState([]);
  const [parcelsDelivered, setParcelsDelivered] = useState([]);

  const [error, setError] = useState(null);
  const [showErrorModal, setShowErrorModal] = useState(false);
  const handleShowErrorModal = () => setShowErrorModal(true);
  const handleCloseErrorModal = () => setShowErrorModal(false);

  // ACP
  const acpId = 1;
  const [acpInfo, setACPInfo] = useState(null);
  const [acpLimit, setACPLimit] = useState(null);  

  const fetchParcels = async () => {
    // Parcels
    const parcelsInDelivery = await getAllParcelsInDelivery(acpId);
    const parcelsWaitingPickup = await getAllParcelsWaitingPickup(acpId);
    const parcelsDelivered = await getAllParcelsDelivered(acpId);

    setParcelsWaitingDelivery(parcelsInDelivery);
    setParcelsWaitingPickup(parcelsWaitingPickup);
    setParcelsDelivered(parcelsDelivered);

    // ACP
    const acpInfo = await getAcp(acpId);
    const acpLimit = await getACPLimit(acpId);

    setACPInfo(acpInfo);
    setACPLimit(acpLimit);
  }

  useEffect(() => {
    fetchParcels();
  }, []);

  // Update parcel status
  const updateParcelStatus = async (parcelID, code, status) => {
    const response = await updateParcelStatusService(parcelID, code, status);
    console.log(response)
    if (response.status == 200) {
      console.log("Parcel status updated");
      // Reload useEffect
      fetchParcels();
    } else {
      console.log("Parcel status update failed!");
      setError("Parcel status update failed!");
      handleShowErrorModal();
    }
  };

  const updateLimit = async (limit) => {
    const updatedLimit = await updateACPLimit(acpId, limit);
    setACPLimit(updatedLimit);
  }

  return (
    <>
      <BasicExample />
      <ACPDetails acp={acpInfo} />
      {acpLimit && <EditableStatCard title="Limit" stat={acpLimit} updateLimit={(limit) => updateLimit(limit)} />}
      <ParcelsWaitingDeliveryTable parcels={parcelsWaitingDelivery} updateStatus={(parcelID, code, status) => updateParcelStatus(parcelID, code, status)} />
      <ParcelsWaitingPickupTable parcels={parcelsWaitingPickup} updateStatus={(parcelID, code, status) => updateParcelStatus(parcelID, code, status)} />
      <ParcelsDeliveredTable parcels={parcelsDelivered} />
      <ErrorModal show={showErrorModal} handleClose={handleCloseErrorModal} error={error} />
    </>
  );
}
