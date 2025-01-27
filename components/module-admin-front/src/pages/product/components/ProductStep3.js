import { Component } from 'react';
import Button from '../../../components/button/Button';
import Inputs from '../../../components/inputs/Inputs'
import Textbox from '../../../components/textbox/Textbox';
import ComboBox from '../../../components/combobox/ComboBox';
import './ProductStep.css'

class ProductStep3 extends Component {

    state = {
        spotItems: [],
        previews: []
    }

    componentDidMount() {
        const { spots } = this.props;

        const comboBoxItems = [...spots.map(spot => ({
            value: spot.seq,
            label: spot.name
        }))];

        this.setState({
            spotItems: comboBoxItems,
            previews: [...this.props.previews]
        });
    }

    addPreview = () => {
        const { previews } = this.state;
        this.setState({
            previews: [...previews, {
                spotSeq: 0,
                image: '',
                description: ''
            }]
        })
    }

    removeSpot = (spot) => {
        if (window.confirm(`[${spot.name}] 을 삭제하시겠습니까`)) {
            this.props.onChangeSpots(this.props.spots.filter(s => s !== spot));
            this.props.onDeleteSpot(spot);
        }
    }

    removePreview = (preview) => {
        const { previews } = this.state;
        this.setState({
            previews: previews.filter(p => p !== preview)
        })
    }

    handleDescription = (e, index) => {
        this.setState((state) => {
            const newPreviews = [...state.previews];
            const prevPreview = newPreviews[index];

            newPreviews[index] = {
                spotSeq: prevPreview.spotSeq,
                image: prevPreview.image,
                description: e.target.value
            }
            return {
                previews: newPreviews
            }
        })
    }

    handleSpot = (e, index) => {
        const spotSeq = Number(e.target.value);
        const { spots } = this.props;
        const targetSpot = spots.find(spot => spot.seq === spotSeq);

        if (targetSpot === undefined) {
            return;
        }
        
        this.setState((state) => {
            const newPreviews = [...state.previews];
            const prevPreview = newPreviews[index];

            newPreviews[index] = {
                spotSeq: targetSpot.seq,
                image: targetSpot.savedImages[0],
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
                                    <ComboBox selectedValue={preview.spotSeq} items={spotItems}
                                        onChange={(e) => handleSpot(e, index)} />
                                </Inputs>
                                <Inputs msg='설명'>
                                    <Textbox hint='미리보기 설명' value={preview.description} onChange={(e) => handleDescription(e, index)} />
                                </Inputs>
                                <Inputs>
                                    <Button msg="삭제" onClick={() => removePreview(preview)} />
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

export default ProductStep3;