import ProductCreateTemplate from './components/ProductCreateTemplate'
import { Component } from 'react';
import ProductStep1 from './components/ProductStep1';
import ProductStep2 from './components/ProductStep2';
import ProductStep3 from './components/ProductStep3';
import planApi from '../../components/planApi';

class ProductCreatePage extends Component {
    state = {
        page: 0,
        step1: {},
        step2: {},
        step3: {}
    }

    updateStep1 = (updatedStep1) => {
        this.setState({ step1: updatedStep1 })
    }

    updateStep2 = (updatedStep2) => {
        this.setState({ step2: updatedStep2 })
    }

    updateStep3 = (updatedStep3) => {
        this.setState({ step3: updatedStep3 }, this.saveNewPlan)
    }

    saveNewPlan = async () => {
        const { step1, step2, step3 } = this.state;

        console.log(step1);
        console.log(step2);
        console.log(step3);
        alert('새로운 플랜 등록 완료!');

        const response = await planApi.create({
            creator: {
                name: step1.creator
            },
            plan: {
                title: step1.planTitle,
                description: step1.planDescription,
                price: step1.price,
                thumbnail: step1.thumbnail,
                recommend: step1.recommend,
                vehicle: step1.vehicle,
                concept: step1.concept,
                cost: step1.cost,
                period: step1.period,
                partner: step1.partner,
            },
            spots: step2.spots,
            previews: step3.previews
        });

        console.log(response);
        console.log(response.data);
    }

    nextPage = () => {
        const { page } = this.state;

        if (page >= 2) {
            // window.location = '/products';
            this.saveNewPlan();
            return;
        }

        const nextPage = page + 1;
        this.setState({
            page: nextPage
        });
    }

    getPage = () => {
        const { page, step2 } = this.state;
        if (page === 0) {
            return (
                <ProductStep1 update={this.updateStep1} nextPage={this.nextPage} />
            )
        }

        if (page === 1) {
            return (
                <ProductStep2 update={this.updateStep2} nextPage={this.nextPage} />
            )
        }

        if (page === 2) {
            return (
                <ProductStep3 update={this.updateStep3} nextPage={this.nextPage} step2={step2} />
            )
        }

        return (
            <div>
                page {page}
            </div>
        )
    }

    render() {
        const {
            getPage
        } = this;

        return (
            <ProductCreateTemplate>
                {
                    getPage()
                }
            </ProductCreateTemplate>
        )
    }
}

export default ProductCreatePage;