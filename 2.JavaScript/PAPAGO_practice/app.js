// 언어 감지 api ( 입력: 텍스트 , 반환: 국가코드 )
// 번역 api (입력: 국가코드, 텍스트 / 반환: 번역된 텍스트 )
 
// 주의 ! => api key 는 따로 뽑아서 쓰기 
// 요청할 때 debounce 사용하기. 

import config from "./config.js";
const {clientID} = config.ClientID;
const {clientSecret} = config.ClientSecret;

// 1. 생성자 함수를 통해 XMLHttpRequest 인스턴스(객체) 생성
const xhr = new XMLHttpRequest();
 
// 2. onload 이벤트 프로퍼티에 서버로부터의 응답이 완료되었는지 확인하는 코드 작성
xhr.onload = () => {
    if (xhr.readyState === xhr.DONE && xhr.status === 200) {
        const responseData = xhr.responseText; // responseText: 서버로부터 받은 응답 데이터
        const result = JSON.parse(responseData); // JSON 역직렬화
        console.log(result); 
    }
}
 

// 언어 입력 받는 파트 
// -----> 
// 언어입력받을걸
// 3. 요청 준비(open(method, url, async, ..))
const detectUrl = 'https://papago.apigw.ntruss.com/langs/v1/dect';
xhr.open('POST', detectUrl);

xhr.setRequestHeader('X-NCP-APIGW-API-KEY-ID', clientID);
xhr.setRequestHeader('X-NCP-APIGW-API-KEY', clientSecret);
xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
 
// 4. 요청 실제 전송
xhr.send();


