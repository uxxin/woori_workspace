// 특정 상품의 리뷰 페이지에 보여질 내용

import { notFound } from "next/navigation";

// 컴포넌트 이름은 해당 페이지를 나타내는 이름으로 적당하게 지으면 됨.
export default async function ReviewDetail({ params }) {
  const { reviewId, productId } = await params;
  console.log(reviewId);
  //[reviewId]의 값을 동적으로 처리
  if (reviewId > 10) {
    notFound();
  }
  return (
    <div>
      상품 {productId}의 {reviewId}번째 리뷰
    </div>
  );
}
