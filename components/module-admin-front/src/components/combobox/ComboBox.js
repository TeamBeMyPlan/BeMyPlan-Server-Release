import './ComboBox.css'

const ComboBox = ({items, onChange}) => {
    const comboBoxItems = items.map(
        ({value, label}, index) => (
            <option value={value} key={index}>{label}</option>
        )
    )

    return (
        <div className="combobox">
            <select onChange={onChange}>
                {comboBoxItems}
            </select>
        </div>
    )
}

export default ComboBox;