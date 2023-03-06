import ProductTemplate from './components/ProductTemplate'
import { Component } from 'react';
import ProductStep1 from './components/ProductStep1';
import ProductStep2 from './components/ProductStep2';
import EditProductStep3 from './components/EditProductStep3';
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

        spots: [],
        previews: [],
    }

    async componentDidMount() {
        const id = window.location.pathname.split('/')[3];
        this.setState({
            id: id
        });

        await this.fetchCreators();
        await this.fetchPlan(id);
        await this.fetchSpots(id);
        await this.fetchPreviews(id);
    }

    async fetchCreators() {
        const creators = await creatorApi.getCreators();
        this.setState({
            creators: [...creators.data]
        })
    }

    async fetchSpots(id) {
        const spots = await planApi.getSpots(id);
        if (spots.code === 200) {
            console.log(spots.data)
            this.setState({
                spots: [...spots.data]
            })
        }
    }

    async fetchPreviews(id) {
        const previews = await planApi.getPreviews(id);
        if (previews.code === 200) {
            console.log(previews.data)
            this.setState({
                previews: [...previews.data]
            })
        }
    }

    async fetchPlan(id) {
        const plan = await planApi.get(id);
        if (plan.code === 200) {
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

    updateSpots = (spots) => {
        this.setState({ spots: [...spots] });
    }

    deleteSpot = async (spot) => {
        await planApi.deleteSpot(spot.id);
        const { id } = this.state;
        await this.fetchPreviews(id);
    }

    updatePreviews = (newPreviews) => {
        this.setState({ previews: [...newPreviews] }, () => this.savePreviews());
    }

    savePlan = async () => {
        const {
            id,
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
        } = this.state;

        if (window.confirm(`[${planTitle}] 일정 정보를 수정하시겠습니까`)) {
            const response = await planApi.put(id, {
                creatorId: creatorId,
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
            });

            if (response.status === 200) {
                console.log(response.data);
                this.nextPage();
            }
        }
    }

    saveSpots = async () => {
        const { id, planTitle, spots } = this.state;
        if (window.confirm(`[${planTitle}] 장소 정보를 수정하시겠습니까`)) {
            console.log(spots);
            const response = await planApi.putSpots(id, spots);

            if (response.status === 200) {
                console.log(response.data);
                await this.fetchSpots(id);
                this.nextPage();
            }
        }
    }

    savePreviews = async () => {
        const { id, planTitle, previews } = this.state;
        if (window.confirm(`[${planTitle}] 미리보기 정보를 수정하시겠습니까`)) {
            console.log(previews);
            
            const response = await planApi.putPreviews(id, previews);

            if (response.status === 200) {
                console.log(response.data);
                alert('수정 완료');
                window.location = '/';
            }
        }
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
            spots,
            previews,
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
            updateSpots,
            deleteSpot,
            updatePreviews,
        } = this;

        if (page === 0) {
            return (
                <ProductStep1 nextPage={this.savePlan}
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

        if (page === 1) {
            return (
                <ProductStep2 nextPage={this.saveSpots}
                    spots={spots} onChangeSpots={updateSpots} onDeleteSpot={deleteSpot} />
            )
        }

        if (page === 2) {
            return (
                <EditProductStep3 nextPage={this.nextPage}
                    spots={spots} previews={previews} onChangePreviews={updatePreviews}/>
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