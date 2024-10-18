import React, { createContext, useContext, useEffect, useState } from "react";
import {
  fetchUserData,
  getStoredAuthData,
  loginUser,
  removeAuthData,
} from "../lib/auth";

const GlobalContext = createContext();

export const useGlobalContext = () => useContext(GlobalContext);

const GlobalProvider = ({ children }) => {
  const [isLogged, setIsLogged] = useState(false);
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    checkAuthStatus();
  }, []);

  const checkAuthStatus = async () => {
    try {
      const { token, username } = await getStoredAuthData();
      if (token && username) {
        const userData = await fetchUserData(token, username);
        setUser(userData);
        setIsLogged(true);
      } else {
        setIsLogged(false);
        setUser(null);
      }
    } catch (error) {
      console.error("Auth check error:", error);
      setIsLogged(false);
      setUser(null);
    } finally {
      setLoading(false);
    }
  };

  const login = async (username, password) => {
    try {
      const { accessToken } = await loginUser(username, password);
      const userData = await fetchUserData(accessToken, username);
      setUser(userData);
      setIsLogged(true);
    } catch (error) {
      console.error("Login error:", error);
      throw error;
    }
  };

  const logout = async () => {
    try {
      await removeAuthData();
      setIsLogged(false);
      setUser(null);
    } catch (error) {
      console.error("Logout error:", error);
    }
  };

  return (
    <GlobalContext.Provider
      value={{
        isLogged,
        user,
        loading,
        login,
        logout,
        checkAuthStatus,
      }}
    >
      {children}
    </GlobalContext.Provider>
  );
};

export default GlobalProvider;
