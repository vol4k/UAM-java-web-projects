import {createSlice} from '@reduxjs/toolkit'

export const authSlice = createSlice({
  name: 'user',
  initialState: {
    auth: false,
    name: 'None',
  },
  reducers: {
    login: (state, action) => {
      state.auth = true
      state.name = action.payload
    },
    logout: (state) => {
      state.auth = false
      state.name = 'None'
    },
  },
})

export const {login, logout} = authSlice.actions

export default authSlice.reducer
