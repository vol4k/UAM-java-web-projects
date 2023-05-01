import React from 'react'

import Container from 'react-bootstrap/Container'
import Navbar from 'react-bootstrap/Navbar'
import {Button, Nav} from 'react-bootstrap'
import {useSelector, useDispatch} from 'react-redux'
import {logout} from '../redux/authSlice'

export default function Header() {
  return (
    <div>
      <Navbar bg='light' expand='lg' className='min-vw-100'>
        <Container>
          <AuthLinks />
          <AuthStatus />
        </Container>
      </Navbar>
    </div>
  )
}

function AuthLinks() {
  const auth = useSelector((state) => state.user.auth)

  if (auth)
    return (
      <Nav className='me-auto'>
        <Nav.Link href='/'>About</Nav.Link>
        <Nav.Link href='/data'>Data Panel</Nav.Link>
      </Nav>
    )
  else
    return (
      <Nav className='me-auto'>
        <Nav.Link href='/'>About</Nav.Link>
      </Nav>
    )
}

function AuthStatus() {
  const auth = useSelector((state) => state.user.auth)
  const name = useSelector((state) => state.user.name)
  const dispatch = useDispatch()

  if (auth)
    return (
      <Navbar.Collapse className='justify-content-end'>
        <Navbar.Text className='me-3'>
          You are logined as <b>{name}</b>
        </Navbar.Text>
        <Button variant='danger' onClick={() => dispatch(logout())}>
          logout
        </Button>
      </Navbar.Collapse>
    )
  else
    return (
      <Navbar.Collapse className='justify-content-end'>
        <Navbar.Text className='me-3'>You are not logined</Navbar.Text>
        <Button variant='primary' href='/auth'>
          login
        </Button>
      </Navbar.Collapse>
    )
}
