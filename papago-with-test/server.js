import express, { json } from "express";
import HTTP from "superagent";
import { detectLanguage } from "./api.js";
import config from "./public/config.js";

const { ClientID, ClientSecret } = config;
const CLIENT_ID = ClientID;
const CLIENT_SECRET = ClientSecret;

const app = express();

app.use(express.static("public"));
app.use(json());

app.get("/", (_, response) => {
  response.sendFile("index.html");
});

const TIMEOUT_MS = 1000; // 타임아웃 테스트 용도의 값(1초)

// 언어 감지 처리 API
app.post("/detect", async (req, res) => {
  console.log("POST: /detect 호출됨"); // Test Runner가 실행했는지 확인하기 위한 코드

  // 테스트 코드를 학습하기 위한 용도의 코드
  try {
    const timeoutPromise = new Promise((_, reject) =>
      setTimeout(() => reject(new Error("timeout")), TIMEOUT_MS)
    );

    const result = await Promise.race([
      detectLanguage(req.body), // 실제 호출
      timeoutPromise, // 1 초 초과 시 reject
    ]);
    res.send(result);
  } catch (err) {
    // console.error(err);
    const status = err.message === "timeout" ? 503 : 500;
    res.status(status).send({
      error: status === 503 ? "Papago API 타임아웃" : "Papago API 호출 실패",
    });
  }
});

// 번역 요청 처리 API
app.post("/translate", (request, response) => {
  const url = "https://papago.apigw.ntruss.com/nmt/v1/translation";

  HTTP.post(url)
    .send(request.body)
    .set("Content-Type", "application/json")
    .set("X-NCP-APIGW-API-KEY-ID", CLIENT_ID)
    .set("X-NCP-APIGW-API-KEY", CLIENT_SECRET)
    .end((error, result) => {
      if (result.statusCode === 200) {
        // 파파고 서버로부터 응답받은 결과 데이터
        const responseDataFromPapago = result.body;

        // 화면 출력에 필요한 값만 추출
        const {
          srcLangType: detectedLanguage,
          tarLangType: targetLanguage,
          translatedText,
        } = responseDataFromPapago.message.result;

        const responseData = {
          detectedLanguage,
          targetLanguage,
          translatedText,
        };

        response.send(responseData);
      } else {
        console.error(error);
      }
    });
});

const port = 3000;
app.listen(port, () =>
  console.log(
    `http://127.0.0.1:${port}/ 서버 프로세스가 ${port}번 포트에서 실행 중입니다.`
  )
);

export default app;
