import './Button.css';

const Button = ({msg, onClick, primary=true}) => {
    return (
        <div className={primary ? "button-primary" : "button-secondary"} onClick={onClick} >
            {msg}
        </div>
    )
}

export default Button;