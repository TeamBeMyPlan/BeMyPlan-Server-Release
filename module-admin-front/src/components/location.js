import api from '../api'

const location = {
    getLocation: async (query) => {
        const result = await api.get('location', {
            params: {
                query: query
            }
        });
        return result;
    }
}

export default location;