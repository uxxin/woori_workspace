// 2. 비동기 방식(Asynchronous)
// https://developer.mozilla.org/en-US/docs/Learn/JavaScript/Asynchronous/Introducing

/**
 * 현재 실행 중인 작업(Task)가 아직 종료되지 않은 상태라고 해도, 다음 작업을 곧바로 실행하는 방식
 */


// 맥락 - first()는 외부에 요청하는 로직
function first() {
    console.log('first() called');
}

// 맥락 - second()는 외부 응답이 올 동안 할 수 있는 로직
function second() {
    console.log('second() called');
}


// Web API에서 제공하는 API들 중에 비동기로 동작하는 함수들(setTimeout(), XMLHttpRequest, ...)
// setTimeout - 일정 시간 지연시킨 이후 실행시킬 함수를 작성할 때 활용
// setTimeout(콜백 함수, 지연시킬 시간)

//여기서 setTimeout(first(),1000) 이런식이면 1초 뒤에 실행 되는게 아니라 호출 시 실행됨. () 빼야 한다. 
setTimeout(first, 1000); // 1초 뒤에 first() 호출
second();
// 1초 지나기 전에 second() 실행됨. 

// -> second() called 찍힌 후 first() called 찍힘. 

// 비동기-> 함수 안 로직이 실행이 되지 않았는데도 함수가 종료됨. 



//JavaScript Visualizer에서 비동기 함수 해보기
//Task Queue는 Call stack 이 비어있을 때 Call stack으로 이동

//Thread는 하나다 !! 
//Event loop도 코드 - 무한 반복됨. 무한 스레드
// 그럼 스레드가 여러개인거 아니야 ? 
// 브라우저에서 JS 런타임 부분이 스레드 하나인거고 브라우저 자체엔 스레드가 여러개임. 