import { useTodos } from "../../contexts/TodoContext";
import TodoItem from "./TodoItem";

const TodoBody = () => {
  const todos = useTodos();
  const filterTodos = (todos, selectedCategory) =>
    selectedCategory === "ALL"
      ? todos
      : todos.filter((todo) => todo.category === selectedCategory);
  const filteredTodos = filterTodos(todos.data, todos.category);

  const sortedTodos = filteredTodos.sort((a, b) => {
    if (a.category === "DONE" && b.category !== "DONE") return 1;
    if (a.category !== "DONE" && b.category === "DONE") return -1;
    return 0;
  });
f
  // const sortedTwice = filterTodos.sort((a,b)=> new Date(a.startdate)-new Date(b.startdate)
  // if(startDiff!==0)return 0;
  // return ) //////////////

  // // 날짜 순으로 정렬
  // const sortedDateTodos = filteredTodos.sort(
  //   (a, b) => new Date(a.date) - new Date(b.date)
  // );

  // //DONE 아래로 정렬
  // const sortedTodos = filteredTodos.sort((a, b) => {
  //   if (a.category === "DONE" && b.category !== "DONE") return 1;
  //   if (a.category !== "DONE" && b.category === "DONE") return -1;
  //   return 0;
  // });

  // // 다 한 일 아래 따로 정렬
  // const excDoneTodos = (todos, selectedCategory) =>
  //   selectedCategory === "ALL"
  //     ? todos.filter((todo) => todo.category !== "DONE")
  //     : selectedCategory === "DONE"
  //     ? []
  //     : todos.filter((todo) => todo.category === selectedCategory);

  // const exceptDoneTodos = excDoneTodos(todos.data, todos.category);

  // const doneTodos = filteredTodos.filter((todo) => todo.category === "DONE");

  // //우선 순위 설정해서 정렬하기 -> 안됨 !!!
  // const priority = { TODO: 1, IN_PROGRESS: 2, DONE: 3 };
  // const sortedPriorityTodos = filteredTodos.sort(
  //   (a, b) => priority[a.category] - priority[b.category]
  // );

  return (
    <ul>
      {sortedDate.map((todo) => (
        <TodoItem todo={todo} key={todo.id} />
      ))}
    </ul>
    // <ul>
    //   {filteredTodos.map((todo) => (
    //     <TodoItem todo={todo} key={todo.id} />
    //   ))}
    // </ul>
    //   // Done 아래로 정렬
    // <div>
    //   <ul>
    //     {exceptDoneTodos.map((todo) => (
    //       <TodoItem todo={todo} key={todo.id} />
    //     ))}
    //   </ul>
    //   <h3>Done</h3>
    //   <ul>
    //     {doneTodos.map((todo) => (
    //       <TodoItem todo={todo} key={todo.id} />
    //     ))}
    //   </ul>
    // </div>
  );
};

export default TodoBody;
