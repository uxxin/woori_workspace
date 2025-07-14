import { optionsFrom } from '../util.js';

// 언어 감지 요청 기능을 수행하는 함수
export const detectLanguage = async (url, text) => {
    let sourceLanguage;
    
    const body = { query: text };

    await fetch(url, optionsFrom('POST', body))
    .then(response => response.json())
    .then(data => {
        sourceLanguage = data.langCode;
    });

    return sourceLanguage;
}

// 언어 번역 요청 기능을 수행하는 함수
export const translateLanguage = async (url, detectedLanguage, targetLanguage, text) => {

    const body = {
        source: detectedLanguage,
        target: targetLanguage,
        text, // text: text와 같음
    }

    const result = await fetch(url, optionsFrom('POST', body))
    .then(response => response.json())
    .then(data => data)
    .catch(error => console.error(error));

    return result;
}