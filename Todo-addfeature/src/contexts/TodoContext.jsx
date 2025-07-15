// Todo라는 도메인과 관련된 컨텍스트 파일

import { createContext, useContext, useReducer } from "react";

const dummyTodos = [
  {
    id: 1,
    title: "React 공부",
    category: "TODO",
    subtasks: [
      { id: 101, title: "props 학습", category: "TODO" },
      { id: 102, title: "reducer 학습", category: "TODO" },
    ],
  },
  {
    id: 2,
    title: "점심 먹기",
    category: "PROGRESS",
    subtasks: [
      { id: 201, title: "밥먹기", category: "TODO" },
      { id: 202, title: "비타민 먹기 학습", category: "TODO" },
    ],
  },
  {
    id: 3,
    title: "커피 마시기",
    summary: "커피를 마신다.",
    category: "DONE",
    subtasks: [
      { id: 301, title: "props 학습", category: "TODO" },
      { id: 302, title: "reducer 학습", category: "TODO" },
    ],
  },
];

// 할일 데이터를 제공하는 컨텍스트
export const TodoContext = createContext();

// 할일 상태 변경(dispatch) 로직들을 제공하는 컨텍스트(useReducer와 관련된 컨텍스트)
export const TodoDispatchContext = createContext();

// TodoContext와 TodoDispatchContext를 감싼(Wrapping) 컴포넌트(추상화 맥락)
export const TodoProvider = ({ children }) => {
  const [todos, dispatch] = useReducer(reducer, {
    data: dummyTodos,
    category: "ALL",
  });

  return (
    <TodoContext.Provider value={todos}>
      <TodoDispatchContext.Provider value={dispatch}>
        {children}
      </TodoDispatchContext.Provider>
    </TodoContext.Provider>
  );
};

// 외부에서 사용하기 편하게 추상화시킨 변수
export const useTodos = () => useContext(TodoContext);
export const useTodosDispatch = () => useContext(TodoDispatchContext);

// reducer 함수
const reducer = (todos, action) => {
  const { data, category } = todos;

  switch (action.type) {
    case "ADD":
      const { newTodo } = action;
      return { data: [...data, newTodo], category };

    case "UPDATE":
      const { updateTodo } = action;
      const updatedTodos = data.map((todo) =>
        todo.id === updateTodo.id ? { ...updateTodo } : todo
      );
      return { data: updatedTodos, category };

    case "DELETE":
      const { id } = action;
      const deletedTodos = data.filter((todo) => todo.id !== id);
      return { data: deletedTodos, category };

    case "FILTER":
      return { data, category: action.selectedCategory };
  }
};
