import { Component } from 'react';
import Textbox from '../../../components/textbox/Textbox'
import Inputs from '../../../components/inputs/Inputs'
import Button from '../../../components/button/Button'
import NumericInput from '../../../components/numeric/NumericInput'
import DaumPostcodeEmbed from 'react-daum-postcode';
import locationApi from '../../../components/locationApi'
import imageApi from '../../../components/imageApi'
import './ProductStep.css'

const TableContainer = ({ striped, children }) => (
    <table className={striped ? "table-striped" : ""}>{children}</table>
);
const TableHeader = ({ children }) => <thead>{children}</thead>;
const TableBody = ({ children }) => <tbody>{children}</tbody>;
const TableRow = ({ children }) => <tr>{children}</tr>;
const TableCell = ({ children }) => <td>{children}</td>;

class ProductStep2 extends Component {

    state = {
        openPostCode: false,
        name: '',
        review: '',
        tip: '',
        address: '',
        selectedAddress: false,
        longitude: 0,
        latitude: 0,
        images: [],
        savedImages: [],
        date: 0,
        spots: [],
        otherSpotSpentTime: 0,
        otherSpotVehicle: '',
        otherSpot: {},
        selectedOtherSpot: false,
        openOtherSpots: false
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
            latitude: latitude,
            selectedAddress: true
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
            date: 0,
            otherSpot: {},
            openOtherSpots: false
        });
    }

    toggleOtherSpot = () => {
        const { openOtherSpots } = this.state;
        this.setState({
            openOtherSpots: !openOtherSpots
        })
    }

    selectOtherSpot = (index) => {
        const { spots } = this.state;

        this.setState({
            otherSpot: spots[index],
            selectedOtherSpot: true
        });

        this.toggleOtherSpot();
    }

    addItem = () => {
        const {
            name,
            review,
            tip,
            address,
            longitude,
            latitude,
            savedImages,
            date,
            otherSpot,
            otherSpotSpentTime,
            otherSpotVehicle,
            spots
        } = this.state;

        const newSpot = {
            name,
            review,
            tip,
            address,
            longitude,
            latitude,
            savedImages,
            date,
            otherSpot: {
                spot: otherSpot,
                spentTime: otherSpotSpentTime,
                vehicle: otherSpotVehicle,
            }
        }

        this.setState({
            spots: [...spots, newSpot]
        });

        this.clear();
    }

    handleName = (e) => { this.setState({ name: e.target.value }) }
    handleReview = (e) => { this.setState({ review: e.target.value }) }
    handleTip = (e) => { this.setState({ tip: e.target.value }) }
    handleDate = (e) => { this.setState({ date: e.target.value }) }
    handleOtherSpotSpentTime = (e) => { this.setState({ otherSpotSpentTime: e.target.value }) }
    handleOtherSpotVehicle = (e) => { this.setState({ otherSpotVehicle: e.target.value }) }

    render() {
        const { 
            openPostCode,
            name,
            review,
            tip,
            address,
            selectedAddress,
            longitude,
            latitude,
            openOtherSpots,
            selectedOtherSpot,
            spots,
            otherSpot,
            otherSpotSpentTime,
            otherSpotVehicle
        } = this.state;
        const {
            handleName,
            handleReview,
            handleTip,
            handleDate,
            handleOtherSpotSpentTime,
            handleOtherSpotVehicle,
            searchPlace,
            selectAddress,
            fileChangedHandler,
            toggleOtherSpot,
            selectOtherSpot,
            addItem,
        } = this;

        return (
            <>
                <div>
                    <h3>여행지 추가</h3>
                    <Inputs msg='여행지 이름'>
                        <Textbox hint='여행지 이름' value={name} onChange={handleName} />
                    </Inputs>
                    <Inputs msg='솔직 후기'>
                        <Textbox hint='필수 입력' value={review} onChange={handleReview} />
                    </Inputs>
                    <Inputs msg='꿀팁'>
                        <Textbox hint='선택 입력' value={tip} onChange={handleTip} />
                    </Inputs>
                    <Inputs msg='여행지 주소'>
                        <Textbox readOnly={true} hint='여행지 주소' value={address} />
                        <Button msg="검색" onClick={searchPlace} />
                        {
                            openPostCode &&
                            <DaumPostcodeEmbed onComplete={selectAddress}
                                autoClose={false}
                                defaultQuery={address} />
                        }
                        {
                            selectedAddress &&
                            <>
                                <Inputs msg='경도'>
                                    <Textbox readOnly={true} hint='여행지 경도' value={longitude} />
                                </Inputs>
                                <Inputs msg='위도'>
                                    <Textbox readOnly={true} hint='여행지 위도' value={latitude} />
                                </Inputs>
                            </>
                        }
                    </Inputs>
                    <Inputs msg='여행지 사진'>
                        <input type="file" multiple
                            name="files"
                            onChange={fileChangedHandler} />
                    </Inputs>
                    <Inputs msg='해당 여행지 일정 정보'>
                        <NumericInput hint='몇 일차 여행지' onChange={handleDate} />
                    </Inputs>
                    <Inputs msg='연결할 다음 여행지 (Optional)'>
                        <Textbox readOnly={true} hint='다음 여행지' value={otherSpot.name} />
                        <Button msg="선택" onClick={toggleOtherSpot} />
                        {
                            openOtherSpots &&
                            <TableContainer>
                                <TableHeader>
                                    <TableRow>
                                        <TableCell>이름</TableCell>
                                        <TableCell>주소</TableCell>
                                        <TableCell>선택</TableCell>
                                    </TableRow>
                                </TableHeader>
                                <TableBody>
                                    {
                                        spots.map((spot, index) => (
                                            <TableRow key={index}>
                                                <TableCell>{spot.name}</TableCell>
                                                <TableCell>{spot.address}</TableCell>
                                                <TableCell><Button onClick={() => selectOtherSpot(index)}>선택</Button></TableCell>
                                            </TableRow>
                                        ))
                                    }
                                </TableBody>
                            </TableContainer>
                        }
                        {
                            selectedOtherSpot &&
                            <>
                                <Inputs msg='소요시간(분)'>
                                    <NumericInput hint='다음 여행지까지 이동시간 (분)' value={otherSpotSpentTime} onChange={handleOtherSpotSpentTime} />
                                </Inputs>
                                <Inputs msg='이동수단'>
                                    <Textbox hint='다음 여행지까지 이동수단' value={otherSpotVehicle} onChange={handleOtherSpotVehicle} />
                                </Inputs>
                            </>
                        }
                    </Inputs>
                    <div className="next-button-wrapper">
                        <Button msg="추가" onClick={addItem} />
                    </div>                    
                </div>
                <div>
                    <h3>여행지</h3>
                    <TableContainer>
                        <TableHeader>
                            <TableRow>
                                <TableCell>이름</TableCell>
                                <TableCell>주소</TableCell>
                                <TableCell>일차</TableCell>
                                <TableCell>다음 여행지</TableCell>
                            </TableRow>
                        </TableHeader>
                        <TableBody>
                            {
                                spots.map((spot, index) => (
                                    <TableRow key={index}>
                                        <TableCell>{spot.name}</TableCell>
                                        <TableCell>{spot.address}</TableCell>
                                        <TableCell>{spot.date}</TableCell>
                                        <TableCell>{spot.otherSpot.spot.name}</TableCell>
                                    </TableRow>
                                ))
                            }
                        </TableBody>
                    </TableContainer>

                </div>
                <div className="next-button-wrapper">
                    <Button msg="다음 2/3"  onClick={this.props.nextPage}/>
                </div>
            </>
        );
    }
}

export default ProductStep2;