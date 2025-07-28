import { useTodos } from "../../contexts/TodoContext";
import TodoItem from "./TodoItem";

const TodoBody = () => {
  const todos = useTodos();
  const filterTodos = (todos, selectedCategory) =>
    selectedCategory === "ALL"
      ? todos.filter((todo) => todo.category !== "DONE")
      : selectedCategory === "DONE"
      ? []
      : todos.filter((todo) => todo.category === selectedCategory);
  const filteredTodos = filterTodos(todos.data, todos.category);
  const doneTodos = todos.data.filter((todo) => todo.category === "DONE");

  return (
    <div>
      <ul>
        {filteredTodos.map((todo) => (
          <TodoItem todo={todo} key={todo.id} />
        ))}
      </ul>
      <br />
      <h2 className="inline-block pt-2 pb-2 pl-4 pr-4 bg-white text-black rounded-lg ">
        Done 👍
      </h2>
      <ul>
        {doneTodos.map((todo) => (
          <TodoItem todo={todo} key={todo.id} />
        ))}
      </ul>
    </div>
  );
};

export default TodoBody;
