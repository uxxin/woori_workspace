import express, { json } from 'express';

const app = express()
const port = 3000
// 미들웨어 작성 부분(app 밑)
app.use(express.static('public'))
app.use(express.json()) // 역직렬화 모듈

const rootHandler = (_, res) => {
  res.sendFile('index.html');
}

// 루트 경로 요청 시 index.html을 응답하는 핸들러
app.get('/', rootHandler);

// TODO: 언어 감지 요청 처리할 핸들러
app.post('/detect', (request, response) => {
  // 언어 감지 요청 처리 로직 작성 부분
  console.log(request.body); // 요청 객체에서 body(페이로드)의 값을 추출

});

// TODO: 언어 번역 요청 처리할 핸들러
app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})
