import React from 'react'
import NavBar from './NavBar'
import { Outlet } from 'react-router-dom'
import { Col, Container, Row } from 'react-bootstrap'

export default function Layout() {
  return (
    <>
      <NavBar />
      <Container>
        <Row className="justify-content-center">
          <Col lg={10}>
            <Outlet />
          </Col>
        </Row>
      </Container>
    </>
  )
}
