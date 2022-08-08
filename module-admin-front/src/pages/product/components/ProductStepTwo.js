import { Component } from 'react';
import Textbox from '../../../components/textbox/Textbox'
import Inputs from '../../../components/inputs/Inputs'
import Button from '../../../components/button/Button'
import DaumPostcodeEmbed from 'react-daum-postcode';
import locationApi from '../../../components/locationApi'
import imageApi from '../../../components/imageApi'

const TableContainer = ({ striped, children }) => (
    <table className={striped ? "table-striped" : ""}>{children}</table>
);
const TableHeader = ({ children }) => <thead>{children}</thead>;
const TableBody = ({ children }) => <tbody>{children}</tbody>;
const TableRow = ({ children }) => <tr>{children}</tr>;
const TableCell = ({ children }) => <td>{children}</td>;

class ProductStepTwo extends Component {

    state = {
        openPostCode: false,
        name: '',
        review: '',
        tip: '',
        address: '',
        longitude: 0,
        latitude: 0,
        images: [],
        savedImages: [],
        spots: []
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

    clear = () => {
        this.setState({
            openPostCode: false,
            name: '',
            review: '',
            tip: '',
            address: '',
            longitude: 0,
            latitude: 0,
            images: [],
            savedImages: [],
        });
    }

    addItem = () => {
        const {
            name,
            review,
            tip,
            address,
            longitude,
            latitude,
            spots
        } = this.state;
        
        const newSpot = {
            name: name,
            address: address
        }

        this.setState({
            spots: [...spots, newSpot]
        });

        this.clear();
    }

    handleName = (e) => { this.setState({ name: e.target.value }) }
    handleReview = (e) => { this.setState({ review: e.target.value }) }
    handleTip = (e) => { this.setState({ tip: e.target.value }) }

    render() {
        const { openPostCode,
            name,
            review,
            tip,
            address,
            longitude,
            latitude,
            spots
        } = this.state;
        const {
            handleName,
            handleReview,
            handleTip,
            searchPlace,
            selectAddress,
            fileChangedHandler,
            addItem
        } = this;

        return (
            <div>
                <div>
                    <h3>여행지 추가</h3>
                    <Inputs msg='여행지 이름'>
                        <Textbox hint='여행지 이름' value={name} onChange={handleName} />
                    </Inputs>
                    <Inputs msg='솔직 후기'>
                        <Textbox hint='필수 입력' value={review} onChange={handleReview}/>
                    </Inputs>
                    <Inputs msg='꿀팁'>
                        <Textbox hint='선택 입력' value={tip} onChange={handleTip}/>
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
                    <Button msg="추가" onClick={addItem} />
                </div>
                <div>
                    <h3>여행지</h3>
                    <TableContainer>
                        <TableHeader>
                            <TableRow>
                                <TableCell>이름</TableCell>
                                <TableCell>주소</TableCell>
                                <TableCell>일차</TableCell>
                            </TableRow>
                        </TableHeader>
                        <TableBody>
                            {
                                spots.map((spot, index) => (
                                    <TableRow key={index}>
                                        <TableCell>{spot.name}</TableCell>
                                        <TableCell>{spot.address}</TableCell>
                                        <TableCell>1</TableCell>
                                    </TableRow>        
                                ))
                            }
                        </TableBody>
                    </TableContainer>
                  
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