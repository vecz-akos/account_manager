import React, {createContext, useContext} from 'react';
import axios from 'axios';
import { AuthContext } from './AuthContext';
  
const AxiosContext = createContext({});
const { Provider } = AxiosContext;

const AxiosProvider = ({children}) => {
  const authContext = useContext(AuthContext);

  const publicAxios = axios.create({
    baseURL: 'http://localhost:8080',
  });

  const authAxios = axios.create({
    baseURL: 'http://localhost:8080',
  }); 

  authAxios.interceptors.request.use(
    config => {
      if (!config.headers.Authorization) {
        config.headers.Authorization = `Bearer ${authContext.getAccessToken()}`;
      }

      return config;
    },
    error => {
      return Promise.reject(error);
    },
  );

  return (
    <Provider
      value={{
        authAxios,
        publicAxios
      }}>
      {children}
    </Provider>
  );
};

export {AxiosContext, AxiosProvider};
