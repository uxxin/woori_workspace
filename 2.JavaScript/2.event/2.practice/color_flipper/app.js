const changeColorButton = document.getElementById('btn');
const color = document.getElementById('color');
//const container = document.querySelector('.container');
const body = document.body;
const text = document.querySelector('h2');


// const changeBackgroundColor = () => {
//     const r = Math.floor(Math.random() * 256); 
//     const g = Math.floor(Math.random() * 256);
//     const b = Math.floor(Math.random() * 256);
    
//     const randomColor = `rgb(${r}, ${g}, ${b})`; // ${값, 식}할당 가능
//     container.style.background = randomColor;
// };

const changeBackgroundColor = () => {
    const r = Math.floor(Math.random() * 256); 
    const g = Math.floor(Math.random() * 256);
    const b = Math.floor(Math.random() * 256);
    
    const randomColor = `rgb(${r}, ${g}, ${b})`; // ${값, 식}할당 가능
    body.style.background = randomColor;
};


const changeTextColor = () => {
    const r = Math.floor(Math.random() * 256); 
    const g = Math.floor(Math.random() * 256);
    const b = Math.floor(Math.random() * 256);
    
    const randomColor = `rgb(${r}, ${g}, ${b})`;
    color.textContent = randomColor;
    color.style.color = randomColor;
};

changeColorButton.addEventListener('click', changeTextColor);
changeColorButton.addEventListener('click', changeBackgroundColor);



// 화살표 함수로 해도 됨
// changeColorButton.addEventListener('click', () => {

// });