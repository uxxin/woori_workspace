//2.이벤트 핸들러 프로퍼티 방식으로 이벤트 부여하기
const evPropButton = document.getElementById('btn-ev-prop');

console.dir(evPropButton);
//개발자도구에 on으로 시작하는 것들=> 이벤트 종류

//클릭 이벤트가 발생했을 때 동작시킬 로직(함수)
const eventHandler = function(){
    console.log('button clicked!!!');
}

console.log(eventHandler);
eventHandler();

evPropButton.onclick= eventHandler;
console.dir(evPropButton); //onclick이 null에서 함수가 들어간걸로 바뀌는걸 확인 가능



// 3. addEventListner 방식
const addEvLsnrButton = document.getElementById('btn-add-ev-lsnr');

//addEventListern(이벤트 타입, 콜백함수);
//콜백함수: 함수의 인수로 전달되는 함수 ( 여기선 buttonHandler)
// addEvLsnrButton.addEventListener('click', buttonHandler);

// function buttonHandler(){
//     console.log('button clicked~~!!');
// }


//실제 사용 코드
addEvLsnrButton.addEventListener('click', () => console.log('button clicked~~'));