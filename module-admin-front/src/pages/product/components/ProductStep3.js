import { Component } from 'react';
import Button from '../../../components/button/Button';
import Inputs from '../../../components/inputs/Inputs'

class ProductStep3 extends Component {

    state = {
        
    }

    render() {
        return (
            <div>
                <h3>미리 보기</h3>
                <Inputs msg='여행지 사진'>
                        <input type="file"
                            name="file"/>
                </Inputs>
                <Button msg="추가"/>
            </div>
        );
    }
}

export default ProductStep3;