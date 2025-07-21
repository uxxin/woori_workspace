import Link from "next/link";

// products/page.js, 상품 목록이 보여지는 페이지
const ProductList = (params) => {
  //상품 목록이 들어있는 리스트 데이터
  const productList = [3, 4];
  const { productId } = params;
  return (
    <div>
      <h1>Product List</h1>

      {productList.map((productId) => (
        <h2 key={productId}>
          <Link href={`products/${productId}`}>Product {productId}</Link>
        </h2>
      ))}
    </div>
  );
};
export default ProductList;
