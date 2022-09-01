import './NumericInput.css'

const NumericInput = ({hint, value, onChange}) => {
    return (
        <div className="input">
            <input placeholder={hint} value={value} onChange={onChange}/>
        </div>
    )
}

export default NumericInput;