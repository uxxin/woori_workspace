/**
 * Promise와 XHR을 활용해서 비동기 요청 처리 수행
 * -> 서버에서 데이터 받아서 처리하기
 *
 * 전체 Users 목록 조회(GET: /users)
 *
 * - 요청이 성공했을 경우 브라우저 콘솔에 데이터 출력
 * - 실패했을 경우에는 에러 메시지 출력
 *      - 서버가 종료되어 있을 경우
 *      - 잘못된 경로 요청 등
 */
const executor = (resolve, reject) => {
  // 비동기 처리 작업 작성 부분
  const xhr = new XMLHttpRequest();
  const url = "http://localhost:4000/users";
  xhr.open("GET", url);

  xhr.onload = () => {
    if (xhr.status === 200) {
      // 200 성공 시
      const data = JSON.parse(xhr.response);
      resolve(data); // 응답 결과 데이터를 resolve에 작성
    } else {
      // 2xx 가 아닌 HTTP 상태코드 관련 에러
      reject(new Error(`HTTP 오류: ${xhr.status}`)); // 에러 메시지를 reject에 작성
    }
  };

  xhr.onerror = () => {
    // 네트워크 에러(서버 종료, DNS 에러 등)
    reject(new Error("네트워크 오류: 서버 연결 실패"));
  };

  xhr.send();
};

const promise = new Promise(executor);

// 후속 처리
promise
  .then((data) => console.log(data))
  .catch((error) => {
    console.log("캐치 블럭");
    console.error(error);
  });
