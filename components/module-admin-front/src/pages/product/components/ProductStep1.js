import { Component, createRef } from 'react';
import ComboBox from '../../../components/combobox/ComboBox';
import Textbox from '../../../components/textbox/Textbox'
import NumericInput from '../../../components/numeric/NumericInput'
import Inputs from '../../../components/inputs/Inputs'
import Button from '../../../components/button/Button'
import imageApi, { compressionOptions } from '../../../components/imageApi'
import creatorApi from '../../../components/creatorApi'
import imageCompression from 'browser-image-compression';
import './ProductStep.css'

class ProductStep1 extends Component {
    async componentDidMount() {
        this.fileInputRef = createRef();

        await this.fetchCreators();
    }

    state = {
        creators: [],
        creatorId: 0,
        planTitle: '여행 일정',
        planDescription: '여행 일정이에요',
        thumbnail: '',
        concept: 'HEALING',
        partner: 'FAMILY',
        period: 0,
        cost: 0,
        vehicle: 'CAR',
        recommend: false,
        region: 'JEJUALL',
        price: 0
    }

    async fetchCreators() {
        const creators = await creatorApi.getCreators();
        this.setState({
            creators: [...creators.data]
        })
    }

    handleCreatorName = (e) => { 
        this.setState({ 
            creatorId: e.target.value
        }) }

    handlePlanTitle = (e) => { this.setState({ planTitle: e.target.value }) }

    handlePlanDescription = (e) => { this.setState({ planDescription: e.target.value }) }

    handleConept = (e) => { this.setState({ concept: e.target.value }) }

    handlePartner = (e) => { this.setState({ partner: e.target.value }) }

    handlePeriod = (e) => { 
        this.setState({ period: Number(e.target.value) }) 
    }

    handleCost = (e) => { this.setState({ cost: Number(e.target.value) }) }

    handleVehicle = (e) => { this.setState({ vehicle: e.target.value }) }

    handleRecommend = (e) => { this.setState({ recommend: JSON.parse(e.target.value) }) }

    handlePrice = (e) => { this.setState({ price: Number(e.target.value) }) }

    handleRegion = (e) => { this.setState({ region: e.target.value })}

    fileChangedHandler = async e => {
        const files = e.target.files;
        const formData = new FormData();

        for (let i = 0; i < files.length; i++) {
            const compressed = await imageCompression(files[i], compressionOptions);
            formData.append('files', compressed);
        }

        const response = await imageApi.upload(formData);
        this.setState({
            thumbnail: response.data[0]
        });
    }

    saveAndNext = () => {
        const { creatorId,
            planTitle,
            planDescription,
            thumbnail,
            concept,
            partner,
            period,
            cost,
            vehicle,
            recommend,
            region,
            price } = this.state;
        const { nextPage, update } = this.props;

        update({
            creatorId,
            planTitle,
            planDescription,
            concept,
            thumbnail,
            partner,
            period,
            cost,
            vehicle,
            recommend,
            price,
            region
        });

        nextPage();
    }

    render() {
        const {
            handleCreatorName, handlePlanTitle, handlePlanDescription,
            handleConept, handlePartner, handlePeriod, handleCost, handleRegion,
            handleVehicle, handleRecommend, handlePrice, 
            fileChangedHandler,
            saveAndNext } = this;

        const {
            creators, planTitle, planDescription, cost, price } = this.state;

        const CreatorSelect = creators.map(creator => {
            return {
                value: creator.id, 
                label: creator.name
            }
        });

        return (
            <>
                <div>
                    <h3>크리에이터 정보</h3>
                    <Inputs msg='닉네임'>
                        <ComboBox items={CreatorSelect} onChange={handleCreatorName}/>
                        {/* <Textbox hint='크리에이터 닉네임' value={creator} onChange={handleCreatorName} /> */}
                    </Inputs>
                    <Inputs msg='여행일정제목'>
                        <Textbox hint='여행 일정 제목' value={planTitle} onChange={handlePlanTitle} />
                    </Inputs>
                    <Inputs msg='여행 일정 소개글'>
                        <Textbox hint='여행 일정 소개글' value={planDescription} onChange={handlePlanDescription} />
                    </Inputs>
                    <Inputs msg='여행지 섬네일'>
                        <input type="file"
                            ref={this.fileInputRef}
                            name="files"
                            onChange={fileChangedHandler} />
                    </Inputs>
                    <h3>일정정보</h3>
                    <Inputs msg='컨셉'>
                        <ComboBox items={[
                            { value: 'HEALING', label: '힐링' },
                            { value: 'EATING', label: '맛집' },
                            { value: 'HOTPLACE', label: '핫플' },
                            { value: 'LIFESHOT', label: '인생샷' },
                            { value: 'LOCAL', label: '로컬' },
                            { value: 'ACTIVITY', label: '액티비티' },
                            { value: 'CAMPING', label: '캠핑' }
                        ]} onChange={handleConept} />
                    </Inputs>
                    <Inputs msg='지역'>
                        <ComboBox items={[
                            { value: 'JEJU', label: '제주' },
                        ]}/>
                    </Inputs>
                    <Inputs msg='지역 상세'>
                        <ComboBox items={[
                            { value: 'JEJUALL', label: '제주 전체' },
                            { value: 'JEJUWEST', label: '제주 서부' },
                            { value: 'JEJUEAST', label: '제주 동부' },
                            { value: 'JEJUCITY', label: '제주 시내' },
                        ]} onChange={handleRegion} />
                    </Inputs>
                    <Inputs msg='여행파트너'>
                        <ComboBox items={[
                            { value: 'FAMILY', label: '가족' },
                            { value: 'FRIEND', label: '친구' },
                            { value: 'COUPLE', label: '연인' },
                            { value: 'SOLO', label: '혼자' },
                            { value: 'DOG', label: '반려견' }
                        ]} onChange={handlePartner} />
                    </Inputs>
                    <Inputs msg='여행 경비'>
                        <NumericInput hint='1개' value={cost} onChange={handleCost} />
                    </Inputs>
                    <Inputs msg='이동수단'>
                        <ComboBox items={[
                            { value: 'CAR', label: '승용차' },
                            { value: 'PUBLIC', label: '대중교통' },
                            { value: 'WALK', label: '도보' },
                            { value: 'BICYCLE', label: '자전거' }
                        ]} onChange={handleVehicle} />
                    </Inputs>
                    <Inputs msg='여행시기'>
                        <ComboBox items={[
                            { value: '1', label: '1월' },
                            { value: '2', label: '2월' },
                            { value: '3', label: '3월' },
                            { value: '4', label: '4월' },
                            { value: '5', label: '5월' },
                            { value: '6', label: '6월' },
                            { value: '7', label: '7월' },
                            { value: '8', label: '8월' },
                            { value: '9', label: '9월' },
                            { value: '10', label: '10월' },
                            { value: '11', label: '11월' },
                            { value: '12', label: '12월' },
                        ]} onChange={handlePeriod}/>
                    </Inputs>
                    <Inputs msg='가격'>
                        <NumericInput hint='1개' value={price} onChange={handlePrice} />
                    </Inputs>
                    <Inputs msg='추천여부'>
                        <ComboBox items={[
                            { value: false, label: 'No' },
                            { value: true, label: 'Yes' }
                        ]} onChange={handleRecommend} />
                    </Inputs>

                </div>
                <div className="next-button-wrapper">
                    <Button msg="다음 1/3" onClick={saveAndNext} />
                </div>
            </>
        );
    }
}

export default ProductStep1;