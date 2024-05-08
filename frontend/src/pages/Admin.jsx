import React, { useContext, useEffect, useState } from 'react'
import { AxiosContext } from '../contexts/AxiosContext';
import { Table } from 'react-bootstrap';

export default function Admin() {
  const { publicAxios } = useContext(AxiosContext);
  const [ users, setUsers ] = useState([]);
  const [ errorMsg, setErrorMsg ] = useState("");

  const usersRequest = () => {
    publicAxios.get("/auth/all_user").then(res => {
      setUsers(res.data);
    }).catch(error => {
      setErrorMsg("Please login with admin user to access user list!");
    })
  }
  
  useEffect(() => {
    usersRequest();
  }, []);

  return (
    <>
      <h1>Users</h1>
      <p>{errorMsg}</p>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Name</th>
            <th>Username</th>
            <th>Roles</th>
          </tr>
        </thead>
        <tbody>
          {
            users.map(user =>
              <tr key={user["username"]}>
                <td>{user["name"]}</td>
                <td>{user["username"]}</td>
                <td>{user["roles"].map(role => role.name).join(", ")}</td>
              </tr>
            )
          }
        </tbody>
      </Table>
    </>
  )
}
