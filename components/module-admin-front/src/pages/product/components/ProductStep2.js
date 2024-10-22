import { Component, createRef } from 'react';
import Textbox from '../../../components/textbox/Textbox'
import Inputs from '../../../components/inputs/Inputs'
import Button from '../../../components/button/Button'
import ComboBox from '../../../components/combobox/ComboBox'
import NumericInput from '../../../components/numeric/NumericInput'
import DaumPostcodeEmbed from 'react-daum-postcode';
import locationApi from '../../../components/locationApi'
import imageApi, { compressionOptions } from '../../../components/imageApi'
import imageCompression from 'browser-image-compression';
import './ProductStep.css'
import './Table.css'
import Loading from '../../../components/loading/Loading';

const TableContainer = ({ striped, children }) => (
    <table className={striped ? "table-striped" : ""}>{children}</table>
);
const TableHeader = ({ children }) => <thead>{children}</thead>;
const TableBody = ({ children }) => <tbody>{children}</tbody>;
const TableRow = ({ children }) => <tr>{children}</tr>;
const TableCell = ({ children }) => <td>{children}</td>;

class ProductStep2 extends Component {

    componentDidMount() {
        this.fileInputRef = createRef();
    }

    state = {
        isLoading: false,
        editable: false,
        editSpotSeq: 0,
        openPostCode: false,

        name: '',
        type: 'TOURSPOT',
        review: '',
        tip: '',
        address: '',
        longitude: 0,
        latitude: 0,
        savedImages: [],
        date: 0,
        nextSpotName: '',
        nextSpotSpentTime: 0,
        nextSpotVehicle: 'NONE',
        nextSpot: {},
    }

    togglePost = () => {
        const { openPostCode } = this.state;
        this.setState({
            openPostCode: !openPostCode
        })
    }

    selectAddress = async (data) => {
        this.togglePost();

        const response = await locationApi.getLocation(data.address);

        this.setState({
            address: data.address,
            longitude: response.data.longitude,
            latitude: response.data.latitude,
        })
    }

    fileChangedHandler = async e => {
        this.setState({
            isLoading: true,
        });

        const files = e.target.files;
        const formData = new FormData();
        for (let i = 0; i < files.length; i++) {
            const compressed = await imageCompression(files[i], compressionOptions);
            formData.append('files', compressed);
        }

        const response = await imageApi.upload(formData);
        this.setState({
            savedImages: response.data,
            isLoading: false,
        });
    }

    clear = () => {
        this.fileInputRef.current.value = null;
        
        this.setState({
            editable: false,
            editSpotSeq: 0,
            openPostCode: false,
            name: '',
            type: 'TOURSPOT',
            review: '',
            tip: '',
            address: '',
            longitude: 0,
            latitude: 0,
            savedImages: [],
            nextSpotName: '',
            nextSpotSpentTime: 0,
            nextSpotVehicle: 'NONE',
            nextSpot: {},
        });
    }

    editSpot = () => {
        const {
            editSpotSeq,
            name,
            review,
            tip,
            address,
            type,
            longitude,
            latitude,
            savedImages,
            date,
            nextSpotSpentTime,
            nextSpotVehicle,
        } = this.state;

        const newSpot = this.props.spots[editSpotSeq];
        newSpot.name = name;
        newSpot.review = review;
        newSpot.tip = tip;
        newSpot.type = type;
        newSpot.address = address;
        newSpot.longitude = longitude;
        newSpot.latitude = latitude;
        newSpot.savedImages = savedImages;
        newSpot.date = date;
        newSpot.spentTime = nextSpotSpentTime;
        newSpot.vehicle = nextSpotVehicle;

        const updatedSpots = [...this.props.spots];
        this.props.onChangeSpots(updatedSpots);

        this.clear();
    }

    addSpot = () => {
        const {
            name,
            review,
            tip,
            address,
            type,
            longitude,
            latitude,
            savedImages,
            date,
            nextSpotSpentTime,
            nextSpotVehicle,
        } = this.state;

        const newSpot = {
            seq: this.props.spots.length,
            name,
            review,
            tip,
            type,
            address,
            longitude,
            latitude,
            savedImages,
            date,
            spentTime: nextSpotSpentTime,
            vehicle: nextSpotVehicle,
        }

        const updatedSpots = [...this.props.spots, newSpot];
        this.props.onChangeSpots(updatedSpots);

        this.clear();
    }

    changeEditableMode = (spot, seq) => {
        this.setState({
            editable: true,
            editSpotSeq: seq,
            name: spot.name,
            review: spot.review,
            tip: spot.tip,
            type: spot.type,
            date: spot.date,
            address: spot.address,
            longitude: spot.longitude,
            latitude: spot.latitude,
            nextSpotSpentTime: spot.spentTime,
            nextSpotVehicle: spot.vehicle,
            savedImages: [...spot.savedImages]
        })
    }

    removeSpot = (spot) => {
        if (window.confirm(`[${spot.name}] 을 삭제하시겠습니까`)) {
            this.props.onChangeSpots(this.props.spots.filter(s => s !== spot));
            this.props.onDeleteSpot(spot);
        }
    }

    handleName = (e) => { this.setState({ name: e.target.value }) }
    handleReview = (e) => { this.setState({ review: e.target.value }) }
    handleTip = (e) => { this.setState({ tip: e.target.value }) }
    handleDate = (e) => { this.setState({ date: Number(e.target.value) }) }
    handleType = (e) => { this.setState({ type: e.target.value }) }
    handleNextSpotSpentTime = (e) => { this.setState({ nextSpotSpentTime: Number(e.target.value) }) }
    handleNextSpotVehicle = (e) => { this.setState({ nextSpotVehicle: e.target.value }) }
    handleLongitude = (e) => { this.setState({ longitude: Number(e.target.value) }) }
    handleLatitude = (e) => { this.setState({ latitude: Number(e.target.value) }) }

    render() {
        const {
            isLoading,
            editable,
            openPostCode,
            name,
            review,
            tip,
            type,
            date,
            address,
            longitude,
            latitude,
            nextSpotSpentTime,
            nextSpotVehicle
        } = this.state;
        const {
            handleName,
            handleReview,
            handleTip,
            handleType,
            handleDate,
            handleNextSpotSpentTime,
            handleNextSpotVehicle,
            handleLongitude,
            handleLatitude,
            selectAddress,
            fileChangedHandler,
            addSpot,
            editSpot,
            changeEditableMode,
            removeSpot,
        } = this;

        const {
            nextPage
        } = this.props;

        return (
            <>
                <div>
                    {isLoading ? <Loading/> : null}
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
                    <Inputs msg='유형'>
                        <ComboBox selectedValue={type} items={[
                            { value: 'TOURSPOT', label: '관광지' },
                            { value: 'CAFE', label: '카페' },
                            { value: 'RESTAURANT', label: '식당' },
                            { value: 'MUSEUM', label: '박물관' },
                            { value: 'BEACH', label: '바다' },
                            { value: 'BAR', label: '술집' },
                            { value: 'STORE', label: '잡화점' },
                            { value: 'ACCOMODATIONS', label: '숙소' },
                            { value: 'BAKERY', label: '베이커리' },
                            { value: 'LIQUORSTORE', label: '주류점' },
                            { value: 'BOOKSTORE', label: '서점' },
                            { value: 'MARKET', label: '시장' },
                            { value: 'ARTMUSEUM', label: '미술관' }
                        ]} onChange={handleType} />
                    </Inputs>
                    <Inputs msg='여행지 주소'>
                        <Textbox readOnly={true} hint='여행지 주소' value={address} />
                        <Button msg="검색" onClick={this.togglePost} />
                        { openPostCode && <DaumPostcodeEmbed onComplete={selectAddress} autoClose={false} defaultQuery={address} />}
                        <>
                            <Inputs msg='경도'>
                                <NumericInput hint='여행지 경도' value={longitude} onChange={handleLongitude}/>
                            </Inputs>
                            <Inputs msg='위도'>
                                <NumericInput hint='여행지 위도' value={latitude} onChange={handleLatitude}/>
                            </Inputs>
                        </>
                    </Inputs>
                    <Inputs msg='여행지 사진'>
                        <input type="file" multiple
                            ref={this.fileInputRef}
                            name="files"
                            onChange={fileChangedHandler} />
                    </Inputs>
                    <Inputs msg='해당 여행지 일정 일차'>
                        <NumericInput hint='몇 일차 여행지' value={date} onChange={handleDate} />
                    </Inputs>
                    <Inputs msg='다음 여행지까지'>
                        <Inputs msg='소요시간(분)'>
                            <NumericInput hint='다음 여행지까지 이동시간 (분)' value={nextSpotSpentTime} onChange={handleNextSpotSpentTime} />
                            <Inputs msg='이동수단'>
                                <ComboBox selectedValue={nextSpotVehicle} items={[
                                    { value: 'NONE', label: '없음' },
                                    { value: 'CAR', label: '승용차' },
                                    { value: 'PUBLIC', label: '대중교통' },
                                    { value: 'WALK', label: '도보' },
                                    { value: 'BICYCLE', label: '자전거' }
                                ]} onChange={handleNextSpotVehicle} />
                            </Inputs>
                        </Inputs>
                    </Inputs>
                    <div className="next-button-wrapper">
                        <Button primary={!editable} msg={editable ? "수정" : "추가"} onClick={editable ? editSpot : addSpot} />
                    </div>
                </div>
                <div>
                    <h3>여행지</h3>
                    <Inputs>
                        <TableContainer>
                            <TableHeader>
                                <TableRow>
                                    <TableCell>이름</TableCell>
                                    <TableCell>주소</TableCell>
                                    <TableCell>일차</TableCell>
                                    <TableCell>수정</TableCell>
                                    <TableCell>삭제</TableCell>
                                </TableRow>
                            </TableHeader>
                            <TableBody>
                                {
                                    this.props.spots.map((spot, seq) => (
                                        <TableRow key={seq} onClick={() => alert(seq)}>
                                            <TableCell>{spot.name}</TableCell>
                                            <TableCell>{spot.address}</TableCell>
                                            <TableCell>{spot.date}</TableCell>
                                            <TableCell><Button msg="선택" onClick={() => changeEditableMode(spot, seq)} /></TableCell>
                                            <TableCell><Button primary={false} msg="삭제" onClick={() => removeSpot(spot)} /></TableCell>
                                        </TableRow>
                                    ))
                                }
                            </TableBody>
                        </TableContainer>
                    </Inputs>
                </div>
                <div className="next-button-wrapper">
                    <Button msg="다음 2/3" onClick={nextPage} />
                </div>
            </>
        );
    }
}

export default ProductStep2;