import ProductCreateTemplate from './components/ProductCreateTemplate'
import { Component } from 'react';
import ProductStep1 from './components/ProductStep1';
import ProductStep2 from './components/ProductStep2';
import ProductStep3 from './components/ProductStep3';

class ProductCreatePage extends Component {
    state = {
        page: 0,
        step1: {},
        step2: {}
    }

    updateStep1 = (updatedStep1) => {
        this.setState({step1: updatedStep1})
    }

    updateStep2 = (updatedStep2) => {
        this.setState({step2: updatedStep2})
    }

    saveNewPlan = () => {
        alert('새로운 플랜 등록 완료!');
    }

    nextPage = () => {
        const { page } = this.state;

        if (page >= 2) {
            window.location = '/products';
            this.saveNewPlan();
            return;
        }

        const nextPage = page + 1;
        this.setState({
            page: nextPage
        });
    }

    getPage = () => {
        const { page } = this.state;
        if (page === 0) {
            return (
                <ProductStep1 update={this.updateStep1} nextPage={this.nextPage}/>
            )
        }

        if (page === 1) {
            return (
                <ProductStep2 update={this.updateStep2} nextPage={this.nextPage}/>
            )
        }

        if (page === 2) {
            console.log(this.state.step2);
            return (
                <ProductStep3 nextPage={this.nextPage}/>
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