import axios from "axios";

const serverUrl = 'http://localhost:8080';
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
};

export default api;