const executor = () => {};

const promise = new Promise(executor); //Promise 객체 생성
// 아직 promise가 이행되지 않은 상태라 State = pending
console.log(promise);
// pending: 방금 약속을 맺기만 했고, 아직 약속의 이행/실패 결과가 나오지 않은 상태
