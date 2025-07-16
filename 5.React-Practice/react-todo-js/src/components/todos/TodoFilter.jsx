import React, { useContext } from "react";
import { TODO_CATEGORY_ICON } from "@/constants/icon";
//@ : 어떤 경로에서든 편하게 불러오게 vite.config.js에서 정의해줘야함.
import MyContext from "../../context/TodoContext";

const TodoFilter = () => {
  const { setFilter } = useContext(MyContext);
  return (
    <select
      className="p-2 text-gray-100 bg-gray-800 rounded"
      data-cy="todo-filter"
      onChange={(e) => setFilter(e.target.value)}
    >
      {/* defaultValue? - https://react.dev/reference/react-dom/components/select */}
      <option value="ALL" defaultValue={"1"}>
        All
      </option>
      <option value="TODO">{TODO_CATEGORY_ICON.TODO} To do</option>
      <option value="PROGRESS">
        {TODO_CATEGORY_ICON.PROGRESS} On progress
      </option>
      <option value="DONE">{TODO_CATEGORY_ICON.DONE} Done</option>
    </select>
  );
};
export default TodoFilter;
