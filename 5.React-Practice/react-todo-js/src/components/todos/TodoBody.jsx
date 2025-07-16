import React from "react";
import TodoItem from "./TodoItem";

//key !! 알아두기

// const todoList = todos.map((todo) => <TodoItem todo={todo} key={todo.id} />);

const TodoBody = ({ todos }) => {
  // ()에 props로 찍고 콘솔 찍어보면 {todos} 형태로 나옴
  return (
    <ul>
      {/* props로 내려받은 todos 배열로 map 연산 */}
      {todos.map((todoItem) => (
        <TodoItem todo={todoItem} key={todoItem.id} />
      ))}
    </ul>
  );
};
//강사님 코드랑 비교해보기

export default TodoBody;
