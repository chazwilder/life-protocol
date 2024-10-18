import AsyncStorage from "@react-native-async-storage/async-storage";
import axios from "axios";

export const authConfig = {
  endpoint: "http://192.168.1.212:8080",
};

export const createUser = ({ username, password, email }) => {
  return axios
    .post(`${authConfig.endpoint}/api/users`, {
      username,
      password,
      email,
      firstName: username,
      lastName: "User",
      createdAt: new Date().toISOString(),
      lastLogin: new Date().toISOString(),
    })
    .then((response) => {
      console.log("User created successfully:", response.data);
      return response.data;
    })
    .catch((error) => {
      console.error(
        "Error creating user:",
        error.response ? error.response.data : error.message
      );
      throw error;
    });
};

export const storeToken = async (token) => {
  try {
    await AsyncStorage.setItem("accessToken", token);
  } catch (error) {
    console.error("Error storing auth token", error);
  }
};

export const getStoredToken = async () => {
  try {
    return await AsyncStorage.getItem("accessToken");
  } catch (error) {
    console.error("Error retrieving auth token", error);
    return null;
  }
};

export const removeToken = async () => {
  try {
    await AsyncStorage.removeItem("accessToken");
  } catch (error) {
    console.error("Error removing auth token", error);
  }
};

export const loginUser = async ({ username, password }) => {
  try {
    const response = await axios.post(`${authConfig.endpoint}/api/auth/login`, {
      username,
      password,
    });

    const accessToken = response.data.accessToken;
    await storeToken(accessToken);
    return accessToken;
  } catch (error) {
    console.error("Login error:", error);
    throw error;
  }
};

export const isAuthenticated = async () => {
  const token = await getStoredToken();
  return !!token;
};
