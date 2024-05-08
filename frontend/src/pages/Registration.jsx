import React, { useContext, useState } from 'react'
import { Alert, Button, Form } from 'react-bootstrap'
import { AxiosContext } from '../contexts/AxiosContext';
import { Link } from 'react-router-dom';

export default function Registration() {
  const { publicAxios } = useContext(AxiosContext);

  const [errorMsg, setErrorMsg] = useState({});
  const [success, setSuccess] = useState(false);

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
        setSuccess(true)
      }).catch(err => {
        const messages = {};
        const data = err?.response?.data;
        for (let key of ["name", "username", "password", "role"]) {
            if (Object.keys(data).indexOf(key) !== -1) {
                messages[key] = data[key];
            }
        }
        setErrorMsg(messages);
      });
  };
  return (
    <>
        <Form onSubmit={handleSubmit}>
            {success ?
                <Alert variant='info'>"User registered"</Alert>
              : <></>}
            <Form.Group className="mb-3">
                <Form.Label>Name</Form.Label>
                <Form.Control name="name" type="name" />
                <Form.Text>{errorMsg?.name}</Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Username</Form.Label>
                <Form.Control name="username" type="text" />
                <Form.Text>{errorMsg?.username}</Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Password</Form.Label>
                <Form.Control name="password" type="password" />
                <Form.Text>{errorMsg?.password}</Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Role</Form.Label>
                <Form.Select name="role">
                    <option value="2">Accountant</option>
                    <option value="3">User</option>
                </Form.Select>
                <Form.Text>{errorMsg?.role}</Form.Text>
            </Form.Group>
            <Button variant="primary" type="submit">Registration</Button>
        </Form>
        <Link to="/">Back to login</Link>
    </>
  )
}
