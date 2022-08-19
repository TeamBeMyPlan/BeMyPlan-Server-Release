import { Component, createRef } from 'react';
import ComboBox from '../../../components/combobox/ComboBox';
import Textbox from '../../../components/textbox/Textbox'
import NumericInput from '../../../components/numeric/NumericInput'
import Inputs from '../../../components/inputs/Inputs'
import Button from '../../../components/button/Button'
import imageApi from '../../../components/imageApi'
import './ProductStep.css'

class ProductStep1 extends Component {
    componentDidMount() {
        this.fileInputRef = createRef();
    }

    state = {
        creator: '크리에이터',
        planTitle: '여행 일정',
        planDescription: '여행 일정이에요',
        thumbnail: '',
        concept: 'HEALING',
        partner: 'FAMILY',
        period: 0,
        cost: 0,
        vehicle: 'CAR',
        recommend: false,
        price: 0
    }

    handleCreatorName = (e) => { this.setState({ creator: e.target.value }) }

    handlePlanTitle = (e) => { this.setState({ planTitle: e.target.value }) }

    handlePlanDescription = (e) => { this.setState({ planDescription: e.target.value }) }

    handleConept = (e) => { this.setState({ concept: e.target.value }) }

    handlePartner = (e) => { this.setState({ partner: e.target.value }) }

    handlePeriod = (e) => { this.setState({ period: Number(e.target.value) }) }

    handleCost = (e) => { this.setState({ cost: Number(e.target.value) }) }

    handleVehicle = (e) => { this.setState({ vehicle: e.target.value }) }

    handleRecommend = (e) => { this.setState({ recommend: JSON.parse(e.target.value) }) }

    handlePrice = (e) => { this.setState({ price: Number(e.target.value) }) }


    fileChangedHandler = async e => {
        const files = e.target.files;
        const formData = new FormData();
        for (let i = 0; i < files.length; i++) {
            formData.append('files', files[i]);
        }

        const response = await imageApi.upload(formData);
        this.setState({
            thumbnail: response.data[0]
        });
    }

    saveAndNext = () => {
        const { creator,
            planTitle,
            planDescription,
            thumbnail,
            concept,
            partner,
            period,
            cost,
            vehicle,
            recommend,
            price } = this.state;
        const { nextPage, update } = this.props;

        update({
            creator,
            planTitle,
            planDescription,
            concept,
            thumbnail,
            partner,
            period,
            cost,
            vehicle,
            recommend,
            price
        });

        nextPage();
    }

    render() {
        const {
            handleCreatorName, handlePlanTitle, handlePlanDescription,
            handleConept, handlePartner, handlePeriod, handleCost,
            handleVehicle, handleRecommend, handlePrice,
            fileChangedHandler,
            saveAndNext } = this;

        const {
            creator, planTitle, planDescription,
            period, cost, price } = this.state;

        return (
            <>
                <div>
                    <h3>크리에이터 정보</h3>
                    <Inputs msg='닉네임'>
                        <Textbox hint='크리에이터 닉네임' value={creator} onChange={handleCreatorName} />
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
                        <Textbox hint='언제가지' value={period} onChange={handlePeriod} />
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