import React, { useContext, useState } from 'react'
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { Link } from 'react-router-dom';
import { AuthContext } from '../contexts/AuthContext';
import { AxiosContext } from '../contexts/AxiosContext';

export default function Login() {
  const auth = useContext(AuthContext);
  const { publicAxios } = useContext(AxiosContext);

  const [errorMsg, setErrorMsg] = useState("");
  const [isLoggedIn, setIsLoggedIn] = useState(auth.authState.authenticated || false);

  const handleSubmit = (event) => {
    event.preventDefault();

    const formData = new FormData(event.currentTarget);
    const username = formData.get("username");
    const password = formData.get("password");
    
    publicAxios.post("/auth/login", {
        "username": username,
        "password": password,
      },
      { headers: {
          'Content-Type': 'application/json'
      }}).then(res => {
        const { token } = res.data;
          auth.setAuthState({
          accessToken: token,
          username: username,
          authenticated: true,
        });
        setIsLoggedIn(true);
      }).catch(err => {
        setErrorMsg("Wrong username or password!");
        setIsLoggedIn(false);
      });
  }

  const handleLogout = () => {
    auth.logout();
    setIsLoggedIn(false);
  }

  return isLoggedIn ?
    <></>
  : <>
    <Form onSubmit={handleSubmit}>
      {errorMsg ? <Form.Text>{errorMsg}</Form.Text> : <></>}
      <Form.Group className="mb-3">
        <Form.Label>Username</Form.Label>
        <Form.Control name='username' />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicPassword">
        <Form.Label>Password</Form.Label>
        <Form.Control name="password" type="password" />
      </Form.Group>
      <Button type='submit' variant="primary">Login</Button>
    </Form>
    <Link to="/registration">Registration</Link>
  </>
}
