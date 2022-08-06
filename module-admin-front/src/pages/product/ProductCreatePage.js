import ProductCreateTemplate from './components/ProductCreateTemplate'
import Button from '../../components/button/Button'
import { Component } from 'react';
import ProductStepOne from './components/ProductStepOne';
import ProductStepTwo from './components/ProductStepTwo';

const Empty_Add_Plan = {
    creator: {
        name: ''
    },
    plan: {
        title: '',
        description: '',
        concept: '',
        partner: '',
        vehicle: '',
        period: '',
        price: 0,
        recommend: false,
        spots: [
            {
                title: '',
                review: '',
                tip: '',
                address: '',
                latitude: 0,
                longitude: 0,
                images: []
            }
        ],
        previews: [
            {
                image: '',
                description: ''
            }
        ]
    }
}

class ProductCreatePage extends Component {
    state = {
        page: 0,
        creator: {
            name: ''
        },
        plan: {
            title: '',
            description: '',
            concept: '',
            partner: '',
            vehicle: '',
            period: '',
            price: 0,
            recommend: false,
        }
    }

    nextPage = () => {
        const { page, creator, plan } = this.state;

        if (page >= 3) {
            window.location = '/';
            return;
        }

        this.setState({
            page: page + 1
        });
    }

    handleCreatorName = (e) => { this.setState({ creator: { name: e.target.value } }) }

    handlePlanTitle = (e) => { this.setState({ plan: { title: e.target.value } }) }

    handlePlanDescription = (e) => { this.setState({ plan: { description: e.target.value } }) }

    handlePlanConept = (e) => { this.setState({ plan: { concept: e.target.value } }) }

    handlePartner = (e) => { this.setState({ plan: { partner: e.target.value } }) }

    handlePeriod = (e) => { this.setState({ plan: { period: e.target.value } }) }

    handleCost = (e) => { this.setState({ plan: { price: e.target.value } }) }

    handleVehicle = (e) => { this.setState({ plan: { vehicle: e.target.value } }) }

    handleRecommend = (e) => { this.setState({ plan: { recommend: e.target.value } }) }

    getPage = (page, creator, plan) => {
        if (page === 0) {
            return (
                <ProductStepOne
                    creator={creator}
                    plan={plan}
                    handleCreatorName={this.handleCreatorName}
                    handlePlanTitle={this.handlePlanTitle}
                    handlePlanDescription={this.handlePlanDescription}
                    handlePlanConcept={this.handlePlanConept}
                    handlePartner={this.handlePartner}
                    handlePeriod={this.handlePeriod}
                    handleCost={this.handleCost}
                    handleVehicle={this.handleVehicle}
                    handleRecommend={this.handleRecommend}
                    />
            )
        }
        if (page === 1) {
            return (
                <ProductStepTwo/>
            )
        }

        return (
            <div>
                page {page}
            </div>
        )
    }

    render() {
        const { page, creator, plan } = this.state;
        const {
            nextPage,
            getPage
        } = this;

        return (
            <ProductCreateTemplate next={(
                <Button msg="다음" onClick={nextPage} />)
            }>
                {
                    getPage(page, creator, plan)
                }
            </ProductCreateTemplate>
        )
    }
}

export default ProductCreatePage;