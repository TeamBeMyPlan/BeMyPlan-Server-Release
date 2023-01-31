import api from '../api'

const planApi = {
    create: async (data, config = {}) => {
        const result = await api.post('api/v1/plan', data);

        return result;
    },
    list: async () => {
        const result = await api.get('api/plans');

        return result;
    }
}

export default planApi;