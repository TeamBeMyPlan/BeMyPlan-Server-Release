import './Inputs.css'

const Inputs = ({msg, children}) => {
    return (
        <div className="inputs">
            <div className="inputs-label">
                {msg}
            </div>
            <div className="inputs-area">
                {children}
            </div>
        </div>
    )
}

export default Inputs;