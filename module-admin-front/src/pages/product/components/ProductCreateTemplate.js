import './ProductCreateTemplate.css'

const ProductCreateTemplate = ({next, children}) => {
    return (
        <main className="product-create-template">
            <div className="product-create-wrapper">
                {children}
            </div>
            <div className="next-button-wrapper">
                {next}
            </div>
        </main>
    );
}

export default ProductCreateTemplate;