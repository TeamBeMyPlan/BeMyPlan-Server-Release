import axios from "axios";

//const serverUrl = 'http://3.36.234.206';
const serverUrl = 'http://3.39.166.87';
// const serverUrl = 'http://localhost:8080';
const api = {
  get: async (url, data) => {
    try {
      const response = await axios.get(`${serverUrl}/${url}`, data);

      if (response.status === 200) {
        return response.data;
      }

      return response;
    } catch (err) {
      return Promise.reject(err);
    }
  },
  delete: async (url, data) => {
    try {
      const response = await axios.delete(`${serverUrl}/${url}`, data);

      if (response.status === 200) {
        return response.data;
      }

      return response;
    } catch (err) {
      return Promise.reject(err);
    }
  },
  post: async (url, data, config = {}) => {
    try {
      const response = await axios.post(`${serverUrl}/${url}`, data, config);

      return response;
    } catch (err) {
      return Promise.reject(err);
    }
  },
  put: async (url, data, config = {}) => {
    try {
      const response = await axios.put(`${serverUrl}/${url}`, data, config);

      return response;
    } catch (err) {
      return Promise.reject(err);
    }
  },
};

export default api;