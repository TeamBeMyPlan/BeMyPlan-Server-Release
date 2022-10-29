import { Link } from "react-router-dom";
import './ProductPage.css'

const ProductPage = () => {
    return (
        <div className="product-container">
            <Link className="product-container-item" to="new">상품 추가</Link>
            <Link className="product-container-item" to="edit">상품 수정</Link>
            <Link className="product-container-item" to="delete">상품 제거</Link>
        </div>
    )
}

export default ProductPage;