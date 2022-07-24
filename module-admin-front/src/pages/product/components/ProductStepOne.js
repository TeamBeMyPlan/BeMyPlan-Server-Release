import { useState } from 'react';
import ComboBox from '../../../components/combobox/ComboBox';
import Textbox from '../../../components/textbox/Textbox'
import NumericInput from '../../../components/numeric/NumericInput'
import Inputs from '../../../components/inputs/Inputs'

const ProductStepOne = () => {
    const [value, setValue] = useState('');

    function handleChange(e) {
        setValue(e.target.value);
    }

    return (
        <div>
            <h3>크리에이터 정보</h3>
            <Inputs msg='닉네임'>
                <Textbox hint='크리에이터 닉네임' value={value} onChange={handleChange}/>
            </Inputs>
            <Inputs msg='여행일정제목'>
                <Textbox hint='여행 일정 제목' value={value} onChange={handleChange}/>
            </Inputs>
            <Inputs msg='크리에이터 소개글'>
                <Textbox hint='크리에이터 소개글' value={value} onChange={handleChange}/>
            </Inputs>
        
            
            <h3>일정정보</h3>
            <Inputs msg='컨셉'>
                <ComboBox/>
            </Inputs>
            <Inputs msg='여행 장소 수'>
                <NumericInput hint='1개'/>
            </Inputs>
            <Inputs msg='식당 수'>
                <NumericInput hint='1개'/>
            </Inputs>
            <Inputs msg='여행파트너'>
                <ComboBox/>
            </Inputs>
            <Inputs msg='여행 일수'>
                <NumericInput hint='1개'/>
            </Inputs>
            <Inputs msg='여행 경비'>
                <NumericInput hint='1개'/>
            </Inputs>
            <Inputs msg='이동수단'>
                <ComboBox/>
            </Inputs>
            <Inputs msg='여행시기'>
                <Textbox hint='언제가지'/>
            </Inputs>
            <Inputs msg='가격'>
                <NumericInput hint='1개'/>
            </Inputs>
            <Inputs msg='추천여부'>
                <ComboBox/>
            </Inputs>
        </div>
    );
}

export default ProductStepOne;