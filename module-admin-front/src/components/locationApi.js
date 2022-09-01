import api from '../api'

const locationApi = {
    getLocation: async (query) => {
        const result = await api.get('api/location', {
            params: {
                query: query
            }
        });
        return result;
    }
}

export default locationApi;