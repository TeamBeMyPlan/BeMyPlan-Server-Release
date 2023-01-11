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
        openPostCode: false,
        selectedAddress: false,
        selectedNextSpot: false,
        openNextSpots: false,

        name: '',
        type: 'TOURSPOT',
        review: '',
        tip: '',
        address: '',
        longitude: 0,
        latitude: 0,
        savedImages: [],
        date: 0,
        spots: [],
        nextSpotName: '',
        nextSpotSpentTime: 0,
        nextSpotVehicle: 'CAR',
        nextSpot: {},
    }

    togglePost = () => {
        const { openPostCode } = this.state;
        this.setState({
            openPostCode: !openPostCode
        })
    }

    toggleNextSpot = () => {
        const { openNextSpots } = this.state;
        this.setState({
            openNextSpots: !openNextSpots
        })
    }

    selectAddress = async (data) => {
        this.togglePost();

        const response = await locationApi.getLocation(data.address);

        this.setState({
            address: data.address,
            longitude: response.data.longitude,
            latitude: response.data.latitude,
            selectedAddress: true
        })
    }

    fileChangedHandler = async e => {
        const files = e.target.files;
        const formData = new FormData();
        for (let i = 0; i < files.length; i++) {
            const compressed = await imageCompression(files[i], compressionOptions);
            formData.append('files', compressed);
        }

        const response = await imageApi.upload(formData);
        this.setState({
            savedImages: response.data
        });
    }

    clear = () => {
        this.fileInputRef.current.value = null;
        
        this.setState({
            openPostCode: false,
            selectedAddress: false,
            selectedNextSpot: false,
            openNextSpots: false,
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
            nextSpotVehicle: 'CAR',
            nextSpot: {},
        });
    }

    selectNextSpot = (index) => {
        const { spots } = this.state;

        this.setState({
            nextSpot: spots[index],
            nextSpotName: spots[index].name,
            selectedNextSpot: true
        });

        this.toggleNextSpot();
    }

    addItem = () => {
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
            nextSpot,
            nextSpotSpentTime,
            nextSpotVehicle,
            spots,
            selectedNextSpot
        } = this.state;

        const newSpot = {
            id: spots.length,
            name,
            review,
            tip,
            type,
            address,
            longitude,
            latitude,
            savedImages,
            date,
            nextSpot: {},
            hasNext: false
        }

        if (selectedNextSpot) {
            newSpot.hasNext = true;
            newSpot.nextSpot = {
                id: nextSpot.id,
                spentTime: nextSpotSpentTime,
                vehicle: nextSpotVehicle,
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
    handleDate = (e) => { this.setState({ date: Number(e.target.value) }) }
    handleType = (e) => { this.setState({ type: e.target.value }) }
    handleNextSpotSpentTime = (e) => { this.setState({ nextSpotSpentTime: Number(e.target.value) }) }
    handleNextSpotVehicle = (e) => { this.setState({ nextSpotVehicle: e.target.value }) }

    saveAndNext = () => {
        const { nextPage, update } = this.props;
        const { spots } = this.state;

        update({spots});
        nextPage();
    }

    render() {
        const {
            openPostCode,
            name,
            review,
            tip,
            date,
            address,
            selectedAddress,
            longitude,
            latitude,
            openNextSpots,
            selectedNextSpot,
            spots,
            nextSpotName,
            nextSpotSpentTime
        } = this.state;
        const {
            handleName,
            handleReview,
            handleTip,
            handleType,
            handleDate,
            handleNextSpotSpentTime,
            handleNextSpotVehicle,
            selectAddress,
            fileChangedHandler,
            toggleNextSpot,
            selectNextSpot,
            addItem,
            saveAndNext
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
                    <Inputs msg='유형'>
                        <ComboBox items={[
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
                            ref={this.fileInputRef}
                            name="files"
                            onChange={fileChangedHandler} />
                    </Inputs>
                    <Inputs msg='해당 여행지 일정 정보'>
                        <NumericInput hint='몇 일차 여행지' value={date} onChange={handleDate} />
                    </Inputs>
                    <Inputs msg='연결할 다음 여행지 (Optional)'>
                        <Textbox readOnly={true} hint='다음 여행지' value={nextSpotName} />
                        <Button msg="선택" onClick={toggleNextSpot} />
                        {
                            openNextSpots &&
                            <TableContainer>
                                <TableHeader>
                                    <TableRow>
                                        <TableCell>ID</TableCell>
                                        <TableCell>이름</TableCell>
                                        <TableCell>주소</TableCell>
                                        <TableCell>선택</TableCell>
                                    </TableRow>
                                </TableHeader>
                                <TableBody>
                                    {
                                        spots.map((spot, index) => (
                                            <TableRow key={index}>
                                                <TableCell>{spot.id}</TableCell>
                                                <TableCell>{spot.name}</TableCell>
                                                <TableCell>{spot.address}</TableCell>
                                                <TableCell><Button onClick={() => selectNextSpot(index)} msg="선택" /></TableCell>
                                            </TableRow>
                                        ))
                                    }
                                </TableBody>
                            </TableContainer>
                        }
                        {
                            selectedNextSpot &&
                            <>
                                <Inputs msg='소요시간(분)'>
                                    <NumericInput hint='다음 여행지까지 이동시간 (분)' value={nextSpotSpentTime} onChange={handleNextSpotSpentTime} />
                                </Inputs>
                                <Inputs msg='이동수단'>
                                    <ComboBox items={[
                                        { value: 'CAR', label: '승용차' },
                                        { value: 'PUBLIC', label: '대중교통' },
                                        { value: 'WALK', label: '도보' },
                                        { value: 'BICYCLE', label: '자전거' }
                                    ]} onChange={handleNextSpotVehicle} />
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
                    <Inputs>
                        <TableContainer>
                            <TableHeader>
                                <TableRow>
                                    <TableCell>ID</TableCell>
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
                                            <TableCell>{spot.id}</TableCell>
                                            <TableCell>{spot.name}</TableCell>
                                            <TableCell>{spot.address}</TableCell>
                                            <TableCell>{spot.date}</TableCell>
                                            {
                                               spot.hasNext && <TableCell>{spot.nextSpot.id}</TableCell>
                                            }
                                        </TableRow>
                                    ))
                                }
                            </TableBody>
                        </TableContainer>
                    </Inputs>
                </div>
                <div className="next-button-wrapper">
                    <Button msg="다음 2/3" onClick={saveAndNext} />
                </div>
            </>
        );
    }
}

export default ProductStep2;