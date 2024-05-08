import React, { useContext, useEffect, useState } from 'react'
import { AxiosContext } from '../contexts/AxiosContext';
import { Table } from 'react-bootstrap';

export default function Admin() {
  const { publicAxios } = useContext(AxiosContext);
  const [ users, setUsers ] = useState([]);

  const usersRequest = () => {
    publicAxios.get("/auth/all_user").then(res => {
      setUsers(res.data);
    }).then(error => {
      console.log(JSON.stringify(error));
    })
  }
  
  useEffect(() => {
    usersRequest();
  }, []);

  return (
    <>
      <h1>Users</h1>
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
