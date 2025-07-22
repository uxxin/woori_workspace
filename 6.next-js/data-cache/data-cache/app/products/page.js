import Dashboard from "@/components/Dashboard";

export const dynamic = "force-dynamic";

export default async function CachePage() {
  // 서버 컴포넌트에서 데이터 가져오기 (fetching)
  const res = await fetch("http://localhost:4000/api/data", {
    cache: "force-cache",
  });

  //   const etag = res.headers.get("etag");
  //   console.log("🧾 ETag:", etag);

  const data = await res.json();
  return (
    <div>
      <h1 className="text-xl font-bold mb-4">Data Cache</h1>
      <Dashboard data={data} />
    </div>
  );
}
