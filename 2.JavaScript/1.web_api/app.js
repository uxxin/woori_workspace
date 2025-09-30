// DOM 객체 출력
console.log(document);
// log 대신 dir 사용해도 됨. 

//console.log(window); // 이걸로 alret 등등 확인해보기
// 원래라면 window.alert, window.document 로 해야 되는데 자주 사용해서 alert 만 해도 된다. 


//DOM 조작
const h1 = document.querySelector('h1');
console.log(h1);

console.log(h1.textContent);

//h1의 텍스트 값을 변경, 조작
h1.textContent = 'CSS란?';


//가독성 측면에서 사용권장
const h1Tag = document.getElementById('main-title');
console.log(h1Tag);
//#써야 하는거 알아보기


//class 이름이 list-item인 엘리먼트 가져오기
const liList = document.getElementsByClassName('list-item');
console.log(liList);

//liList.map(li => console.log(li));
//배열이 아니므로 출력 불가. liList의 타입 HTMLCollection은 배열이 아님. 


console.log(typeof liList);


//진짜 배열인지 확인하려면? Array클래스의 메서드를 활용
console.log(Array.isArray(liList));
//HTMLCollection을 배열 타입으로 변환해서 작성해야함. 

const liList2 = document.querySelectorAll('.list-item');
console.log(liList2);

console.log(Array.isArray(liList2));

liList2.forEach(li => {
    console.log(li)
});


//특정 HTML 엘리먼트의 위계를 활용해서 가져오기
console.log(document.querySelector('ul li:first-of-type'));