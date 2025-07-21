"use client";
import Dashboard from "@/components/1.csr/Dashboard";
import Header from "@/components/1.csr/Header";
import Sidebar from "@/components/1.csr/Sidebar";

import { useEffect, useState } from "react";

export default function CSRPage() {
  console.log("CSR Page 호출 됨");

  const [data, setData] = useState(null);
  console.log(data);

  // Node.js 백엔드 서버에서 데이터를 요청/응답받는 처리 with useEffect 훅
  useEffect(() => {
    console.log("useEffect 호출 됨");

    // 부수효과(Side effect)를 작성할 수 있도록 제공하는 훅

    fetch("http://localhost:4000/api/data?delay=2000")
      .then((response) => response.json())
      .then((json) => setData(json));
  }, []);
  /*
    useEffect 동작 순서
    1. CSRPage 컴포넌트가 먼저 렌더링됨 -> 컴포넌트가 화면에 마운트(mounted)되었다는 의미
    2. 컴포넌트가 마운트되면 useEffect의 콜백이 호출 됨(실행됨)
    */

  return (
    <div>
      <section className="min-h-screen flex">
        <aside className="w-64 bg-gray-100 border-r p-4">
          <Sidebar />
        </aside>

        <div className="flex-1 flex flex-col">
          <header className="h-16 bg-white border-b flex items-center justify-between px-6">
            <Header />
          </header>
          <main className="flex-1 p-6">
            <h1 className="text-xl font-bold mb-4">Client-Side Rendering</h1>
            {/* Dashboard 컴포넌트 렌더링되는 곳 */}
            {data === null ? <p>로딩 중..</p> : <Dashboard data={data} />}
          </main>
        </div>
      </section>
    </div>
  );
}
