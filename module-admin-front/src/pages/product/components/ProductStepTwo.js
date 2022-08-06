import { Component, useState } from 'react';
import Textbox from '../../../components/textbox/Textbox'
import Inputs from '../../../components/inputs/Inputs'
import Button from '../../../components/button/Button'
import DaumPostcodeEmbed from 'react-daum-postcode';

class ProductStepTwo extends Component {

    state = {
        openPostCode: false,
        address: ''
    }

    uploadImage = (e) => {
        alert('hi');
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

    selectAddress = (data) => {
        this.togglePost();
        console.log(data);

        this.setState({
            address: data.address
        })
    }

    render() {
        const { openPostCode,
            address
        } = this.state;
        const {
            searchPlace,
            uploadImage,
            selectAddress
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
                        <Textbox readOnly={true} hint='여행지 경도' />
                        <Textbox readOnly={true} hint='여행지 위도' />
                    </Inputs>
                    <Inputs msg='여행지 사진'>
                        <Textbox hint='Upload Image' />
                        <Button msg="Upload" onClick={uploadImage} />
                    </Inputs>
                </div>
                {
                    openPostCode &&
                    <DaumPostcodeEmbed onComplete={selectAddress}
                        autoClose={false}
                        defaultQuery='판교역료 235' />
                }
            </div>
        );
    }
}

export default ProductStepTwo;