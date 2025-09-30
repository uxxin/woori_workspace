import Link from "next/link";

export default function Home() {
  return (
    <div>
      {/* Shift + Alt + Left/Right 방향키 - 영역 확장/축소 */}
      Hello
      <br />
      <Link href="/blog" className="text-2xl text-blue-500 underline">
        블로그
      </Link>
      <br />
      <Link href="/products" className="text-2xl text-blue-500 underline">
        상품
      </Link>
    </div>
  );
}
