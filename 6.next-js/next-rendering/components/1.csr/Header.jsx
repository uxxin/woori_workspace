"use client"; // csr로만 동작하는 예시를 확인하기 위해 작성

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
