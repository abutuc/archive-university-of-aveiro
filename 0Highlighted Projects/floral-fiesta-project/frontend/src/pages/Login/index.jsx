import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

// Components
import BasicExample from "../../components/Navbar";
import LoginForm from "./components/LoginForm";

// Bootstrap
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Alert from "react-bootstrap/Alert";

// Services
import {
    login,
} from '../../services/AuthService';

export default function Login() {
    const navigate = useNavigate();
    const [error, setError] = useState(null);
    const [showError, setShowError] = useState(false);

    const handleLogin = async (email, password) => {
        const user = {
            email: email,
            password: password,
        };

        const response = await login(user);
        if (response.status === 200) {
            console.log(response.data);
            localStorage.setItem('user', JSON.stringify(response.data));
            navigate('/');
            
        } else {
            console.log(response.data);
            setError(response.data);
            setShowError(true);
        }
    }

    return (
        <>
            <BasicExample />
            <Container className="mt-5">
                <Row className="justify-content-md-center">
                    <Col md={6}>
                        {showError && <Alert variant="danger" onClose={() => setShowError(false)} dismissible>{error.message}</Alert>}
                    </Col>
                </Row>
                <Row className="justify-content-md-center">
                    <Col md={6}>
                        <LoginForm handleLogin={(email, password) => handleLogin(email, password)}/>
                    </Col>
                </Row>
            </Container>
        </>
    )
}