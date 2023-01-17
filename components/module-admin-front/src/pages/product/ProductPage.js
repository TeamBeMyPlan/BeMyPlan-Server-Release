import { Link } from "react-router-dom";
import './ProductPage.css'

const ProductPage = () => {
    return (
        <div className="product-container">
            <Link className="product-container-item" to="new">상품 추가</Link>
        </div>
    )
}

export default ProductPage;