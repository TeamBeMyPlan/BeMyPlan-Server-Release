import { Component } from 'react';
import Button from '../../../components/button/Button';
import Inputs from '../../../components/inputs/Inputs'
import Textbox from '../../../components/textbox/Textbox';
import ComboBox from '../../../components/combobox/ComboBox';
import './ProductStep.css'

class ProductStep3 extends Component {

    state = {
        spots: [],
        spotItems: [],
        previews: [
            {
                spotId: -1,
                image: '',
                description: ''
            }
        ]
    }

    componentDidMount() {
        const { step2 } = this.props;
        const comboBoxItems = [{
            value: -1,
            label: '-'
        }, ...step2.spots.map(spot => ({
            value: spot.id,
            label: spot.name
        }))];

        this.setState({
            spots: step2.spots,
            spotItems: comboBoxItems
        });
    }

    addPreview = () => {
        const { previews } = this.state;
        this.setState({
            previews: [...previews, {
                spotId: -1,
                image: '',
                description: ''
            }]
        })
    }

    handleDescription = (e, index) => {
        this.setState((state) => {
            const newPreviews = [...state.previews];
            const prevPreview = newPreviews[index];

            newPreviews[index] = {
                spotId: prevPreview.spotId,
                image: prevPreview.image,
                description: e.target.value
            }
            return {
                previews: newPreviews
            }
        })
    }

    handleSpot = (e, index) => {
        const spotId = e.target.value;
        const { spots } = this.state;
        const targetSpot = spots.find(spot => spot.id == spotId);

        this.setState((state) => {
            const newPreviews = [...state.previews];
            const prevPreview = newPreviews[index];

            newPreviews[index] = {
                spotId: targetSpot.id,
                image: targetSpot.savedImages[0],
                description: prevPreview.description
            }
            return {
                previews: newPreviews
            }
        })
    }

    saveAndNext = () => {
        const { update } = this.props;
        const { previews } = this.state;

        update({previews});
    }

    render() {
        const { previews, spotItems } = this.state;
        const { addPreview,
            handleDescription,
            handleSpot,
            saveAndNext } = this;

        return (
            <>
                <div>
                    {
                        previews.map((preview, index) => (
                            <div key={index}>
                                <h3>미리보기 {index + 1}</h3>
                                <Inputs msg='여행지'>
                                    <ComboBox items={spotItems}
                                        onChange={(e) => handleSpot(e, index)} />
                                </Inputs>
                                <Inputs msg='설명'>
                                    <Textbox hint='미리보기 설명' value={preview.description} onChange={(e) => handleDescription(e, index)} />
                                </Inputs>
                            </div>
                        ))
                    }
                    <div className="next-button-wrapper">
                        <Button msg="추가" onClick={addPreview} />
                    </div>
                </div>
                <div className="next-button-wrapper">
                    <Button msg="완료 3/3" onClick={saveAndNext} />
                </div>
            </>
        );
    }
}

export default ProductStep3;