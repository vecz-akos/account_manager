import React, { useContext, useState } from 'react'
import { Button, Form } from 'react-bootstrap'
import { AxiosContext } from '../contexts/AxiosContext';
import { Link } from 'react-router-dom';

export default function Registration() {
  const { publicAxios } = useContext(AxiosContext);

  const [errorMsg, setErrorMsg] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();

    const formData = new FormData(event.currentTarget);
    const username = formData.get("username");
    const name = formData.get("name");
    const password = formData.get("password");
    const role = Number(formData.get("role"));

    publicAxios.post("/auth/register", {
        "username": username,
        "name": name,
        "password": password,
        "role": role,
      },
      { headers: {
          'Content-Type': 'application/json'
      }}).then(res => {
        setErrorMsg("User registered!")
      }).catch(error => {
        setErrorMsg(error.response.data.exceptionMessage)
      });
  };
  return (
    <>
        <Form onSubmit={handleSubmit}>
            <Form.Text id="passwordHelpBlock" muted>
                {errorMsg}
            </Form.Text>
            <Form.Group className="mb-3">
                <Form.Label>Name</Form.Label>
                <Form.Control naem="name" type="name" />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Username</Form.Label>
                <Form.Control name="username" type="text" />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Password</Form.Label>
                <Form.Control name="password" type="password" />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Role</Form.Label>
                <Form.Select name="role">
                    <option value="2">Accountant</option>
                    <option value="3">User</option>
                </Form.Select>
            </Form.Group>
            <Button variant="primary" type="submit">Registration</Button>
        </Form>
        <Link to="/">Back to login</Link>
    </>
  )
}
