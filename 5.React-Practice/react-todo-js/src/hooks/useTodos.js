import { useState, useEffect } from "react";

// 로컬 스토리지 키 => 저장할 때 사용하는 키 이름 ( 나중에 불러올 때 유용 )
const STORAGE_KEY = "my-todos";

export function useTodos() {
  // 로컬스토리지에서 기존 todos 불러오기
  const loadTodosFromStorage = () => {
    const stored = localStorage.getItem(STORAGE_KEY);
    return stored ? JSON.parse(stored) : [];
  };

  const [todos, setTodos] = useState(loadTodosFromStorage);
  const [filter, setFilter] = useState("ALL");

  // todos가 바뀔 때마다 localStorage에 저장
  //JavaScript 객체/배열이기 때문에 문자열로 변환해야 localStorage에 저장 가능 (localStorage는 문자열만 저장 가능)
  useEffect(() => {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(todos));
  }, [todos]);

  const addTodoHandler = (todo) => {
    todo.id = crypto.randomUUID(); // 유일 ID 사용
    setTodos((prev) => [todo, ...prev]);
  };

  const updateTodoHandler = (updateTodo) => {
    setTodos((prev) =>
      prev.map((todo) => (todo.id === updateTodo.id ? updateTodo : todo))
    );
  };

  const deleteTodoHandler = (id) => {
    setTodos((prev) => prev.filter((todo) => todo.id !== id));
  };

  const updateCategoryHandler = (id, newCategory) => {
    setTodos((prev) =>
      prev.map((todo) =>
        todo.id === id ? { ...todo, category: newCategory } : todo
      )
    );
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

  const filteredTodos = todos.filter((todo) =>
    filter === "ALL" ? true : todo.category === filter
  );

  return {
    todos,
    filteredTodos,
    addTodo: addTodoHandler,
    updateTodo: updateTodoHandler,
    deleteTodo: deleteTodoHandler,
    updateCategory: updateCategoryHandler,
    updateSubtask: updateSubtaskHandler,
    setFilter,
  };
}
