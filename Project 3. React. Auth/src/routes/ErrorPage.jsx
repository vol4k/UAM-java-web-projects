import React from 'react'
import {useRouteError} from 'react-router-dom'
import {Container} from 'react-bootstrap'

export default function ErrorPage() {
  const error = useRouteError()
  console.error(error)

  return (
    <Container className='p-3 shadow vw-50'>
      <div id='error-page'>
        <h1>Oops!</h1>
        <p>Sorry, an unexpected error has occurred.</p>
        <p>
          <i>{error.statusText || error.message}</i>
        </p>
      </div>
    </Container>
  )
}
