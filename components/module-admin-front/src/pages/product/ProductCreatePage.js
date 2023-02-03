import ProductTemplate from './components/ProductTemplate'
import { Component } from 'react';
import ProductStep1 from './components/ProductStep1';
import ProductStep2 from './components/ProductStep2';
import ProductStep3 from './components/ProductStep3';
import planApi from '../../components/planApi';
import creatorApi from '../../components/creatorApi'
import imageCompression from 'browser-image-compression';
import imageApi, { compressionOptions } from '../../components/imageApi'

class ProductCreatePage extends Component {
    state = {
        page: 0,
        step3: {},

        creators: [],
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

        spots: [],
    }

    async componentDidMount() {
        await this.fetchCreators();
    }

    async fetchCreators() {
        const creators = await creatorApi.getCreators();
        this.setState({
            creators: [...creators.data]
        })
    }

    updateStep3 = (updatedStep3) => {
        this.setState({ step3: updatedStep3 }, this.saveNewPlan)
    }

    saveNewPlan = async () => {
        const { step3 } = this.state;

        console.log(step3);

        const {
            creatorId,
            planTitle,
            planDescription,
            thumbnail,
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

            spots,
        } = this.state;

        const response = await planApi.create({
            creator: {
                id: creatorId
            },
            plan: {
                title: planTitle,
                description: planDescription,
                thumbnail: thumbnail,
                concept: concept,
                partner: partner,
                period: period,
                cost: cost,
                vehicle: vehicle,
                recommend: recommend,
                region: region,
                price: price,
                tags: tags,
                recommendTargets: recommendTargets,
            },
            spots: spots,
            previews: step3.previews
        });


        alert('새로운 플랜 등록 완료!');
        window.location = '/';

        console.log(response);
        console.log(response.data);
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

    updateSpots = (spots) => {
        this.setState({ spots: [...spots] });
    }

    getPage = () => {
        const { page,
            creators,
            planTitle,
            planDescription,
            cost,
            price,
            tags,
            recommendTargets,
            spots,
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
            fileChangedHandler,
            updateSpots
        } = this;

        if (page === 0) {
            return (
                <ProductStep1 nextPage={this.nextPage}
                    creators={creators} onChangeCreator={handleCreatorName}
                    planTitle={planTitle} onChangePlanTitle={handlePlanTitle}
                    onChangeThumbnail={fileChangedHandler}
                    planDescription={planDescription} onChangePlanDescription={handlePlanDescription}
                    onChangeConcept={handleConcept}
                    onChangePartner={handlePartner}
                    onChangePeriod={handlePeriod}
                    cost={cost} onChangeCost={handleCost}
                    onChangeVehicle={handleVehicle}
                    onChangeRecommend={handleRecommend}
                    onChangeRegion={handleRegion}
                    price={price} onChangePrice={handlePrice}
                    tags={tags} onChangeTags={handleTags}
                    recommendTargets={recommendTargets} onChangeRecommendTargets={handleRecommendTargets}
                />
            )
        }

        if (page === 1) {
            return (
                <ProductStep2 nextPage={this.nextPage}
                    spots={spots} onChangeSpots={updateSpots} />
            )
        }

        if (page === 2) {
            return (
                <ProductStep3 nextPage={this.nextPage} 
                    spots={spots} update={this.updateStep3} />
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
            <ProductTemplate>
                {
                    getPage()
                }
            </ProductTemplate>
        )
    }
}

export default ProductCreatePage;