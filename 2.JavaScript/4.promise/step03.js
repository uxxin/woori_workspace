/* 
    Promise 객체의 인수로 전달할 콜백 함수
*/

const executor = (resolve, reject) => {
  //작업 처리할 로직 작성 부분
  // 요청이 성공했을 경우, => fullfilled
  resolve("요청 처리 성공 결과값(ex. JSON)");
  // 요청이 실패했을 경우, => rejected
  // reject('요청 처리 실패 메세지');
};

const promise = new Promise(executor);
console.log(promise);

//요청 처리 성공 결과값(ex. JSON)라는 데이터를 받아서 처리
promise.then((response) => {
  // resolve()의 인수로 받은 응답 데이터가 response로 전달됨.
  console.log(response); // 약속이 이행되었을 경우 수행할 로직
});
//resolve 결과값(ex. JSON)이 response로 들어옴.
