import express, { json } from 'express';
import HTTP from 'superagent';
import config from './public/config.js';

const {ClientID, ClientSecret} = config;

const app = express();

app.use(express.static('public'))
app.use(json())

const clientId = ClientID;
const clientSecret = ClientSecret;

app.get('/', (_, response) => {
    response.sendFile('index.html');
});

// 언어 감지 처리 API
app.post('/detect', (request, response) => {
    const url = 'https://papago.apigw.ntruss.com/langs/v1/dect';

    HTTP.post(url)
        .send(request.body)
        .set('Content-Type', 'application/json')
        .set('X-NCP-APIGW-API-KEY-ID', clientId)
        .set('X-NCP-APIGW-API-KEY', clientSecret)
        .end((error, result) => {
            if (result.statusCode === 200) {
                response.send(result.body);
            } else {
                console.error(error);
            }
        });
});

// 번역 요청 처리 API
app.post('/translate', (request, response) => {

    const url = 'https://papago.apigw.ntruss.com/nmt/v1/translation';

    HTTP.post(url)
        .send(request.body)
        .set('Content-Type', 'application/json')
        .set('X-NCP-APIGW-API-KEY-ID', clientId)
        .set('X-NCP-APIGW-API-KEY', clientSecret)
        .end((error, result) => {
            if (result.statusCode === 200) {
                // 파파고 서버로부터 응답받은 결과 데이터
                const responseDataFromPapago = result.body;

                // 화면 출력에 필요한 값만 추출
                const { srcLangType: detectedLanguage, tarLangType: targetLanguage, translatedText } = responseDataFromPapago.message.result;

                const responseData = {
                    detectedLanguage,
                    targetLanguage,
                    translatedText
                }

                response.send(responseData);
            } else {
                console.error(error);
            }
        });
});

const port = 3000;
app.listen(port,
    () => console.log(`http://127.0.0.1:${port}/ 서버 프로세스가 3000번 포트에서 실행 중입니다.`)
);