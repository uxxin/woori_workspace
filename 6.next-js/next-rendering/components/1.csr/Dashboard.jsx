"use client"; // // csr로만 동작하는 예시를 확인하기 위해 작성

// Express로 구성한 서버에서 데이터를 받아서 렌더링 처리
const Dashboard = ({ data }) => {
  // data - 서버에서 받은 데이터를 props로 받음
  console.log("<Dashboard /> 렌더링 됨");

  return (
    <ul className="space-y-2">
      {data.map((item, idx) => (
        <li key={idx} className="p-2 bg-gray-100 rounded">
          {item}
        </li>
      ))}
    </ul>
  );
};

export default Dashboard;
