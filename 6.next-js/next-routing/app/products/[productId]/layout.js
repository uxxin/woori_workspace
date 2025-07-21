import React from "react";

function productDetailLayout({ children }) {
  return (
    <section>
      <div className="p-4 bg-blue-500">상품 상세 페이지 레이아웃 코드</div>
      {children}
    </section>
  );
}

export default productDetailLayout;
