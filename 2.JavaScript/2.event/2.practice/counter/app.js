const decreaseButton = document.getElementsByClassName('btn decrease');
const resetButton = document.querySelector('.btn reset');
const increaseButton = document.querySelector('.btn increase');
const valueElement = document.getElementById('value');


//forEach문 사용 



const decreaseValue = () => {
    let newValue = valueElement.textContent ;
    value = Number(value)+1;
    value.textContent = newValue;
}


console.log(document);
console.dir(document);

decreaseButton.addEventListener('click', decreaseValue);
