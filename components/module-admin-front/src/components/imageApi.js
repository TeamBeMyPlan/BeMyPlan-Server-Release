import api from '../api'

const imageApi = {
    upload: async (data, config = {}) => {
        const result = await api.post('api/upload', data);

        return result.data;
    }
}

export const compressionOptions = {
    maxSizeMB: 5,
}

export default imageApi;