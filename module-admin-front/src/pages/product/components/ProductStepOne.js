import { Component, useState } from 'react';
import ComboBox from '../../../components/combobox/ComboBox';
import Textbox from '../../../components/textbox/Textbox'
import NumericInput from '../../../components/numeric/NumericInput'
import Inputs from '../../../components/inputs/Inputs'

const tempComboBoxItems = [
    { value: true, label: 'Y' },
    { value: false, label: 'N' }
]

class ProductStepOne extends Component {
    render() {
        const {
            creator,
            plan,
            handleCreatorName,
            handlePlanTitle,
            handlePlanDescription,
            handlePlanConcept,
            handlePartner,
            handlePeriod,
            handleCost,
            handleVehicle,
            handleRecommend
        } = this.props;

        return (
            <div>
                <h3>크리에이터 정보</h3>
                <Inputs msg='닉네임'>
                    <Textbox hint='크리에이터 닉네임' value={creator.name} onChange={handleCreatorName} />
                </Inputs>
                <Inputs msg='여행일정제목'>
                    <Textbox hint='여행 일정 제목' value={plan.title} onChange={handlePlanTitle} />
                </Inputs>
                <Inputs msg='여행 일정 소개글'>
                    <Textbox hint='여행 일정 소개글' value={plan.description} onChange={handlePlanDescription} />
                </Inputs>

                <h3>일정정보</h3>
                <Inputs msg='컨셉'>
                    <ComboBox items={tempComboBoxItems} onChange={handlePlanConcept} />
                </Inputs>
                <Inputs msg='여행파트너'>
                    <ComboBox items={tempComboBoxItems} />
                </Inputs>
                <Inputs msg='여행 경비'>
                    <NumericInput hint='1개' />
                </Inputs>
                <Inputs msg='이동수단'>
                    <ComboBox items={tempComboBoxItems} />
                </Inputs>
                <Inputs msg='여행시기'>
                    <Textbox hint='언제가지' />
                </Inputs>
                <Inputs msg='가격'>
                    <NumericInput hint='1개' />
                </Inputs>
                <Inputs msg='추천여부'>
                    <ComboBox items={tempComboBoxItems} />
                </Inputs>
            </div>
        );
    }
}

export default ProductStepOne;