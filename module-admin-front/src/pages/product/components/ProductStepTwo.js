import { Component } from 'react';
import Textbox from '../../../components/textbox/Textbox'
import Inputs from '../../../components/inputs/Inputs'
import Button from '../../../components/button/Button'
import DaumPostcodeEmbed from 'react-daum-postcode';
import locationApi from '../../../components/locationApi'
import imageApi from '../../../components/imageApi'

class ProductStepTwo extends Component {

    state = {
        openPostCode: false,
        address: '',
        longitude: 0,
        latitude: 0,
        images: [],
        savedImages: []
    }

    togglePost = () => {
        const { openPostCode } = this.state;
        this.setState({
            openPostCode: !openPostCode
        })
    }

    searchPlace = (e) => {
        this.togglePost();
    }

    selectAddress = async (data) => {
        this.togglePost();

        const { longitude, latitude } = await locationApi.getLocation(data.address);

        this.setState({
            address: data.address,
            longitude: longitude,
            latitude: latitude
        })
    }

    fileChangedHandler = async e => {
        const files = e.target.files;
        this.setState({
            images: files
        });

        const formData = new FormData();
        for (let i = 0; i < files.length; i++) {
            formData.append('files', files[i]);
        }

        const response = await imageApi.upload(formData);
        this.setState({
            savedImages: response.data
        });
    }

    render() {
        const { openPostCode,
            address,
            longitude,
            latitude
        } = this.state;
        const {
            searchPlace,
            selectAddress,
            fileChangedHandler,
        } = this;

        return (
            <div>
                <div>
                    <h3>여행지 추가</h3>
                    <Inputs msg='여행지 이름'>
                        <Textbox hint='여행지 이름' />
                    </Inputs>
                    <Inputs msg='솔직 후기'>
                        <Textbox hint='필수 입력' />
                    </Inputs>
                    <Inputs msg='꿀팁'>
                        <Textbox hint='선택 입력' />
                    </Inputs>
                    <Inputs msg='여행지 주소'>
                        <Textbox readOnly={true} hint='여행지 주소' value={address} />
                        <Button msg="검색" onClick={searchPlace} />
                    </Inputs>
                    <Inputs msg='여행지 경도'>
                        <Textbox readOnly={true} hint='여행지 경도' value={longitude} />
                        <Textbox readOnly={true} hint='여행지 위도' value={latitude} />
                    </Inputs>
                    <Inputs msg='여행지 사진'>
                        <Textbox hint='Upload Image' />
                        <input type="file" multiple
                            name="files"
                            onChange={fileChangedHandler} />
                    </Inputs>
                </div>
                {
                    openPostCode &&
                    <DaumPostcodeEmbed onComplete={selectAddress}
                        autoClose={false}
                        defaultQuery='판교역로235' />
                }
            </div>
        );
    }
}

export default ProductStepTwo;