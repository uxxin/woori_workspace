//서버에서 받아온 데이터라고 가정
const dummyTodos = [
  {
    id: 1,
    title: "React 공부",
    category: "TODO",
    subtasks: [
      { id: 101, title: "props 학습", done: false },
      { id: 102, title: "reducer 학습", done: false },
    ],
  },
  {
    id: 2,
    title: "점심 먹기",
    category: "PROGRESS",
    subtasks: [
      { id: 201, title: "밥먹기", done: true },
      { id: 202, title: "비타민 먹기", done: false },
    ],
  },
  {
    id: 3,
    title: "커피 마시기",
    summary: "커피를 마신다.",
    category: "DONE",
    subtasks: [
      { id: 301, title: "props 학습", done: true },
      { id: 302, title: "reducer 학습", done: true },
    ],
  },
];

export default dummyTodos;
