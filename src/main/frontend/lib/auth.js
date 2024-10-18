import AsyncStorage from "@react-native-async-storage/async-storage";
import axios from "axios";

export const authConfig = {
  endpoint: "http://192.168.1.212:8080",
};

export const storeAuthData = async (token, username) => {
  try {
    await AsyncStorage.setItem("accessToken", token);
    await AsyncStorage.setItem("username", username);
  } catch (error) {
    console.error("Error storing auth data", error);
  }
};

export const getStoredAuthData = async () => {
  try {
    const token = await AsyncStorage.getItem("accessToken");
    const username = await AsyncStorage.getItem("username");
    return { token, username };
  } catch (error) {
    console.error("Error retrieving auth data", error);
    return { token: null, username: null };
  }
};

export const removeAuthData = async () => {
  try {
    await AsyncStorage.removeItem("accessToken");
    await AsyncStorage.removeItem("username");
  } catch (error) {
    console.error("Error removing auth data", error);
  }
};

export const loginUser = async (username, password) => {
  try {
    const response = await axios.post(`${authConfig.endpoint}/api/auth/login`, {
      username,
      password,
    });
    const { accessToken } = response.data;
    await storeAuthData(accessToken, username);
    return { accessToken, username };
  } catch (error) {
    console.error("Login error:", error);
    throw error;
  }
};

export const fetchUserData = async (token, username) => {
  try {
    const response = await axios.get(
      `${authConfig.endpoint}/api/users/username/${username}`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching user data:", error);
    throw error;
  }
};
