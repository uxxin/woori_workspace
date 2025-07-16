//서버에서 받아온 데이터라고 가정
const dummyTodos = [
  {
    id: 1,
    title: "React 공부",
    summary: "React를 공부한다.",
    category: "DONE",
    subtasks: [
      { id: 101, title: "공부하기 1-1", done: true },
      { id: 102, title: "공부하기 1-2", done: true },
      { id: 103, title: "공부하기 1-3", done: true },
    ],
  },
  {
    id: 2,
    title: "점심 먹기",
    summary: "점심을 먹는다.",
    category: "PROGRESS",
    subtasks: [
      { id: 201, title: "공부하기 2-1", done: true },
      { id: 202, title: "공부하기 2-2", done: false },
      { id: 203, title: "공부하기 2-3", done: true },
    ],
  },
  {
    id: 3,
    title: "커피 마시기",
    summary: "커피를 마신다.",
    category: "TODO",
    subtasks: [
      { id: 201, title: "공부하기 3-1", done: false },
      { id: 202, title: "공부하기 3-2", done: false },
      { id: 203, title: "공부하기 3-3", done: false },
    ],
  },
];

export default dummyTodos;
