import api from '../api'

const planApi = {
    create: async (data, config = {}) => {
        const result = await api.post('api/v1/plan', data);

        return result;
    }
}

export default planApi;