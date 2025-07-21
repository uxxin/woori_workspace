// 특정 상품의 디테일 페이지에 보여질 내용
export default async function Page({ params }) {
  const { productId } = await params;
  console.log(productId);
  //[productId]의 값을 동적으로 처리
  return <div>상품 {productId} 디테일 페이지</div>;
}
