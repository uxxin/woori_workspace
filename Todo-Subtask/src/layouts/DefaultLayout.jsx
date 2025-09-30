//자식 요소를 안쪽에 넣겠다. => 열고 닫는 태그로 사용 가능 <></>
const DefaultLayout = ({ children }) => {
  return (
    <div className="w-full h-full overflow-y-scroll bg-slate-400">
      <div className="max-w-xl mx-auto min-w-[20rem]">{children}</div>
    </div>
  );
};

export default DefaultLayout;
