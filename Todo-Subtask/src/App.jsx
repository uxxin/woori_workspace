import TodoBody from "./components/todos/TodoBody";
import TodoHeader from "./components/todos/TodoHeader";
import DefaultLayout from "./layouts/DefaultLayout";
import { useState } from "react";
import MyContext from "./context/TodoContext";
import dummyTodos from "./data/dummyTodos";

// 해당 컴포넌트의 파일명은 App.jsx(js)로 만듦
function App() {
  //dummyTodos를 App.jsx가 관리하는 하나의 상태로 등록
  const [todos, setTodos] = useState(dummyTodos);

  // 1. 할일 등록 기능
  const addTodoHandler = (todo) => {
    console.log(todo);
    todo.id = todos.length + 1;
    setTodos((todos) => [todo, ...todos]);
  };

  const updateTodoHandler = (updateTodo) => {
    const updatedTodos = todos.map((todo) =>
      todo.id === updateTodo.id ? updateTodo : todo
    );
    setTodos(updatedTodos);
  };

  const deleteTodoHandler = (id) => {
    const updatedTodos = todos.filter((todo) => todo.id != id);
    setTodos(updatedTodos);
  };

  const [filter, setFilter] = useState("ALL");

  const filteredTodos = todos.filter((todo) => {
    return filter === "ALL" ? true : todo.category === filter;
  });

  // 새로운 카테고리값 newCategory 값과 id 를 통해 해당 항목의 category 변경
  const updateCategoryHandler = (id, newCategory) => {
    const updatedTodos = todos.map((todo) =>
      todo.id === id ? { ...todo, category: newCategory } : todo
    );
    setTodos(updatedTodos);
  };

  return (
    <DefaultLayout>
      <header>
        <h1 className="pt-8 mx-auto text-red-200 max-w-max text-7xl">
          <img
            className="ml-4"
            src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Thought%20Balloon.png"
            alt="Thought Balloon"
            width="75"
            height="75"
          />
          <img
            src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Animals/Seal.png"
            alt="Seal"
            width="75"
            height="75"
          />
        </h1>
      </header>

      <MyContext.Provider
        value={{
          addTodo: addTodoHandler,
          updateTodo: updateTodoHandler,
          updateCategory: updateCategoryHandler,
          deleteTodo: deleteTodoHandler,
          setFilter,
        }}
      >
        <section className="max-w-xl m-4 mx-auto">
          <TodoHeader />
          {/* dummyTodos라는 데이터를 todos라는 이름으로 전달 */}
          {/* TodoBody(dummyTodos)형태로 호출 */}
          <TodoBody todos={filteredTodos} />
          {/* {todos}는 위에서 setTodos로 관리하는 todos (dummyTodos + 새로 입력한 todo들)이다 */}
        </section>
      </MyContext.Provider>
    </DefaultLayout>
  );
}

export default App;
