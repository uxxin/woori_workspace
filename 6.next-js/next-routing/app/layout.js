import "./globals.css";

// app/layout.js는 꼭 children props를 받도록 해야함.
// → 하위 페이지들을 렌더링할 때 사용되어야 하기 때문에

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <header className="p-2 bg-lime-200">Header</header>
      <body>{children}</body>
      <footer className="p-1 bg-orange-200">Footer</footer>
    </html>
  );
}

//추후 <header>, <footer> 역시 별도의 UI 컴포넌트로 작성해서 app/layout.js에 import 할 수 있음.
