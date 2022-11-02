import './ProductCreateTemplate.css'

const ProductCreateTemplate = ({children}) => {
    return (
        <main className="product-create-template">
            <div className="product-create-wrapper">
                {children}
            </div>
        </main>
    );
}

export default ProductCreateTemplate;