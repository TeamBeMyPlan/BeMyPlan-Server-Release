const ProductCreateTemplate = ({next, children}) => {
    return (
        <main>
            <div>
                {children}
            </div>
            <div>
                {next}
            </div>
        </main>
    );
}

export default ProductCreateTemplate;