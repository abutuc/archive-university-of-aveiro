import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

// Components
import BasicExample from "../../components/Navbar";
import RegisterForm from "./components/RegisterForm";

// Bootstrap
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

// Services
import { register } from '../../services/AuthService';

export default function Register() {
    const navigate = useNavigate();

    const handleRegister = async (user) => {
        const response = await register(user);
        console.log(response)
        if (response.status === 200) {
            navigate('/login');
        } else {
            alert('Error registering user');
        }
    }

    return (
        <>
            <BasicExample />
            <Container className="mt-5">
                <Row className="justify-content-md-center">
                    <Col md={6}>
                        <RegisterForm handleRegister={(user) => handleRegister(user)}/>
                    </Col>
                </Row>
            </Container>
        </>
    )
}