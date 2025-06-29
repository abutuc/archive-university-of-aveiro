import React, { useState, useEffect } from 'react';

// Bootstrap
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Col from 'react-bootstrap/Col';
import Container from 'react-bootstrap/Container';

export default function EditableStatCard({ title, stat, updateLimit }) {

    const [isEditing, setIsEditing] = useState(true);

    const handleEdit = () => {
        setIsEditing(!isEditing);
    }

    const [newStat, setNewStat] = useState(stat);

    useEffect(() => {
        setNewStat(stat);
    }, [stat]);

    const handleSave = () => {
        updateLimit(newStat);
        setIsEditing(!isEditing);
    }

    return (
        <Container className='mb-5'>
            <Col md={3}>
                <Card>
                    <Card.Body>
                        <Card.Title>{title}</Card.Title>
                        <Form>
                            <Form.Group controlId="limitInput" className="mb-2">
                                <Form.Control type="number" placeholder="Enter new stat" value={newStat} onChange={(e) => setNewStat(e.target.value)} disabled={isEditing} />
                            </Form.Group>
                        </Form>
                        {isEditing ?
                            <Button size="sm" variant="primary" onClick={handleEdit} id="editLimit">Edit</Button>
                            :
                            <>
                                <Button size="sm" variant="primary" onClick={handleSave} id="saveLimit">Save</Button>
                                <Button size="sm" variant="danger" className="ms-2" onClick={handleEdit}>Cancel</Button>
                            </>
                        }
                    </Card.Body>
                </Card>
            </Col>
        </Container>
    )
}