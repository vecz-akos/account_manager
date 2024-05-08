import React, { useContext, useState } from 'react'
import { Alert, Button, Form } from 'react-bootstrap'
import { AxiosContext } from '../contexts/AxiosContext';

export default function InvoiceForm() {
  const { authAxios } = useContext(AxiosContext);

  const [errorMsg, setErrorMsg] = useState({});
  const [success, setSuccess] = useState(false);

  const handleSubmit = (event) => {
    event.preventDefault();

    setSuccess(false);

    const formData = new FormData(event.currentTarget);
    const customerName = formData.get("customerName");
    const invoiceDate = formData.get("invoiceDate");
    const dueDate = formData.get("dueDate");
    const itemName = formData.get("itemName");
    const description = formData.get("description");
    const price = formData.get("price");

    authAxios.post("/invoice/", {
        "customerName": customerName,
        "invoiceDate": invoiceDate,
        "dueDate": dueDate,
        "itemName": itemName,
        "description": description,
        "price": price,
    }).then(res => {
        setSuccess(true);
    }).catch(err => {
        const messages = {};
        const data = err?.response?.data;
        for (let key of ["customerName", "invoiceDate", "dueDate", "itemName", "description", "price"]) {
            if (Object.keys(data).indexOf(key) !== -1) {
                messages[key] = data[key];
            }
        }
        setErrorMsg(messages);
    })
  };
  return (
    <Form onSubmit={handleSubmit}>
      {success ?
          <Alert variant='info'>"Invoice created"</Alert>
        : <></>}
      <Form.Group className="mb-3">
        <Form.Label>Customer name</Form.Label>
        <Form.Control type="text" name="customerName" />
        <Form.Text>{errorMsg?.customerName}</Form.Text>
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>Invoice date</Form.Label>
        <Form.Control type="date" name="invoiceDate" />
        <Form.Text >{errorMsg?.invoiceDate}</Form.Text>
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>Due date</Form.Label>
        <Form.Control type="date" name="dueDate" />
        <Form.Text >{errorMsg?.dueDate}</Form.Text>
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>Itemname</Form.Label>
        <Form.Control type="text" name="itemName" />
        <Form.Text >{errorMsg?.itemName}</Form.Text>
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>Description</Form.Label>
        <Form.Control type="text" name="description" />
        <Form.Text >{errorMsg?.description}</Form.Text>
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>Price</Form.Label>
        <Form.Control type="number" name="price" />
        <Form.Text >{errorMsg?.price}</Form.Text>
      </Form.Group>
      <Button type='submit' variant="primary">Create</Button>
    </Form>
  )
}
