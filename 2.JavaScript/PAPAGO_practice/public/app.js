//source : 번역할 텍스트
//target : 번역된 결과 텍스트

// 배열 디스트럭처링 활용
const [sourceSelect, targetSelect] = document.getElementsByTagName('select');
const [sourceTextArea, targetTextArea]= document.getElementsByTagName('textarea');


//현업에서는 직접 구현 말고 useDebounce 사용. 
let timerId;
sourceTextArea.addEventListener('input', event => {

    if (timerId) clearTimeout(timerId);
    
    //이벤트 리스너 안에 있으면 어쨋든 안 -> ㅇ , 아, 안 처럼 3번 호출됨. 
    // 마지막으로 타이핑한 후 2초 경과된 타이머만 그대로 동작하도록
    timerId = setTimeout(()=> {
        console.log(event.target.value);
    }, 2000); 
});


