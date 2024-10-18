import axios from "axios";
import React, { createContext, useContext, useEffect, useState } from "react";
import {
  getStoredToken,
  isAuthenticated,
  loginUser,
  removeToken,
  authConfig
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
      const authenticated = await isAuthenticated();
      if (authenticated) {
        const token = await getStoredToken();
        setIsLogged(true);
        await fetchUserData(token);
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

  const fetchUserData = async (token) => {
    try {
      const response = await axios.get(`${authConfig.endpoint}/api/users/me`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setUser(response.data);
    } catch (error) {
      console.error("Error fetching user data:", error);
      setUser(null);
    }
  };

  const login = async (username, password) => {
    try {
      const token = await loginUser(username, password);
      setIsLogged(true);
      await fetchUserData(token);
    } catch (error) {
      console.error("Login error:", error);
      throw error;
    }
  };

  const logout = async () => {
    try {
      await removeToken();
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
        setIsLogged,
        user,
        setUser,
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
