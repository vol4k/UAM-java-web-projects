import React, {useEffect, useState} from 'react'

import BootstrapTable from 'react-bootstrap-table-next'
import paginationFactory from 'react-bootstrap-table2-paginator'
import {dataIsActual} from '../redux/dataSlice'
import {useSelector, useDispatch} from 'react-redux'

function DynamicTable(props) {
  const dispatch = useDispatch()
  const isActual = useSelector((state) => state.data.isActual)

  const [tableData, setData] = useState([])
  const [columns, setColumns] = useState([''])
  const [loading, setLoading] = useState(true)
  const [hasContent, setStatus] = useState(false)

  function fetchTable(url) {
    return fetch(url, {
      method: 'GET',
      headers: {
        'Content-type': 'application/json; charset=UTF-8',
        'Access-Control-Allow-Origin': '*',
      },
    })
      .then((response) => response.json())
      .then((data) => data)
  }

  useEffect(() => {
    fetchTable(props.url)
      .then((data) => {
        if (data.length === 0) return
        setStatus(true)
        setData(data)
        setColumns(
          Object.keys(data[0]).map((field) => {
            return {dataField: field, text: field, sort: true}
          })
        )
      })
      .finally(() => {
        setLoading(false)
        dispatch(dataIsActual(true))
      })
  }, [props, dispatch, isActual])

  return (
    <div>
      {!hasContent && <h1 className='text-center'>No data</h1>}
      {!loading && hasContent && (
        <BootstrapTable
          bootstrap4
          keyField='id'
          data={tableData}
          columns={columns}
          pagination={paginationFactory({sizePerPage: 5})}
        />
      )}
    </div>
  )
}

export default DynamicTable
