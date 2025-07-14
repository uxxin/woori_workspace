const [sourceSelect, targetSelect] = document.getElementsByTagName("select");
const [sourceTextArea, targetTextArea] =
  document.getElementsByTagName("textarea");

let targetLanguage = "en";
targetSelect.addEventListener("change", (event) => {
  targetLanguage = event.target.value;
});

// 공통 fetch 함수
const fetchJson = async (url, method = "POST", body = {}) => {
  const response = await fetch(url, {
    method,
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body),
  });

  if (!response.ok) {
    throw new Error(`요청 실패: ${response.status}`);
  }

  return response.json();
};

// 언어 감지 함수
const detectLang = async (text) => {
  const res = await fetchJson("/detect", "POST", { query: text });
  return res.langCode;
};

// 번역 함수
const translateLang = async ({ source, target, text }) => {
  const res = await fetchJson("/translate", "POST", {
    source,
    target,
    text,
  });
  return res.translatedText;
};

// 메인 로직 함수
const papago = async (text) => {
  try {
    const detectedLang = await detectLang(text);
    sourceSelect.value = detectedLang;

    const translated = await translateLang({
      source: detectedLang,
      target: targetLanguage,
      text,
    });

    targetTextArea.value = translated;
  } catch (err) {
    console.error("번역 중 오류:", err);
  }
};

// 2초 debounce 적용
let timer;
sourceTextArea.addEventListener("input", () => {
  if (timer) clearTimeout(timer);

  timer = setTimeout(() => {
    const text = sourceTextArea.value.trim();
    if (text) {
      papago(text);
    }
  }, 2000);
});

//언어 바뀌면 내용도 바꾸게 해야 함.
//api 연결과 UI 별개로 구분되도록 코딩하는 것이 좋음.
