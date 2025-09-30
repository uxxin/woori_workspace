import Dashboard from "@/components/3.streaming-ssr/Dashboard";
import { Suspense } from "react";

export const dynamic = "force-dynamic";

async function fetchData() {
  const res = await fetch("http://localhost:4000/api/data?delay=2000");
  return res.json();
}

async function DashboardWithData() {
  const data = await fetchData();
  return <Dashboard data={data} />;
}

export default async function StreamingPage() {
  return (
    <div>
      <h1 className="text-xl font-bold mb-4">Stremaing SSR</h1>
      {/* 렌더링을 지연시킬 부분만 Suspense로 감싸줌, <h1>Streaming SSR</h1>은 먼저 렌더링 */}
      {/* <Suspense fallback={<p>Loading...</p>}> */}
      <DashboardWithData />
      {/* </Suspense> */}
    </div>
  );
}
