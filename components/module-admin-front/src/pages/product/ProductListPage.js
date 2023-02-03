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

    deletePlan = async ({planId, planTitle}) => {
        if (window.confirm(`[${planTitle}] 해당 일정을 정말 삭제하시겠습니까`)) {
            console.log('hi');
            await planApi.delete(planId);
            window.location = '/';
        }
    }

    render() {
        const { plans } = this.state;
        const { deletePlan } = this;

        const planList = plans.map(plan => 
            <Inputs key={plan.id} msg={plan.title}>
                <Button msg="수정" onClick={() => alert('수정')} />
                <Button primary={false} msg="삭제" onClick={() => deletePlan({
                    planId: plan.id,
                    planTitle: plan.title})} />
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