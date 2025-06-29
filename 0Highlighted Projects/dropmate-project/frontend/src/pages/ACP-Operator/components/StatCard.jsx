import React, {useState, useEffect} from 'react';

// Bootstrap
import Card from 'react-bootstrap/Card';

export default function StatCard({ title, stat }) {

    const [statValue, setStatValue] = useState(null);

    useEffect(() => {
        setStatValue(stat);
    }, [stat]);

    if (!stat) return (
        <Card>
            <Card.Body>
                <Card.Title>{title}</Card.Title>    
                <Card.Text>
                    Loading...
                </Card.Text>
            </Card.Body>
        </Card>
    )

    return (
        <Card>
            <Card.Body>
                <Card.Title>{title}</Card.Title>    
                <Card.Text>
                    {statValue}
                </Card.Text>
            </Card.Body>
        </Card>
    )
}