const express = require('express')

const app = express()
const port = 3000
// 정적 리소스 호스팅, Express.js의 미들웨어 기능으로 활용
// public이라는 이름의 폴더를 정적 리소스가 제공되는 곳으로 적용
app.use(express.static('public'))

const rootHandler = (req, res) => {
  res.sendFile('index.html'); // /public/index.html의 파일을 응답하게 됨
}

// 루트 경로로 요청하면 Hello World가 아닌 index.html이 응답되도록
app.get('/', rootHandler);

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})
