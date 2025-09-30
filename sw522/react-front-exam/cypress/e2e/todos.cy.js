// 내 포트 번호가 맞는지 확인!
const PORT = 5173;

beforeEach(() => {
  // Todo 메인 페이지에 접근
  cy.visit(`http://localhost:${PORT}`);
});

// ↓ 각 테스트 시나리오를 실행하거나 스킵하기 위해서는 .skip 코드를 제거하거나 추가하시면 됩니다.

// 문제 1 테스트 시나리오
describe("# 문제1 - 할 일 삭제 전 삭제 확인 안내 기능", () => {
  beforeEach(() => {
    cy.visit("http://localhost:5173/");
    cy.wait(1000);
  });

  it("휴지통 버튼 클릭 시 삭제 확인 안내 모달이 활성화된다.", () => {
    cy.get('[data-cy="recycle-bin"]:first').click();
    cy.wait(1000);
  });

  it("삭제 확인 안내 모달에서 삭제 버튼 클릭 시 해당 할 일이 제거된다.", () => {
    cy.get('[data-cy="recycle-bin"]:first').click();
    cy.wait(500);

    // 삭제 버튼 클릭
    cy.get('[data-cy="process-remove"]').click();
    cy.wait(500);

    // 할 일 목록 화면에서 "React 공부" 할 일이 존재하지 않는지 확인
    cy.contains("React 공부").should("not.exist");
    cy.wait(500);
  });

  it("삭제 확인 안내 모달에서 취소 버튼 클릭 시 삭제 처리를 수행하지 않고 모달이 비활성화된다.", () => {
    cy.get('[data-cy="recycle-bin"]:first').click();
    cy.wait(500);

    cy.get('[data-cy="process-cancel"]').click();
    cy.wait(500);

    cy.contains("React 공부").should("exist");
    cy.wait(500);
  });
});

// 문제 2 테스트 시나리오
describe("# 문제2 - 할 일 마감 기한 등록 기능", () => {
  it("할 일 등록 폼에 날짜를 입력할 수 있는 항목이 존재한다.", () => {
    // Add Todo 버튼
    cy.get('[data-cy="add-todo-button"]:first').click();
    cy.wait(500);

    // 날짜 입력 폼이 존재하는지 확인
    cy.get("input[type=date]").should("exist");
  });

  it("할 일이 등록되면 할 일 목록에 등록한 새로운 할 일이 추가되어 표시된다.", () => {
    cy.get('[data-cy="add-todo-button"]:first').click();

    // 할 일 입력
    cy.get("#title").type("실기 문제풀기");
    cy.wait(500);
    cy.get("#summary").type("실기 평가를 통과한다.. 제발!!");
    cy.wait(500);
    cy.get("#category").select("PROGRESS");
    cy.wait(500);
    cy.get("#deadline").type("2025-07-29");
    cy.wait(500);

    // 할일 추가
    cy.get('[data-cy="process-add-or-update"]').click();

    cy.contains("실기 문제풀기").should("exist");
  });

  it('7월 29일 기준으로 7월 31일까지 수행해야할 할 일 등록 시, 등록된 할 일에 "2일 남음"이라는 문구가 표시된다.(등록 당일 날짜를 포함하였기 때문에 2일)', () => {
    cy.wait(2000);
    cy.get('[data-cy="add-todo-button"]').click();

    // 할 일 입력
    cy.get("#title").type("백엔드 공부");
    cy.wait(500);
    cy.get("#summary").type("백엔드 공부를 시작한다.");
    cy.wait(500);
    cy.get("#category").select("TODO");
    cy.wait(500);
    cy.get("#deadline").type("2025-07-31");
    cy.wait(1500);

    // 할일 추가
    cy.get('[data-cy="process-add-or-update"]').click();
    cy.wait(1000);

    cy.get('[data-cy="todo-item"]:last').within(() => {
      cy.contains(`2일 남음`).should("exist");
    });
  });
});

// 문제 3 테스트 시나리오
describe("# 문제3 - 툴팁(Tooltip) 기능", () => {
  it('Add Todo 버튼에 마우스를 포커싱할 경우, "새로운 할 일을 등록하세요"라는 메시지가 작성된 툴팁이 활성화된다.', () => {
    // 2. Add Todo 버튼 찾기
    cy.get("[data-cy=add-todo-button]").realHover(); // 진짜 hover 발생

    cy.contains("새로운 할 일을 등록하세요").should("be.visible");
  });
});

// 문제 4(독자 기능 구현)는 별도의 테스트 코드가 없으며, LMS 문제 4번 가이드 참고하여 제출
