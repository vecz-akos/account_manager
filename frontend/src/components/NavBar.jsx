import React from 'react'
import { Container, Nav, Navbar } from 'react-bootstrap'
import { Link } from 'react-router-dom'

export default function NavBar() {
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
          </Navbar.Collapse>
        </Container>
      </Navbar>
  )
}
