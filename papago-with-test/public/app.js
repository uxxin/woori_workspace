import { detectLanguage, translateLanguage } from "./api/api.js";
import { changeLanguage } from "./util.js";

const [sourceSelect, targetSelect] = document.getElementsByTagName('select');
const [sourceTextArea, targetTextArea] = document.getElementsByTagName('textarea');

let targetLang = 'en';
targetSelect.addEventListener('change', (event) => targetLang = event.target.value);

let timer;
sourceTextArea.addEventListener('input', (event) => {
    if (timer) clearTimeout(timer);

    timer = setTimeout(async () => {
        const text = event.target.value;

        const detectedResult = await detectLanguage('/detect', text);

        targetLang = changeLanguage(detectedResult, targetLang);

        const { detectedLanguage, targetLanguage, translatedText } = await translateLanguage('/translate', detectedResult, targetLang, text);
        console.log(detectedLanguage, targetLanguage, translatedText);
        

        // 결과값 바인딩
        sourceSelect.value = detectedLanguage;
        targetSelect.value = targetLanguage;
        targetTextArea.value = translatedText;
    }, 1500);
    
});
