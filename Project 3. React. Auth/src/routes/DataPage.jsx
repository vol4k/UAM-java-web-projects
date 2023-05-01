import React from 'react'
import {Container} from 'react-bootstrap'
import Tab from 'react-bootstrap/Tab'
import Tabs from 'react-bootstrap/Tabs'
import DynamicTable from '../modules/DynamicTable'
import DataControlPanel from '../modules/DataControlPanel'

const server = 'http://localhost:8080/api'

const DataPage = () => (
  <Container className='p-3 shadow vw-50'>
    <Tabs>
      <Tab eventKey='companies' title='Companies'>
        <Data url='/companies' />
      </Tab>
      <Tab eventKey='shops' title='Shops'>
        <Data url='/shops' />
      </Tab>
      <Tab eventKey='products' title='Products'>
        <Data url='/products' />
      </Tab>
      <Tab eventKey='depots' title='Depots'>
        <Data url='/depots' />
      </Tab>
    </Tabs>
  </Container>
)

function Data(props) {
  let api = server + props.url
  return (
    <div>
      <DynamicTable url={api} />
      <DataControlPanel url={api} />
    </div>
  )
}

export default DataPage
