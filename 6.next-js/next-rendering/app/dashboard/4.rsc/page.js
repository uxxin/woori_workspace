import Dashboard from "@/components/4.rsc/Dashboard";

export const dynamic = "force-dynamic";

export default async function RSCPage() {
  // 서버 컴포넌트에서 데이터 가져오기 (fetching)
  const res = await fetch("http://localhost:4000/api/data?delay=2000");
  const data = await res.json();
  return (
    <div>
      <h1 className="text-xl font-bold mb-4">RSC Rendering</h1>
      <Dashboard data={data} />
    </div>
  );
}
