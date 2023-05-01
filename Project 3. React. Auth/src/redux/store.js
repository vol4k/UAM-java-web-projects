import {configureStore} from '@reduxjs/toolkit'
import authReducer from './authSlice'
import storage from 'redux-persist/lib/storage'
import {persistReducer, persistStore} from 'redux-persist'
import thunk from 'redux-thunk'
import {combineReducers} from '@reduxjs/toolkit'
import dataSlice from './dataSlice'

const persistConfig = {
  key: 'root',
  storage,
}

const persistedReducer = persistReducer(
  persistConfig,
  combineReducers({
    user: authReducer,
    data: dataSlice,
  })
)

export const store = configureStore({
  reducer: persistedReducer,
  middleware: [thunk],
})

export const persistor = persistStore(store)
