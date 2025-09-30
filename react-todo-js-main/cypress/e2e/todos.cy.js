// 시험볼 때는 내 포트 번호가 맞는지 확인
const PORT = 5173;

// 해당 describe 내에 존재하는 테스트 케이스들(it())에 대해서 각 테스트를 실행하기 전에 먼저 실행할 코드들
beforeEach(() => {
  // Todo 메인 페이지에 접근
  cy.visit(`http://localhost:${PORT}`);
  cy.wait(1500); // 1.5초 대기
});

describe("할일 등록 기능", () => {
  it("Add Todo 버튼을 클릭할 경우, 할일 등록 폼 Modal이 활성화된다.", () => {
    // Add Todo 버튼을 클릭하기
    cy.get('[data-cy="add-todo-button"]').click(); // Add Todo버튼을 마우스 클릭

    // Modal 창 안에 작성된 Title이 '할일 등록' 텍스트가 있는지 검증
    cy.get('[data-cy="todo-form-title"]').should("have.text", "할일 등록");
  });

  it("할 일이 등록되면 할 일 목록에 등록한 새로운 할 일이 추가되어 표시된다.", () => {
    cy.wait(1000);
    cy.get('[data-cy="add-todo-button"]').click();

    // 할 일 입력
    cy.get("#title").type("실기 문제풀기");
    cy.wait(500);
    cy.get("#summary").type("실기 평가를 통과한다.. 제발!!");
    cy.wait(500);

    // 할일 추가
    cy.get('[data-cy="process-add-or-update"]').click();
    cy.wait(1000);
    cy.contains("실기 문제풀기").should("exist");
  });
});

describe("할일 필터링 기능", () => {
  it("TODO 필터 선택 시 :책갈피_탭: 아이콘이 있는 항목만 보여야 한다.", () => {
    cy.get('[data-cy="todo-filter"]').select("TODO");
    cy.get('[data-cy="todo-item"]').each(($el) => {
      cy.wrap($el).should("contain.text", "📑");
    });
  });
  // 그 외 다른 카테고리는 생략
});

describe("할일 삭제 기능", () => {
  it("특정 할일의 휴지통 버튼을 클릭할 경우, 선택된 할일이 제거된다.", () => {
    // 삭제 전: 해당 제목이 존재해야 함
    cy.contains('[data-cy="todo-item"]', "점심 먹기").should("exist");

    cy.contains('[data-cy="todo-item"]', "점심 먹기")
      .find('[data-cy="recycle-bin"]')
      .click();

    cy.contains('[data-cy="todo-item"]', "점심 먹기").should("not.exist");
  });
});
