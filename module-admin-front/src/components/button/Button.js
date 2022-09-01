import './Button.css';

const Button = ({msg, onClick}) => {
    return (
        <div className="button" onClick={onClick} >
            {msg}
        </div>
    )
}

export default Button;