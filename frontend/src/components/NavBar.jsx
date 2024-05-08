import React, { useContext } from 'react'
import { Container, Nav, Navbar } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import { AuthContext } from '../contexts/AuthContext';

export default function NavBar() {
  const auth = useContext(AuthContext);
  return (
    <Navbar bg="primary" data-bs-theme="dark">
        <Container fluid>
          <Navbar.Brand href="/">Invoice manager</Navbar.Brand>
          <Navbar.Toggle aria-controls="navbarScroll" />
          <Navbar.Collapse id="navbarScroll">
            <Nav
              className="me-auto my-2 my-lg-0"
              style={{ maxHeight: "100px" }}
              navbarScroll
            ></Nav>
            <Nav className="me-auto">
              <Link className='nav-link' to="/" >Home</Link>
              <Link className='nav-link' to="/invoices">Invoices</Link>
              <Link className='nav-link' to="/admin">Admin</Link>
            </Nav>
              {
                auth.authenticated ?
                  <p>`Signed in as: ${auth.username}`</p>
                : <Link to="/">Login</Link>
              }
          </Navbar.Collapse>
        </Container>
      </Navbar>
  )
}
