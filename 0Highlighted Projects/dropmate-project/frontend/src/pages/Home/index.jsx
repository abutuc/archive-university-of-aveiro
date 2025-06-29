import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

// Componenets
import BasicExample from '../../components/Navbar';
import Login from './components/Login';
import Request from './components/Request';
import ErrorModal from '../../components/ErrorModal';

// Bootstrap
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

//Services
import {
    loginOperator,
    loginAdmin,
    acpRequest,
} from '../../services/homeService';

export default function Home() {
    const navigate = useNavigate();

    const [name, setName] = useState('');
    const [error, setError] = useState(null);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const handleShowErrorModal = () => setShowErrorModal(true);
    const handleCloseErrorModal = () => setShowErrorModal(false);

    const handleLogin = async (email, password, admin) => {
        if (admin) {
            console.log('Admin Login');
            const response = await loginAdmin(email, password);
            console.log(response);
            if (response.status === 200) {
                console.log('Login Success');
                // Set Log in Local Storage
                localStorage.setItem('loggedIn', true);
                // set userType
                localStorage.setItem('userType', 'admin');
                navigate('/admin');
            } else {
                console.log('Login Failed');
                setError(response.data.message);
                setName("Failed");
                handleShowErrorModal();
            }
        } else {
            console.log('ACP Operator Login');
            const response = await loginOperator(email, password);
            console.log(response);
            if (response.status === 200) {
                console.log('Login Success');
                // Set Log in Local Storage
                localStorage.setItem('loggedIn', true);
                // set userType
                localStorage.setItem('userType', 'acp-operator');
                navigate('/acp-operator');
            } else {
                console.log('Login Failed');
                setError(response.data.message);
                setName("Failed");
                handleShowErrorModal();
            }
        }
    }

    const handleRequest = async (request) => {
        console.log(request);

        const response = await acpRequest(request);
        console.log(response);
        if (response.status === 200) {
            console.log('Request Success');
            setError("Request Sent Successfully");
            setName("Success");
            handleShowErrorModal();
        } else {
            console.log('Request Failed');
            setError("Request Failed");
            setName("Failed");
            handleShowErrorModal();
        }
    }


    return (
        <>
            <BasicExample />
            <Container className="mb-5">
                <Row className='justify-content-md-center'>
                    <Col md={6} className='mt-5'>
                        <Login handleLogin={(email, password, admin) => handleLogin(email, password, admin)} />
                    </Col>
                </Row>
                <Row className='justify-content-md-center'>
                    <Col md={6} className='mt-5'>
                        <Request 
                            handleRequest={(request) => handleRequest(request)}
                        />
                    </Col>
                </Row>
            </Container>
            <ErrorModal
                show={showErrorModal}
                handleClose={handleCloseErrorModal}
                error={error}
                title={name}
            />
        </>
    );
}   