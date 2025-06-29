import React, {useState, useEffect} from 'react';

// Bootstrap
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

// Components
import StatCard from './StatCard';

export default function ACPStats({ stats }) {
    const [statsValue, setStatsValue] = useState(null);

    useEffect(() => {
        setStatsValue(stats);
    }, [stats]);

    if (!statsValue) return (
        <Row>
            <Col md={6} className='mb-2'>
                <StatCard title="Parcels Waiting Delivery" stat={null} />
            </Col>
            <Col md={6}>
                <StatCard title="Parcels Waiting Pickup" stat={null} />
            </Col>
            <Col md={6}>
                <StatCard title="Parcels Delivered" stat={null} />
            </Col>
        </Row>
    )

    return (
        <Row>
            <Col md={6} className='mb-2'>
                <StatCard title="Parcels In Delivery" subtitle="Parcels in delivery" stat={statsValue.parcels_in_delivery} />
            </Col>
            <Col md={6}>
                <StatCard title="Parcels Waiting Pickup" subtitle="Parcels waiting pickup" stat={statsValue.parcels_waiting_pickup} />
            </Col>
            <Col md={6}>
                <StatCard title="Total Parcels" subtitle="Total Parcels" stat={statsValue.total_parcels} />
            </Col>
        </Row>
    )
}