import ProductTemplate from './components/ProductTemplate'
import { Component } from 'react';
import planApi from '../../components/planApi';

class ProductEditPage extends Component {
    state = {
        id: 0,
        page: 0,
        plan: {},
        step1: {},
        step2: {},
        step3: {}
    }

    async componentDidMount() {
        const id = window.location.pathname.split('/')[3];
        this.setState({
            id: id
        });

        await this.fetchPlan(id);
    }

    async fetchPlan(id) {
        const plan = await planApi.get(id);
        if (plan.code === 200) {
            console.log(plan.data);

            this.setState({
                plan: plan.data
            })
        }
    }

    updateStep1 = (updatedStep1) => {
        this.setState({ step1: updatedStep1 })
    }

    updateStep2 = (updatedStep2) => {
        this.setState({ step2: updatedStep2 })
    }

    updateStep3 = (updatedStep3) => {
        this.setState({ step3: updatedStep3 })
    }

    nextPage = () => {
        const { page } = this.state;

        if (page >= 2) {
            this.saveNewPlan();
            return;
        }

        const nextPage = page + 1;
        this.setState({
            page: nextPage
        });
    }

    getPage = () => {
        const { id } = this.state;

        return (
            <div>
                page {id}
            </div>
        )
    }

    render() {
        const {
            getPage
        } = this;

        return (
            <ProductTemplate>
                {
                    getPage()
                }
            </ProductTemplate>
        )
    }
}

export default ProductEditPage;