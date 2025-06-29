import React, { useEffect, useState } from 'react';

import Container from 'react-bootstrap/Container';

// Components
import ACPTable from './components/ACPTable.jsx';
import Parcels from './components/Parcels.jsx';
import PartnerTable from './components/PartnerTable.jsx';
import ACPRequestTable from './components/ACPRequestTable.jsx';
import ACPStatisticsTable from './components/ACPStatisticsTable.jsx';

// Services
import {
    getAllAcp,
    getAllACPStatistics,
    deleteAcp,
    getAllParcelsInDelivery,
    getAllParcelsWaitingPickup,
    getAllEstores,
    getAllAcpRequests,
    changeAcpRequestStatus
} from '../../services/adminService.jsx';

export default function Dashboard() {
    const [acps, setACP] = useState([]);
    const [parcels, setParcels] = useState([]);
    const [partners, setPartners] = useState([]);
    const [requests, setRequests] = useState([]);
    const [stats, setStats] = useState([]);

    useEffect(() => {
        async function fetchData() {
          const acp = await getAllAcp();
          const stats = await getAllACPStatistics();
          const getParcelsWaitingPickup = await getAllParcelsWaitingPickup();
          const getParcelsInDelivery = await getAllParcelsInDelivery();
          const requests = await getAllAcpRequests();
          
          const parcels = getParcelsWaitingPickup.concat(getParcelsInDelivery);
          const partners = await getAllEstores();
          
          setACP(acp);
          setStats(
            stats.map(stat => {
              const matchingAcp = acp.find(acp => acp.acpId == stat.acpId);
              if (matchingAcp) {
                stat.acpName = matchingAcp.name;
              }
              return stat;
            })
          );
          setParcels(parcels);
          setPartners(partners);
          setRequests(requests);
        }
      
        fetchData();
      
      }, []);      

    const deleteACP = async (id) => {
        await deleteAcp(id);
        setACP(acps.filter(acp => acp.acpId !== id));
    };

    const changeAcpStatus = async (id, status) => {
        await changeAcpRequestStatus(id, status);

        // Change status of request
        setRequests(requests.map(request => {
            if (request.acpId == id) {
                request.status = status;
            }
            return request;
        }));
    };

    return (
        <>
            <Container>
                <ACPTable acps={acps} deleteACP={(id) => deleteACP(id)} />
                <ACPStatisticsTable stats={stats} />
                <ACPRequestTable requests={requests} changeAcpStatus={(id, status) => changeAcpStatus(id, status)}/>
                <Parcels parcels={parcels} />
                <PartnerTable partners={partners} />
            </Container>
        </>
    )
}