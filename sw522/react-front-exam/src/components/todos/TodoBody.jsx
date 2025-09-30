import { useTodos } from "../../contexts/TodoContext";
import TodoItem from "./TodoItem";

const TodoBody = () => {
  const todos = useTodos();
  const filterTodos = (todos, selectedCategory) =>
    selectedCategory === "ALL"
      ? todos
      : todos.filter((todo) => todo.category === selectedCategory);
  const filteredTodos = filterTodos(todos.data, todos.category);

  // 5. DONE 아래로 정렬
  const sortedTodos = filteredTodos.sort((a, b) => {
    if (a.category === "DONE" && b.category !== "DONE") return 1;
    if (a.category !== "DONE" && b.category === "DONE") return -1;
    return 0;
  });

  return (
    <ul>
      {sortedTodos.map((todo) => (
        <TodoItem todo={todo} key={todo.id} />
      ))}
    </ul>
  );
};

export default TodoBody;
