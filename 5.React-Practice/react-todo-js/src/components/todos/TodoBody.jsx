import React from "react";
import TodoItem from "./TodoItem";

// const todos = [
//   {
//     id: 1,
//     title: "React 공부",
//     summary: "React를 공부한다.",
//     category: "TODO",
//   },
//   {
//     id: 2,
//     title: "점심 먹기",
//     summary: "점심을 먹는다.",
//     category: "PROGRESS",
//   },
//   {
//     id: 3,
//     title: "커피 마시기",
//     summary: "커피를 마신다.",
//     category: "DONE",
//   },
// ];

// 하나의 할일을 가진 TodoItem이라는 이름의 컴포넌트를 작성해서
// TodoBody 내부에서 렌더링

//key !! 알아두기

// const todoList = todos.map((todo) => <TodoItem todo={todo} key={todo.id} />);

const TodoBody = ({ todos, onUpdate, onChange }) => {
  // ()에 props로 찍고 콘솔 찍어보면 {todos} 형태로 나옴
  return (
    <ul>
      {/* props로 내려받은 todos 배열로 map 연산 */}
      {todos.map((todoItem) => (
        <TodoItem
          onChange={onChange}
          onUpdate={onUpdate}
          todo={todoItem}
          key={todoItem.id}
        />
      ))}
    </ul>
  );
};
//강사님 코드랑 비교해보기

export default TodoBody;
