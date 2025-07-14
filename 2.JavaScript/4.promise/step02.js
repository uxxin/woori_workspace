/* 
    Promise 객체의 인수로 전달할 콜백 함수
*/

const executor = (resolve, reject) => {
  //작업 처리할 로직 작성 부분
  console.log("어떤 작업");
  // 요청이 성공했을 경우, => fullfilled
  resolve("요청 처리 성공 결과값(ex. JSON)");
  // 요청이 실패했을 경우, => rejected
  reject("요청 처리 실패 메세지");
};

const promise = new Promise(executor);
console.log(promise);
