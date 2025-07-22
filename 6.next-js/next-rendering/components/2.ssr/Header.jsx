// server에서 실행되도록 하기 위한 옵션 - 디폴트가 서버 컴포넌트로 동작
// (사용자와 interaction 없는 UI면 서버 사용 .. ? )
const Header = () => {
  console.log("<Header /> 렌더링 됨");

  return (
    <div className="flex gap-4">
      <button className="text-sm text-gray-600 hover:text-black">Login</button>
      <button className="text-sm text-gray-600 hover:text-black">
        Settings
      </button>
    </div>
  );
};

export default Header;
