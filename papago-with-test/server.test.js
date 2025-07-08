import { describe, expect, test, vi } from "vitest";
import { detectLanguage } from "./api";
import request from "supertest";

// 1. detectLanguage 함수만 모킹 (api.js에 있는 detectLanguage 함수를 모킹)
vi.mock("./api.js", () => {
  return {
    detectLanguage: vi.fn(),
  };
});

// 2. 모듈 가져오기(테스트를 하려면 해당 함수를 호출해야 하긴 하기때문에)
import app from "./server.js"; //server.js에 있는 app.post('/detect',{}=>{})를 호출해야하기 때문에
import { detectLanguage } from "./api.js";

describe("언어 감지 테스트", () => {
  //api.js에 작성된 detectLanguage를 호출해서 테스트
  //그때 detectLanguage를 실제로 호출하진 않음. (외부 api 코드+비용 등의 문제)
  //즉, detectLanguage를 모킹(가짜 객체로 활용)

  test("성공 시 200 OK와 결과 데이터를 봔환한다", async () => {
    // "안녕하세요"라고 보냈을 때 ko라고 응답하도록 모킹
    // Given
    detectLanguage.mockResolvedValue({
      langCode: "ko",
    });

    // When, 언어 감지를 호출했을 때
    const result = await request(app)
      .post("/detect")
      .send({ query: "안녕하세요" });

    //Then
    expect(result.statusCode).toBe(200);
    expect(result.body).toEqual({ langCode: "ko" });
  });

  test("서버에 문제가 있을 경우 500에러를 응답한다.", async () => {
    detectLanguage.mockRejectedValue(new Error("Papago API 호출 실패"));

    // 500 에러일 것이고, 에러 메세지는 'Papago API 호출 실패'일 것이다.
    const result = await request(app)
      .post("/detect")
      .send({ query: "가나다라마바" });
    expect(result.statusCode).toBe(500);
    expect(result.body).toEqual({ error: "Papago API 호출 실패" });
  });

  test("Papago 서버(우리가 만든 서버)가 언어 감지 결과를 클라이언트에게 응답하지 않을 경우, 503을 반환한다.", async () => {
    //TODO: detectLanguage가 영원히 pending 상태로 남게 모킹
    detectLanguage.mockImplementation(() => new Promise(() => {}));
    // 빈거 넣어주면 영원히 pending 상태됨.

    //나머지는 동일함, 503 에러일 것이고, 에러 메세는 'Papago API 타임아웃'일 것이다.
    const result = await request(app)
      .post("/detect")
      .send({ query: "HaHaHaHaHaHaHa" });
    expect(result.statusCode).toBe(503);
    expect(result.body).toEqual({ error: "Papago API 타임아웃" });
  });
});
