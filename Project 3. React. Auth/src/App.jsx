import React from 'react'

import './App.css'
import Header from './modules/Header'
import {
  createBrowserRouter,
  RouterProvider,
  Navigate,
  useLocation,
} from 'react-router-dom'
import AboutPage from './routes/AboutPage'
import DataPage from './routes/DataPage'
import AuthPage from './routes/AuthPage'
import ErrorPage from './routes/ErrorPage'
import {useSelector} from 'react-redux'
import {Container} from 'react-bootstrap'

const router = createBrowserRouter([
  {
    path: '/',
    element: <AboutPage />,
    errorElement: <ErrorPage />,
  },
  {
    path: '/auth',
    element: <AuthPage />,
    errorElement: <ErrorPage />,
  },
  {
    path: '/data',
    element: (
      <RequireAuth>
        <DataPage />
      </RequireAuth>
    ),
    errorElement: <ErrorPage />,
  },
])

const App = () => (
  <Container className='min-vw-100 min-vh-100 p-0'>
    <Header />
    <Container
      className='d-flex align-items-center justify-content-center'
      style={{height: 85 + 'vh'}}
    >
      <RouterProvider router={router} />
    </Container>
  </Container>
)

function RequireAuth({children}) {
  let auth = useSelector((state) => state.user.auth)
  let location = useLocation()

  if (!auth) {
    return <Navigate to='/auth' state={{from: location}} replace />
  }

  return children
}

export default App
