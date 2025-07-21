import LineChart from "@/components/dashboard/line-chart";

export default function Page() {
  //page.js는 해당 라우트 경로에 나타낼 컴포넌트들을 불러와서 렌더링하는 곳
  //Dashboard 페이지에 필요한 컴포넌트들을 불러와서 page.js에 구성하면 됨
  return (
    <div>
      <h1>Dashboard 페이지</h1>
      {/* LineChart 컴포넌트를 불러와서 구성 */}
      <LineChart />
    </div>
  );
}
