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

  // subtask의 상태를 변경하고, 이에 따라 category를 update 해주는 함수
  const updateSubtaskHandler = (todoId, subtaskId, done) => {
    // update된 Todo (mapping 해야 함. )
    const updatedTodos = todos.map((todo) => {
      // 이제 todo 들에 대해 어떻게 mapping을 할 것인가.
      // => 바꾸려하는 Todo의 아이디와 기존에 있던 Todo id를 비교
      if (todo.id === todoId) {
        // 같은 경우 update 하기 -> 체크에 따라서 바꾸는거니까 title이나 id 건드리지 말고 done 만 변경하면 됨.
        const updatedSubtasks = todo.subtasks.map((subtask) =>
          subtask.id === subtaskId ? { ...subtask, done } : subtask
        );
        // done이 true인 subtask의 개수 세기
        const doneCount = updatedSubtasks.filter((s) => s.done).length;

        // 처음엔 category 전부 TODO로 초기화
        let newCategory = "TODO";
        //doneCount 의 개수에 따라 category 설정
        if (doneCount === updatedSubtasks.length) newCategory = "DONE";
        else if (doneCount > 0) newCategory = "PROGRESS";
        // 반환할 내용 => todo , 업데이트 된 카테고리와 subtasks
        return {
          ...todo,
          subtasks: updatedSubtasks,
          category: newCategory,
        };
        // 바꾸려하는 Todo의 아이디와 아이디 다르면 ? 바뀔거 없이 그냥 Todo 반환
      }
      return todo;
    });
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
          updateSubtask: updateSubtaskHandler,
        }}
      >
        <section className="max-w-xl m-4 mx-auto">
          <TodoHeader />
          {/* dummyTodos라는 데이터를 todos라는 이름으로 전달 */}
          <TodoBody todos={filteredTodos} />
          {/* {todos}는 위에서 setTodos로 관리하는 todos (dummyTodos + 새로 입력한 todo들)이다 */}
        </section>
      </MyContext.Provider>
    </DefaultLayout>
  );
}

export default App;
