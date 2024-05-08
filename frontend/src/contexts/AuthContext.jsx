import React, {createContext, useState} from 'react';

const AuthContext = createContext({});
const {Provider} = AuthContext;

const AuthProvider = ({children}) => {
  const [authState, setAuthState] = useState({
    accessToken: null,
    username: null,
    authenticated: null,
  });

  const logout = async () => {
    setAuthState({
      accessToken: null,
      username: null,
      authenticated: false,
    });
  };

  const getAccessToken = () => {
    return authState.accessToken;
  };

  const getUsername = () => {
    return authState.username;
  };

  return (
    <Provider
      value={{
        authState,
        getAccessToken,
        getUsername,
        setAuthState,
        logout,
      }}>
      {children}
    </Provider>
  );
};

export {AuthContext, AuthProvider};
