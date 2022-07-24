import ProductCreateTemplate from './components/ProductCreateTemplate'
import Button from '../../components/button/Button'
import { useState } from 'react';
import ProductStepOne from './components/ProductStepOne';


const pages = [
    (<ProductStepOne>page1</ProductStepOne>),
    (<div>page2</div>),
    (<div>page3</div>),
    (<div>page4</div>),
];

const ProductCreatePage = () => {
    const [page, setPage] = useState(0);
    
    function nextPage() {
        if (pages.length - 1 <= page) {
            window.location = '/';
            return;
        }

        setPage(page + 1);
    }

    return (
        <ProductCreateTemplate next={(
            <Button msg="다음" onClick={nextPage}/>)
        }>
            <div>{pages[page]}</div>
        </ProductCreateTemplate>
    )
}

export default ProductCreatePage;