import './ComboBox.css'

const ComboBox = ({items, onChange, selectedValue}) => {
    const comboBoxItems = items.map(
        ({value, label}, index) => (
            <option selected={selectedValue === value} value={value} key={index}>{label}</option>
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