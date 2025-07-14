const express = require('express')

const app = express()
const port = 3000

const rootHandler = (req, res) => {
  res.send('Hello World!')
}

app.get('/', rootHandler);

// 실행될 Node.js의 프로세스가 몇번 포트에서 사용자의 요청을 대기하고 있을 것인가?
app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})
