import { Component } from 'react';
import Button from '../../components/button/Button';
import Inputs from '../../components/inputs/Inputs';
import planApi from '../../components/planApi';
import ProductTemplate from './components/ProductTemplate'

class ProductListPage extends Component {
    state = {
        plans: []
    }

    async componentDidMount() {
        await this.fetchPlans();
    }

    async fetchPlans() {
        const plans = await planApi.list();
        if (plans.code === 200) {
            console.log(plans.data);

            this.setState({
                plans: [...plans.data]
            })
        }
    }

    render() {
        const { plans } = this.state;
        const planList = plans.map(plan => 
            <Inputs msg={plan.title}>
                <Button msg="수정" onClick={() => alert('수정')} />
                <Button primary={false} msg="삭제" onClick={() => alert('삭제')} />
            </Inputs>
        
        )

        return (
            <ProductTemplate>
                {planList}
            </ProductTemplate>
        )
    }
}

export default ProductListPage;