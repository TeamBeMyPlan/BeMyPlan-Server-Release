import { Component } from 'react';
import Button from '../../../components/button/Button';
import Inputs from '../../../components/inputs/Inputs'
import Textbox from '../../../components/textbox/Textbox';
import imageApi from '../../../components/imageApi'
import './ProductStep.css'

class ProductStep3 extends Component {

    state = {
        previews: [
            {
                image: '',
                description: ''
            }
        ]
    }

    addPreview = () => {
        const { previews } = this.state;
        this.setState({
            previews: [...previews, {
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
                image: prevPreview.image,
                description: e.target.value
            }
            return {
                previews: newPreviews
            }
        })
    }

    fileChangedHandler = async (e, index) => {
        const files = e.target.files;
        const formData = new FormData();

        for (let i = 0; i < files.length; i++) {
            formData.append('files', files[i]);
        }

        const response = await imageApi.upload(formData);

        this.setState((state) => {
            const newPreviews = [...state.previews];
            const prevPreview = newPreviews[index];

            newPreviews[index] = {
                image: response.data[0],
                description: prevPreview.description
            }
            return {
                previews: newPreviews
            }
        })
    }

    render() {
        const { previews } = this.state;
        const { addPreview,
            handleDescription,
            fileChangedHandler } = this;

        return (
            <>
            <div>
                {
                    previews.map((preview, index) => (
                        <div key={index}>
                            <h3>미리보기 {index + 1}</h3>
                            <Inputs msg='여행지 사진'>
                                <input type="file" name="files" onChange={(e) => fileChangedHandler(e, index)}/>
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
                <Button msg="완료 3/3" onClick={this.props.nextPage}/>
            </div>
            </>
        );
    }
}

export default ProductStep3;