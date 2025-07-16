import { useState } from "react";
import dummyTodos from "../data/dummyTodos";

export function useTodos() {
  const [todos, setTodos] = useState(dummyTodos);
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

  const updateSubtaskHandler = (todoId, subtaskId, done) => {
    const updatedTodos = todos.map((todo) => {
      if (todo.id === todoId) {
        const updatedSubtasks = todo.subtasks.map((subtask) =>
          subtask.id === subtaskId ? { ...subtask, done } : subtask
        );

        //  done이 true인 subtask의 개수 새기
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
