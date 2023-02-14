import ProductTemplate from './components/ProductTemplate'
import { Component } from 'react';
import ProductStep1 from './components/ProductStep1';
import planApi from '../../components/planApi';
import creatorApi from '../../components/creatorApi'
import imageCompression from 'browser-image-compression';
import imageApi, { compressionOptions } from '../../components/imageApi'

class ProductEditPage extends Component {
    state = {
        id: 0,
        page: 0,
        creators: [],
        plan: {},
        
        creatorId: 0,
        planTitle: '',
        planDescription: '',
        thumbnail: '',
        concept: 'HEALING',
        partner: 'FAMILY',
        period: 0,
        cost: 0,
        vehicle: 'CAR',
        recommend: false,
        region: 'JEJUALL',
        price: 0,
        tags: '',
        recommendTargets: '',
    }

    async componentDidMount() {
        const id = window.location.pathname.split('/')[3];
        this.setState({
            id: id
        });

        await this.fetchCreators();
        await this.fetchPlan(id);
    }

    async fetchCreators() {
        const creators = await creatorApi.getCreators();
        this.setState({
            creators: [...creators.data]
        })
    }

    async fetchPlan(id) {
        const plan = await planApi.get(id);
        if (plan.code === 200) {
            console.log(plan.data);

            this.setState({
                creatorId: plan.data.creatorId,
                planTitle: plan.data.title,
                planDescription: plan.data.description,
                thumbnail: plan.data.thumbnail,
                concept: plan.data.concept,
                partner: plan.data.partner,
                period: plan.data.period,
                cost: plan.data.cost,
                vehicle: plan.data.vehicle,
                recommend: plan.data.recommend,
                region: plan.data.region,
                price: plan.data.price,
                tags: plan.data.tags,
                recommendTargets: plan.data.recommendTargets,
            })
        }
    }

    handleCreatorName = (e) => { this.setState({ creatorId: e.target.value }) }
    handlePlanTitle = (e) => { this.setState({ planTitle: e.target.value }) }
    handlePlanDescription = (e) => { this.setState({ planDescription: e.target.value }) }
    handleConcept = (e) => { this.setState({ concept: e.target.value }) }
    handlePartner = (e) => { this.setState({ partner: e.target.value }) }
    handlePeriod = (e) => { this.setState({ period: Number(e.target.value) }) }
    handleCost = (e) => { this.setState({ cost: Number(e.target.value) }) }
    handleVehicle = (e) => { this.setState({ vehicle: e.target.value }) }
    handleRecommend = (e) => { this.setState({ recommend: JSON.parse(e.target.value) }) }
    handlePrice = (e) => { this.setState({ price: Number(e.target.value) }) }
    handleRegion = (e) => { this.setState({ region: e.target.value }) }
    handleTags = (e) => { this.setState({ tags: e.target.value }) }
    handleRecommendTargets = (e) => { this.setState({ recommendTargets: e.target.value }) }
    fileChangedHandler = async e => {
        const files = e.target.files;
        const formData = new FormData();

        for (let i = 0; i < files.length; i++) {
            const compressed = await imageCompression(files[i], compressionOptions);
            formData.append('files', compressed);
        }

        const response = await imageApi.upload(formData);
        this.setState({
            thumbnail: response.data[0]
        });
    }

    getPage = () => {
        const { page,
            creators,
            creatorId,
            planTitle,
            planDescription,
            concept,
            partner,
            period,
            cost,
            vehicle,
            recommend,
            region,
            price,
            tags,
            recommendTargets,
        } = this.state;

        const {
            handleCreatorName,
            handlePlanTitle,
            handlePlanDescription,
            handleConcept,
            handlePartner,
            handlePeriod,
            handleCost,
            handleVehicle,
            handleRecommend,
            handleRegion,
            handlePrice,
            handleTags,
            handleRecommendTargets,
            fileChangedHandler
        } = this;

        if (page === 0) {
            return (
                <ProductStep1 nextPage={this.nextPage}
                    creatorId={creatorId} creators={creators} onChangeCreator={handleCreatorName}
                    planTitle={planTitle} onChangePlanTitle={handlePlanTitle}
                    onChangeThumbnail={fileChangedHandler}
                    planDescription={planDescription} onChangePlanDescription={handlePlanDescription}
                    concept={concept} onChangeConcept={handleConcept}
                    partner={partner} onChangePartner={handlePartner}
                    period={period} onChangePeriod={handlePeriod}
                    cost={cost} onChangeCost={handleCost}
                    vehicle={vehicle} onChangeVehicle={handleVehicle}
                    recommend={recommend} onChangeRecommend={handleRecommend}
                    region={region} onChangeRegion={handleRegion}
                    price={price} onChangePrice={handlePrice}
                    tags={tags} onChangeTags={handleTags}
                    recommendTargets={recommendTargets} onChangeRecommendTargets={handleRecommendTargets}
                />
            )
        }

        return (
            <div>
                page
            </div>
        )
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