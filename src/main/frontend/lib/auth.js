import axios from "axios";

export const authConfig = {
  endpoint: "http://localhost:8080",
};

export const createUser = ({ username, password, email }) => {
    return axios.post(`${authConfig.endpoint}/api/users`, {
      username,
      password,
      email,
      firstName: username, // You might want to separate firstName and lastName in your form
      lastName: "User",
      createdAt: new Date().toISOString(),
      lastLogin: new Date().toISOString(),
    }).then(response => {
      console.log("User created successfully:", response.data);
      return response.data;
    }).catch(error => {
      console.error("Error creating user:", error.response ? error.response.data : error.message);
      throw error;
    });
  };
