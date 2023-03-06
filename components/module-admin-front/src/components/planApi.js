import api from '../api'

const planApi = {
    create: async (data, config = {}) => {
        const result = await api.post('api/plans', data);

        return result;
    },
    list: async () => {
        const result = await api.get('api/plans');

        return result;
    },
    delete: async (id) => {
        const result = await api.delete(`api/plans/${id}`);

        return result;
    },
    get: async (id) => {
        const result = await api.get(`api/plans/${id}`);

        return result;
    },
    getSpots: async (id) => {
        const result = await api.get(`api/plans/${id}/spots`);

        return result;
    },
    getPreviews: async (id) => {
        const result = await api.get(`api/plans/${id}/previews`);

        return result;
    },
    put: async (id, data) => {
        const result = await api.put(`api/plans/${id}`, data);

        return result;
    },
    putSpots: async (id, data) => {
        const result = await api.put(`api/plans/${id}/spots`, data);

        return result;
    },
    putPreviews: async (id, data) => {
        const result = await api.put(`api/plans/${id}/previews`, data);

        return result;
    },
    deleteSpot: async (id) => {
        const result = await api.delete(`api/spots/${id}`);

        return result;
    }
}

export default planApi;