// App이라는 이름의 함수형 컴포넌트

import TodoBody from "./components/todos/TodoBody";
import TodoHeader from "./components/todos/TodoHeader";
import DefaultLayout from "./layouts/DefaultLayout";
import Modal from "./components/ui/Modal";
import { useState } from "react";
import MyContext from "./context/TodoContext";

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
      { id: 202, title: "비타민 먹기 학습", done: false },
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

// 해당 컴포넌트의 파일명은 App.jsx(js)로 만듦
function App() {
  //dummyTodos를 App.jsx가 관리하는 하나의 상태로 등록
  const [todos, setTodos] = useState(dummyTodos);

  // 1. 할일 등록 기능
  // TodoForm으로부터 전달받은 할일 객체를 가지고 todos 배열의 뒤쪽에 추가하는 로직
  const addTodoHandler = (todo) => {
    console.log(todo); // TodoForm으로부터 입력된 값들을 잘 전달받았는지

    //TODO: 배열에 추가하는 로직
    // 새로 작성한 데이터 + 더미데이터
    todo.id = todos.length + 1;
    setTodos((todos) => [todo, ...todos]);

    // //이렇게 해도 된다-1
    // todo.id = todos.length+1;
    // const updatedTodos = [...todos, todo];
    // setTodos(updatedTodos);

    // //강사님 방식
    // const newTodo = {
    //   id: self.crypto.randomUUID(),
    //   ...todo,
    // };
    // const updatedTodos = [...todos, newTodo];
    // setTodos(updatedTodos);
  };

  // const addTodoHandler = (todo) => {
  //   todos.push(todo);
  //   const newTodos = todos; //0x100
  //   setTodos(newTodos);
  // };

  /**
   * 2. 할일 수정 기능
   * @param {*} updateTodo 새롭게 갱신할 할일 객체
   * 문서화 주석
   */

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

  //강사님 방식
  // const filterTodos = () => selectedCategory ==="all"?todos: todos.filter(todo=> todo.category === selectedCategory)
  // const filteredTodos = filteredTodos();

  // 새로운 카테고리값 newCategory 값과 id 를 통해 해당 항목의 category 변경
  const updateCategoryHandler = (id, newCategory) => {
    const updatedTodos = todos.map((todo) =>
      todo.id === id ? { ...todo, category: newCategory } : todo
    );
    setTodos(updatedTodos);
  };

  const updateSubtaskHandler = (todoId, subtaskId, done) => {
    const updatedTodos = todos.map((todo) => {
      if (todo.id === todoId) {
        const updatedSubtasks = todo.subtasks.map((subtask) =>
          subtask.id === subtaskId ? { ...subtask, done } : subtask
        );

        const doneCount = updatedSubtasks.filter((s) => s.done).length;
        let newCategory = "TODO";
        if (doneCount === updatedSubtasks.length) newCategory = "DONE";
        else if (doneCount > 0) newCategory = "PROGRESS";

        return {
          ...todo,
          subtasks: updatedSubtasks,
          category: newCategory,
        };
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
          {/* TodoBody(dummyTodos)형태로 호출 */}
          <TodoBody todos={filteredTodos} />
          {/* {todos}는 위에서 setTodos로 관리하는 todos (dummyTodos + 새로 입력한 todo들)이다 */}
        </section>
      </MyContext.Provider>
    </DefaultLayout>
  );
}

export default App;
