import './Textbox.css'

const Textbox = ({hint, value, onChange}) => {
    return (
        <div className="textbox">
            <input placeholder={hint} value={value} onChange={onChange}/>
        </div>
    )
}

export default Textbox;