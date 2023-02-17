import { Component, createRef } from 'react';
import ComboBox from '../../../components/combobox/ComboBox';
import Textbox from '../../../components/textbox/Textbox'
import NumericInput from '../../../components/numeric/NumericInput'
import Inputs from '../../../components/inputs/Inputs'
import Button from '../../../components/button/Button'
import './ProductStep.css'

class ProductStep1 extends Component {
    async componentDidMount() {
        this.fileInputRef = createRef();
    }

    render() {
        const {
            onChangeCreator,
            onChangePlanTitle,
            onChangePlanDescription,
            onChangeConcept,
            onChangePartner,
            onChangePeriod,
            onChangeCost,
            onChangeVehicle,
            onChangeRecommend,
            onChangeRegion,
            onChangePrice,
            onChangeTags,
            onChangeRecommendTargets,
            onChangeThumbnail,
            nextPage
        } = this.props;

        const CreatorSelect = this.props.creators.map(creator => {
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
                        <ComboBox selectedValue={this.props.creatorId} items={CreatorSelect} onChange={onChangeCreator} />
                    </Inputs>
                    <Inputs msg='여행일정제목'>
                        <Textbox hint='여행 일정 제목' value={this.props.planTitle} onChange={onChangePlanTitle} />
                    </Inputs>
                    <Inputs msg='여행 일정 소개글'>
                        <Textbox hint='여행 일정 소개글' value={this.props.planDescription} onChange={onChangePlanDescription} />
                    </Inputs>
                    <Inputs msg='여행지 섬네일'>
                        <input type="file"
                            ref={this.fileInputRef}
                            name="files"
                            onChange={onChangeThumbnail} />
                    </Inputs>
                    <h3>일정정보</h3>
                    <Inputs msg='컨셉'>
                        <ComboBox selectedValue={this.props.concept} items={[
                            { value: 'HEALING', label: '힐링' },
                            { value: 'EATING', label: '맛집' },
                            { value: 'HOTPLACE', label: '핫플' },
                            { value: 'LIFESHOT', label: '인생샷' },
                            { value: 'LOCAL', label: '로컬' },
                            { value: 'ACTIVITY', label: '액티비티' },
                            { value: 'CAMPING', label: '캠핑' }
                        ]} onChange={onChangeConcept} />
                    </Inputs>
                    <Inputs msg='지역'>
                        <ComboBox items={[
                            { value: 'JEJU', label: '제주' },
                        ]} />
                    </Inputs>
                    <Inputs msg='지역 상세'>
                        <ComboBox selectedValue={this.props.region} items={[
                            { value: 'JEJUALL', label: '제주 전체' },
                            { value: 'JEJUWEST', label: '제주 서부' },
                            { value: 'JEJUEAST', label: '제주 동부' },
                            { value: 'JEJUCITY', label: '제주 시내' },
                        ]} onChange={onChangeRegion} />
                    </Inputs>
                    <Inputs msg='여행파트너'>
                        <ComboBox selectedValue={this.props.partner} items={[
                            { value: 'FAMILY', label: '가족' },
                            { value: 'FRIEND', label: '친구' },
                            { value: 'COUPLE', label: '연인' },
                            { value: 'SOLO', label: '혼자' },
                            { value: 'DOG', label: '반려견' }
                        ]} onChange={onChangePartner} />
                    </Inputs>
                    <Inputs msg='여행 경비'>
                        <NumericInput hint='1개' value={this.props.cost} onChange={onChangeCost} />
                    </Inputs>
                    <Inputs msg='이동수단'>
                        <ComboBox selectedValue={this.props.vehicle} items={[
                            { value: 'CAR', label: '승용차' },
                            { value: 'PUBLIC', label: '대중교통' },
                            { value: 'WALK', label: '도보' },
                            { value: 'BICYCLE', label: '자전거' }
                        ]} onChange={onChangeVehicle} />
                    </Inputs>
                    <Inputs msg='여행시기'>
                        <ComboBox selectedValue={this.props.period} items={[
                            { value: 1, label: '1월' },
                            { value: 2, label: '2월' },
                            { value: 3, label: '3월' },
                            { value: 4, label: '4월' },
                            { value: 5, label: '5월' },
                            { value: 6, label: '6월' },
                            { value: 7, label: '7월' },
                            { value: 8, label: '8월' },
                            { value: 9, label: '9월' },
                            { value: 10, label: '10월' },
                            { value: 11, label: '11월' },
                            { value: 12, label: '12월' },
                        ]} onChange={onChangePeriod} />
                    </Inputs>
                    <Inputs msg='가격'>
                        <NumericInput hint='1개' value={this.props.price} onChange={onChangePrice} />
                    </Inputs>
                    <Inputs msg='추천여부'>
                        <ComboBox selectedValue={this.props.recommend} items={[
                            { value: false, label: 'No' },
                            { value: true, label: 'Yes' }
                        ]} onChange={onChangeRecommend} />
                    </Inputs>
                    <Inputs msg='해쉬태그'>
                        <Textbox hint='해쉬태그' value={this.props.tags} onChange={onChangeTags} />
                    </Inputs>
                    <Inputs msg='이런 분들에게 추천해요'>
                        <Textbox hint='이런 분들에게 추천해요' value={this.props.recommendTargets} onChange={onChangeRecommendTargets} />
                    </Inputs>
                </div>
                <div className="next-button-wrapper">
                    <Button msg="다음 1/3" onClick={nextPage} />
                </div>
            </>
        );
    }
}

export default ProductStep1;