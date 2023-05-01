import React from 'react'
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'
import {Container} from 'react-bootstrap'
import {Row} from 'react-bootstrap'
import {useState} from 'react'
import {useDispatch} from 'react-redux'
import {login} from '../redux/authSlice'
import {useSelector} from 'react-redux'
import {useLocation} from 'react-router-dom'
import {Navigate} from 'react-router-dom'

export default function AuthPage() {
  const [validated, setValidated] = useState(false)
  const dispatch = useDispatch()

  const auth = async (name, password) => {
    await fetch('http://localhost:8080/api/users', {
      method: 'POST',
      body: JSON.stringify({
        name: name,
        password: password,
      }),
      headers: {
        'Content-type': 'application/json; charset=UTF-8',
      },
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.length !== 0) dispatch(login(name))
      })
  }

  async function handleSubmit(event) {
    const form = event.currentTarget
    if (form.checkValidity() !== false) {
      event.preventDefault()
      event.stopPropagation()
    }

    auth(event.target.name.value, event.target.password.value)

    setValidated(true)
  }

  function RedirectAuth({children}) {
    let auth = useSelector((state) => state.user.auth)
    let location = useLocation()

    if (auth) {
      return <Navigate to='/' state={{from: location}} replace />
    }

    return children
  }

  return (
    <Container className='p-3 shadow' style={{width: 30 + 'vw'}}>
      <RedirectAuth>
        <Form noValidate validated={validated} onSubmit={handleSubmit}>
          <Row className='mb-3'>
            <Form.Group controlId='validationUsername'>
              <Form.Label>Username</Form.Label>
              <Form.Control
                required
                name='name'
                type='text'
                placeholder='Username'
              />
              <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
            </Form.Group>
          </Row>
          <Form.Group className='mb-3' controlId='formBasicPassword'>
            <Form.Label>Password</Form.Label>
            <Form.Control
              required
              type='password'
              name='password'
              placeholder='Password'
            />
          </Form.Group>
          <Button type='submit'>Submit form</Button>
        </Form>
      </RedirectAuth>
    </Container>
  )
}
