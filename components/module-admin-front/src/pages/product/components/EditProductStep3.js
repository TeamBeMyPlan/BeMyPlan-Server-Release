import { Component } from 'react';
import Button from '../../../components/button/Button';
import Inputs from '../../../components/inputs/Inputs'
import Textbox from '../../../components/textbox/Textbox';
import ComboBox from '../../../components/combobox/ComboBox';
import './ProductStep.css'

class EditProductStep3 extends Component {

    state = {
        spotItems: [],
        previews: [],
        defaultSpotId: -1,
    }

    componentDidMount() {
        const { spots } = this.props;
        const comboBoxItems = [ ...spots.map(spot => ({
            value: spot.id,
            label: spot.name
        }))];

        this.setState({
            spotItems: comboBoxItems,
            previews: [...this.props.previews],
            defaultSpotId: spots.length > 0 ? spots[0].id : -1,
        });
    }

    addPreview = () => {
        const { previews, defaultSpotId } = this.state;
        this.setState({
            previews: [...previews, {
                spotId: defaultSpotId,
                description: ''
            }]
        })
    }

    removePreview = () => {
        const { previews } = this.state;
        previews.pop();

        this.setState({
            previews: [...previews]
        })
    }

    handleDescription = (e, index) => {
        this.setState((state) => {
            const newPreviews = [...state.previews];
            const prevPreview = newPreviews[index];

            newPreviews[index] = {
                id: prevPreview.id,
                spotId: prevPreview.spotId,
                description: e.target.value
            }
            return {
                previews: newPreviews
            }
        })
    }

    handleSpot = (e, index) => {
        const spotId = Number(e.target.value);
        const { spots } = this.props;
        const targetSpot = spots.find(spot => spot.id === spotId);

        console.log(targetSpot);

        if (targetSpot === undefined) {
            return;
        }
        
        this.setState((state) => {
            const newPreviews = [...state.previews];
            const prevPreview = newPreviews[index];

            newPreviews[index] = {
                id: prevPreview.id,
                spotId: targetSpot.id,
                description: prevPreview.description
            }
            return {
                previews: newPreviews
            }
        })
    }

    saveAndNext = () => {
        const { onChangePreviews } = this.props;
        const { previews } = this.state;
        onChangePreviews(previews);
    }

    render() {
        const { previews, spotItems } = this.state;
        const { addPreview,
            removePreview,
            handleDescription,
            handleSpot,
            saveAndNext } = this;

        return (
            <>
                <div>
                    {
                        previews.map((preview, index) => {
                            return (
                            <div key={index}>
                                <h3>미리보기 {index + 1}</h3>
                                <Inputs msg='여행지'>
                                    <ComboBox selectedValue={preview.spotId} items={spotItems}
                                        onChange={(e) => handleSpot(e, index)} />
                                </Inputs>
                                <Inputs msg='설명'>
                                    <Textbox hint='미리보기 설명' value={preview.description} onChange={(e) => handleDescription(e, index)} />
                                </Inputs>
                                <Inputs>
                                    <Button msg="삭제" onClick={removePreview} />
                                </Inputs>
                            </div>
                        )})
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

export default EditProductStep3;