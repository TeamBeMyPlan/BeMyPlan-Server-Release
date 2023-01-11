import api from '../api'

const imageApi = {
    upload: async (data, config = {}) => {
        const result = await api.post('api/upload', data);

        return result.data;
    }
}

export const compressionOptions = {
    maxSizeMB: 2,
    maxWidthOrHeight: 512
}

export default imageApi;