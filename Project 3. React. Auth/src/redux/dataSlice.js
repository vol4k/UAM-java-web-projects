import {createSlice} from '@reduxjs/toolkit'

export const dataSlice = createSlice({
  name: 'data',
  initialState: {
    isActual: false,
  },
  reducers: {
    dataIsActual: (state, action) => {
      state.isActual = action.payload
    },
  },
})

export const {dataIsActual} = dataSlice.actions

export default dataSlice.reducer
