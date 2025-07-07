/* 
    실패했을 때는 reject()가 호출되며,
    실패 메세지를 가져오기 위해서는 catch()를 활용
*/

const executor = (resolve, reject) => {
  // 요청이 성공했을 경우, => fullfilled
  // resolve('요청 처리 성공 결과값(ex. JSON)');W
  // 요청이 실패했을 경우, => rejected
  reject("요청 처리 실패 메세지");
};

const promise = new Promise(executor);
console.log(promise);

promise
  .then((response) => {
    console.log(response);
  })
  .catch((error) => {
    //rejected되었기 때문에 then 콜백은 실행되지 않음.
    console.error(error);
  })
  .finally(() => {
    console.log("요청 수행 완료");
  });

console.log("next line");
