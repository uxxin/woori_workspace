/*
    XMLHttpRequest -> fetch로 교체한 버전

    1. fetch() 자체가 Promise를 반환하므로 별도의 new Promise같은 래핑이 필요없음

*/

const get = async (endpoint) => {
  const url = `http://localhost:4000/${endpoint}`;

  let response;
  try {
    response = await fetch(url); // 네트워크 오류면 여기서 바로 throw
    console.log(response);
  } catch (err) {
    // fetch() 자체가 실패한 경우 (TypeError: Failed to fetch ...)
    throw new Error("네트워크 오류: 서버 연결 실패");
  }

  if (!response.ok) {
    // 2xx가 아닌 HTTP 상태코드
    throw new Error(`HTTP 오류: ${response.status}`);
  }

  /* 정상 응답 → JSON 파싱 */
  return response.json();
};

// 1단계: id로 게시글(posts) 조회
const getPost = (postId) => {
  return get(`posts/${postId}`);
};

// 2단계: 게시글 작성자를 userId로 조회
const getUser = (post) => get(`users/${post.userId}`);

// 3단계: 결과 출력용 헬퍼 함수
const printUser = (user) => console.log(`작성자 상세 정보:`, user);

// 3 단계를 하나의 함수로 묶어서 활용
/*
    async - 함수 키워드 앞에 붙임, 해당 함수가 비동기 함수로 동작한다라고 지정
*/
async function findUser() {
  try {
    const post = await getPost(1);
    const user = await getUser(post);
    printUser(user);
  } catch (err) {
    console.error(err);
  }
}

console.log("1");
findUser(); // 비동기로 동작
console.log("2");
// 1 출력
// 2 출력
// findUser() 실행
