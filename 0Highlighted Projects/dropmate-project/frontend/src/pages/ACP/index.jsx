import React, { useState, useEffect } from "react"
import { useParams } from "react-router-dom"

import BasicExample from "../../components/Navbar"

// Components
import ACPDetails from "./components/ACPDetails"
import Parcels from "./components/Parcels"

// Services
import { getAcp, getParcelsInDelivery, getParcelsWaitingPickup } from '../../services/adminService.jsx';

export default function ACP() {
    const { id } = useParams();
    const [acp, setACP] = useState();
    const [parcels, setParcels] = useState();

    useEffect(() => {
        async function fetchData() {
            const acp = await getAcp(id);
            const parcels = [];
            await getParcelsInDelivery(id).then(
                (response) => {
                    response.forEach((parcel) => {
                        parcels.push(parcel);
                    });
                }
            )
            await getParcelsWaitingPickup(id).then(
                (response) => {
                    response.forEach((parcel) => {
                        parcels.push(parcel);
                    });
                }
            )
            setACP(acp);
            setParcels(parcels);
        }
        fetchData();
    }, []);

    if (!acp) return (
        <>
            <BasicExample />
            <h1>Loading...</h1>
        </>
    )

    return (
        <>
            <BasicExample />
            <ACPDetails acp={acp}/>
            <Parcels parcels={parcels}/>
        </>
    )
}