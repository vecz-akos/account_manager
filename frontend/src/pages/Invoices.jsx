import React, { useContext, useEffect, useState } from 'react'
import { AxiosContext } from '../contexts/AxiosContext'
import { Table } from 'react-bootstrap';

export default function Invoices() {
  const { authAxios } = useContext(AxiosContext);
  const [ invoices, setInvoices ] = useState([]);

  const invoiceRequest = () => {
    authAxios.get("/invoice/").then(res => {
      setInvoices(res.data);
    }).then(error => {
      console.log(JSON.stringify(error));
    })
  }
  
  useEffect(() => {
    invoiceRequest();
  }, []);
  return (
    <>
      <h1>Invoices</h1>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Customer name</th>
            <th>Invoice date</th>
            <th>Due date</th>
            <th>Itemname</th>
            <th>Description</th>
            <th>Price</th>
          </tr>
        </thead>
        <tbody>
          {
            invoices.map((invoice, i) =>
              <tr key={i}>
                <td>{invoice["customerName"]}</td>
                <td>{invoice["invoiceDate"]}</td>
                <td>{invoice["dueDate"]}</td>
                <td>{invoice["itemName"]}</td>
                <td>{invoice["description"]}</td>
                <td>{invoice["price"]}</td>
              </tr>
            )
          }
        </tbody>
      </Table>
    </>
  )
}
