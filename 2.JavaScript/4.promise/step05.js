/*
    
*/

const xhr = new XMLHttpRequest();
const url = "http://localhost:4000/users";

xhr.open("GET", url);

xhr.onload = () => {
  if (xhr.status === 200) {
    const data = JSON.parse(xhr.response);
    console.log(data);
  } else {
    console.error(`HTTP 오류: ${xhr.status}`); //404, 500등
  }
};

xhr.onerror = () => {
  // 서버 연결 안하면 뜨는 에러
  console.error("네트워크 오류: 서버 연결 실패");
};

xhr.send();
