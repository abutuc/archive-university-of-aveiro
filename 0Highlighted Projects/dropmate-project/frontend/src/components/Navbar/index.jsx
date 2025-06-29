import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

// Bootstrap
import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';

function BasicExample() {
  const navigate = useNavigate();

  // See if user is logged in
  const loggedIn = localStorage.getItem('loggedIn');
  const userType = localStorage.getItem('userType');

  // Log out
  const handleLogout = () => {
    localStorage.removeItem('loggedIn');
    navigate('/');
  }

  return (
    <Navbar bg="light">
      <Container>
        <Navbar.Brand as={Link} to={userType === 'admin' ? '/admin' : '/acp-operator'}>
          DropMate
        </Navbar.Brand>
        <Navbar.Toggle />
        <Navbar.Collapse className="justify-content-end">
          {loggedIn && (
            <Navbar.Text>
              <Link to="/" onClick={handleLogout}>
                Log Out
              </Link>
            </Navbar.Text>
          )}
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default BasicExample;
