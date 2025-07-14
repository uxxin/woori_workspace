// superagent 모듈을 HTTP라는 이름으로 사용하기 위해 import
import HTTP from 'superagent';

const DETECT_LANGUAGE_URL = 'https://papago.apigw.ntruss.com/langs/v1/dect';
const CLIENT_ID = 'g1ns2d9lat'
const CLIENT_SECRET = 'yuMGLhvPatsfiY3vZ1MtHOAw3PGmQEjgFw5Vf87N'
const TRANSLATE_LANGUAGE_URL = 'https://papago.apigw.ntruss.com/nmt/v1/translation';


// 언어 감지 
export const detectLangs = () => {

    const payload = { //요청 바디 데이터 
        query: '안녕하세요?'
    };


    // API 요청을 수행할 수 있는 코드(클라이언트 라이브러리)
    HTTP.post(DETECT_LANGUAGE_URL) //보낼 엔드포인트(주소)
    .send(payload) //보낼 데이터
    .set('X-NCP-APIGW-API-KEY-ID', CLIENT_ID)
    .set('X-NCP-APIGW-API-KEY', CLIENT_SECRET)
    //error가 발생했을 때는 콜백 함수의 인수 중 error에 넣어줌
    //결과값은 result에 넣어줌
    .end((error, result)=>{
        if(result.statusCode === 200){ //응답 결과값이 200, OK 일 경우
            const resultData = result.body;
            console.log(resultData);
        }
        else{
            console.error(error);
        }
    }); //응답받은 결과값 취득했을 때 코드 작성 부분

}


// detectLangs();

export const translate = () => {

    const payload = { //요청 바디 데이터 
        source: 'ko',
        target: 'en',
        text: '안녕하세요',
        glossaryKey: '', 
        replaceInfo: '',
        honorific: true,
    };


    // API 요청을 수행할 수 있는 코드(클라이언트 라이브러리)
    HTTP.post(TRANSLATE_LANGUAGE_URL) //보낼 엔드포인트(주소)
    .send(payload) //보낼 데이터
    .set('X-NCP-APIGW-API-KEY-ID', CLIENT_ID)
    .set('X-NCP-APIGW-API-KEY', CLIENT_SECRET)
    //error가 발생했을 때는 콜백 함수의 인수 중 error에 넣어줌
    //결과값은 result에 넣어줌
    .end((error, result)=>{
        if(result.statusCode === 200){ //응답 결과값이 200, OK 일 경우
            const resultData = result.body;
            console.log(resultData);
        }
        else{
            console.error(error);
        }
    }); //응답받은 결과값 취득했을 때 코드 작성 부분

}

translate();