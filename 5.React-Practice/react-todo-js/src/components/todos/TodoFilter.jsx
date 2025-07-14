import React from "react";
import { TODO_CATEGORY_ICON } from "@/constants/icon";
//@ : 어떤 경로에서든 편하게 불러오게 vite.config.js에서 정의해줘야함.
const TodoFilter = () => {
  return (
    <select
      className="p-2 text-gray-100 bg-gray-800 rounded"
      data-cy="todo-filter"
    >
      {/* defaultValue? - https://react.dev/reference/react-dom/components/select */}
      <option value="all" defaultValue={"1"}>
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
