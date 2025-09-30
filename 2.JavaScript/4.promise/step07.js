/*
    Promise를 활용하여 순서가 있는 2단계 비동기 요청 체인 구현하기
    - 차이점은 new Promise와 같이 직접 Promise를 생성하던 것을 get()함수 내에 래핑하여 처리

    상황. 내 서비스에 매일 자정에 글을 가장 첫 번째로(1등으로) 등록한 회원에게 상품을 주고싶음.
    게시글은 매일 자정 초기화됨(1등으로 등록한 사람이 누구인지는 매일 바뀜)

    -> 일단 게시글을 먼저 조회를 해야하고, 조회된 게시글에 나온 userId를 확인
    확인된 userId로 해당 user의 상세 정보를 조회

    1. posts/1로 id가 1번인 posts를 조회 -getPost(postId)
    2. 조회된 posts에서 userId가 1인 user 상세 정보를 조회 -getUser(post)
    (optional) 3. 조회된 user를 출력하는 메서드 - printUser(user)
*/

// 공통 처리할 GET 함수 - onload, onerror 등 요청 처리 로직은 유사하기 때문에 하나의 유틸함수로 작성
const get = (endpoint) => {
  //프로미스 객체를 밚놘하는 함수(체이닝을 사용하기 위해)

  const promise = new Promise((resolve, reject) => {
    // 비동기 작업 로직 작성 부분
    const xhr = new XMLHttpRequest();
    const url = `http://localhost:4000/${endpoint}`; // ← posts·users 경로만 바뀜
    xhr.open("GET", url);

    xhr.onload = () => {
      if (xhr.status === 200) {
        const data = JSON.parse(xhr.response);
        // console.log(data);

        resolve(data);
      } else {
        reject(new Error(`HTTP 오류: ${xhr.status}`));
      }
    };

    xhr.onerror = () => {
      reject(new Error("네트워크 오류: 서버 연결 실패"));
    };

    xhr.send();
  });

  return promise;
};

// 1단계: id로 게시글(posts) 조회
// const getPost = (postId) => get(`posts/${postId}`);

const getPost = (postId) => {
  return get(`posts/${postId}`);
};

//2단계: 게시글 작성자를 userId로 조회
const getUser = (post) => get(`users/${post.userId}`);

//3단계: 결과 출력용 헬퍼 함수
const printUser = (user) => console.log(`작성자 상세 정보: `, user);

getPost(1) // Promise 객체를 반환하기 때문에 then, catch와 같은 체이닝 가능
  .then(getUser)
  .then(printUser);
//   .then((user) => console.log(user)); //getUser로 받은 user 정보 출력
//   .then((data) => console.log(data));
// 이 data가 위 post임.
