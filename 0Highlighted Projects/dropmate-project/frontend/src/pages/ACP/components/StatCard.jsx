// Bootstrap
import Card from 'react-bootstrap/Card';

export default function StatCard({ title, stat }) {
    return (
        <Card>
            <Card.Body>
                <Card.Title>{title}</Card.Title>    
                <Card.Text id={title}>
                    {stat}
                </Card.Text>
            </Card.Body>
        </Card>
    )
}