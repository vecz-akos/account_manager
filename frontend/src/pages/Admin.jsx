import React, { useContext, useEffect, useState } from 'react'
import { AxiosContext } from '../contexts/AxiosContext';
import { Alert, Button, Form, Stack, Table } from 'react-bootstrap';

export default function Admin() {
  const { authAxios, publicAxios } = useContext(AxiosContext);
  const [ users, setUsers ] = useState([]);
  const [ errorMsg, setErrorMsg ] = useState("");
  const [ infoMsg, setInfoMsg ] = useState("");

  const roles = ["admin", "accountant", "user"];

  const usersRequest = () => {
    authAxios.get("/auth/all_user").then(res => {
      setUsers(res.data);
    }).catch(error => {
      setErrorMsg("Please login with admin user to access user list!");
    })
  };

  const handleRole = (event, user, role) => {
    if (event.target.checked) {
      authAxios.post(`/auth/all_user/${user}/role`, {
        "roleName": role
      }, ).then(res => {
        setInfoMsg("Role modified.");
      }).catch(err => {
        setErrorMsg("Something went wrong.");
      });
    } else {
      authAxios.delete(`/auth/all_user/${user}/role`, { data :{
        "roleName": role
      }}, ).then(res => {
        setInfoMsg("Role modified.");
      }).catch(err => {
        setErrorMsg("Something went wrong.");
      });
    }
  };

  const handleDelete = (username) => {
    authAxios.delete(`/auth/all_user/${username}`).then(res => {
      setInfoMsg(`User (${username}) was deleted.`)
    }).catch(err => {
      setErrorMsg("Network error. Operation was not succesful.")
    })
  };
  
  useEffect(() => {
    usersRequest();
  }, [errorMsg, infoMsg]);

  return (
    <>
      <h1>Users</h1>
      {errorMsg ? <Alert variant='danger'>{errorMsg}</Alert> : <></>}
      {infoMsg ? <Alert variant='info'>{infoMsg}</Alert> : <></>}
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Name</th>
            <th>Username</th>
            <th>Roles</th>
            <th>-</th>
          </tr>
        </thead>
        <tbody>
          {
            users.map(user =>
              <tr key={user["username"]}>
                <td>{user["name"]}</td>
                <td>{user["username"]}</td>
                <td>
                  <Form className="mb-3">
                    {roles.map((role, i) =>
                      <Form.Check
                        onChange={(e) => handleRole(e, user["username"], role)}
                        key={i}
                        label={role}
                        value={role}
                        checked={user["roles"].map(r => r.name).indexOf(role) !== -1}
                      />)
                    }
                  </Form>
                </td>
                <td>{user["username"] !== "admin" ? <Button variant="danger" onClick={() => handleDelete(user["username"])}>Delete</Button> : <></>}</td>
              </tr>
            )
          }
        </tbody>
      </Table>
    </>
  )
}
