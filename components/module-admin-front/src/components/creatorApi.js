import api from '../api'

const creatorApi = {
    getCreators: async (config = {}) => {
        const result = await api.get('api/creator');

        return result;
    }
}

export default creatorApi;