// 콜백함수 기본

function greeting(name) {
    console.log(`Hello ${name}`);
}

// 두번째 인수인 callbackFn은 함수를 받는 자리
function processUserInput(name, callbackFn) {
    // callbackFn = greeting() 함수가 할당됨
    
    // 두번째 인수로 받은 함수를 호출
    callbackFn(name);
}

// greeting을 콜백 함수
// 콜백 함수 - "어떤 함수의 인수로 전달되는 함수"
//processUserInput('Yoo', greeting);


// 함수 2개 구현

// 1. 쿠팡에서 사과를 기다리는 함수 waitCoupang(appleBox, callback)
// 함수의 동작: '쿠팡에서 ${appleBox}가 도착했다' 출력

// 2. bringItToNeighbor()
// 함수 동작: '옆집 아주머니에게 전달 완료!' 출력

// appleBox: '사과'라는 문자열 전달

function waitCoupang(appleBox, callback){
    console.log(`쿠팡에서 ${appleBox}가 도착했다`);
    callback();
}


function bringItToNeighbor(){
    console.log(`옆집 아주머니에게 전달 완료!`);
}

waitCoupang('사과', bringItToNeighbor);