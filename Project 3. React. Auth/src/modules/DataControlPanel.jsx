import React, {useState} from 'react'
import {Container} from 'react-bootstrap'
import Button from 'react-bootstrap/Button'
import Modal from 'react-bootstrap/Modal'
import {Form} from 'react-bootstrap'
import {dataIsActual} from '../redux/dataSlice'
import {useDispatch} from 'react-redux'

function DataControlPanel(props) {
  const dispatch = useDispatch()

  const [showAdd, setShowAdd] = useState(false)
  const [showEdit, setShowEdit] = useState(false)
  const [showDelete, setShowDelete] = useState(false)
  const [validated, setValidated] = useState(false)

  const POST_FIELDS = {
    companies: ['name'],
    depots: ['productId', 'shopId'],
    products: ['name', 'companyId'],
    shops: ['name', 'depot_productid'],
  }

  const PUT_FIELDS = {
    companies: ['id', 'name'],
    depots: ['productId', 'shopId'],
    products: ['id', 'name', 'companyId'],
    shops: ['id', 'name', 'depot_productid'],
  }

  const PRIMARY_FIELDS = {
    companies: ['id'],
    depots: ['productId'],
    products: ['id'],
    shops: ['id'],
  }

  const makeRequest = (url, method, body) => {
    fetch(url, {
      method: method,
      body: JSON.stringify(body),
      headers: {
        'Content-type': 'application/json; charset=UTF-8',
      },
    }).finally(() => {
      dispatch(dataIsActual(false))
    })
  }

  async function handleSubmit(event) {
    const form = event.currentTarget

    if (form.checkValidity() !== false) {
      event.preventDefault()
      event.stopPropagation()
    }

    const action = event.target.action.split('/').pop()
    const tableType = props.url.split('/').pop()

    switch (action) {
      case 'POST':
        makeRequest(props.url, action, getBody(event, tableType))
        break
      case 'PUT':
        makeRequest(
          props.url + '/' + event.target[PRIMARY_FIELDS[tableType]].value,
          action,
          getBody(event, tableType)
        )
        break
      case 'DELETE':
        makeRequest(
          props.url + '/' + event.target[PRIMARY_FIELDS[tableType]].value,
          action,
          {}
        )
        break
      default:
        break
    }

    setValidated(true)
    setShowAdd(false)
    setShowEdit(false)
    setShowDelete(false)
  }

  const getBody = (event, tableType) => {
    let body = {}
    POST_FIELDS[tableType].forEach((field) => {
      console.log(field)
      body[field] = event.target[field].value
    })
    console.log(body)
    return body
  }

  const FormGroup = (controlId, label, type, text) => {
    return (
      <Form.Group className='mb-3' controlId={controlId}>
        <Form.Label>{label}</Form.Label>
        <Form.Control type={type} name={type} required />
        <Form.Text className='text-muted'>{text}</Form.Text>
      </Form.Group>
    )
  }

  const GenerateForm = (props) => {
    let types
    switch (props.action) {
      case 'DELETE':
        types = PRIMARY_FIELDS
        break
      case 'POST':
        types = POST_FIELDS
        break
      case 'PUT':
        types = PUT_FIELDS
        break
      default:
        types = null
    }

    return (
      <Form
        onSubmit={handleSubmit}
        noValidate
        validated={validated}
        name={props.tableName}
        action={props.action}
      >
        {types[props.tableName].map((t) =>
          FormGroup(props.tableName + t + 'Form', t, t, 'Enter ' + t)
        )}

        <Button variant='primary' type='submit'>
          Submit
        </Button>
      </Form>
    )
  }

  return (
    <div>
      <>
        <Container className='my-2 d-flex align-items-center justify-content-center'>
          <Button onClick={() => setShowAdd(true)} className='ms-3'>
            Add
          </Button>
          <Button onClick={() => setShowEdit(true)} className='ms-3'>
            Edit
          </Button>
          <Button onClick={() => setShowDelete(true)} className='ms-3'>
            Delete
          </Button>
        </Container>

        <Modal
          size='lg'
          show={showAdd}
          onHide={() => setShowAdd(false)}
          aria-labelledby='Add'
        >
          <Modal.Header closeButton>
            <Modal.Title id='add-title'>Add modal</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <GenerateForm
              tableName={props.url.split('/').pop()}
              action='POST'
            />
          </Modal.Body>
        </Modal>

        <Modal
          size='lg'
          show={showEdit}
          onHide={() => setShowEdit(false)}
          aria-labelledby='Edit'
        >
          <Modal.Header closeButton>
            <Modal.Title id='edit-title'>Edit modal</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <GenerateForm tableName={props.url.split('/').pop()} action='PUT' />
          </Modal.Body>
        </Modal>

        <Modal
          size='lg'
          show={showDelete}
          onHide={() => setShowDelete(false)}
          aria-labelledby='Delete'
        >
          <Modal.Header closeButton>
            <Modal.Title id='delete-title'>Delete modal</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <GenerateForm
              tableName={props.url.split('/').pop()}
              action='DELETE'
            />
          </Modal.Body>
        </Modal>
      </>
    </div>
  )
}

export default DataControlPanel
