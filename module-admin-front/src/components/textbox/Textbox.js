import './Textbox.css'

const Textbox = ({hint, value, onChange, readOnly = false}) => {
    return (
        <div className="textbox">
            <input placeholder={hint} value={value} onChange={onChange} readOnly={readOnly}/>
        </div>
    )
}

export default Textbox;