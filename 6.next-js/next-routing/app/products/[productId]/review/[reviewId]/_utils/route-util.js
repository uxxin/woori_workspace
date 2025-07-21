import { notFound } from "next/navigation";

// NotFound를 트리거시키는 함수
export const triggerNotFound = (reviewId) => {
  if (reviewId > 10) {
    notFound();
  }
};
