"use client";
import { useState } from "react";

export default function LikeCounter({ item }) {
  console.log("LikeCounter 렌더링 됨");

  const [count, setCount] = useState(0);

  return (
    <div className="mt-2 flex items-center gap-2 text-sm text-gray-600">
      <button
        onClick={() => setCount(count + 1)}
        className="px-2 py-1 bg-blue-100 hover:bg-blue-200 rounded text-xs"
      >
        {item} 좋아요 👍 {count} 개
      </button>
    </div>
  );
}
