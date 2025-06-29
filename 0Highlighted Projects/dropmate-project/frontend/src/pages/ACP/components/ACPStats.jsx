import React, { useState, useEffect } from 'react';

// Bootstrap
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

// Components
import StatCard from './StatCard';

export default function ACPStats({ stats }) {
    return (
        <Row className="">
            <Col md={6} className='mb-3'>
                <StatCard title="Parcel Limit" subtitle="Total Parcels" stat={stats.parcel_limit} />
            </Col>
            <Col md={6}>
                <StatCard title="Parcels in Delivery" subtitle="Total Parcels" stat={stats.parcels_waiting_for_delivery} />
            </Col>
            <Col md={6}>
                <StatCard title="Parcels Waiting Pickup" subtitle="Total Parcels" stat={stats.parcels_waiting_for_pickup} />
            </Col>
            <Col md={6}>
                <StatCard title="Total Parcels" subtitle="Total Parcels" stat={stats.parcels_delivered} />
            </Col>
        </Row>
    )
}