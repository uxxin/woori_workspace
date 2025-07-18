import React from "react";
import { TODO_CATEGORY_ICON } from "@/constants/icon";
import IconButton from "@/components/ui/IconButton";
import TodoForm from "./TodoForm";
import { useTodosDispatch } from "@/contexts/TodoContext";
import NewModal from "@/components/ui/NewModal";

const TodoItem = ({ todo }) => {
  const dispatch = useTodosDispatch();

  return (
    <li
      data-cy="todo-item"
      className="flex gap-4 justify-between my-4 py-4 px-4 border-[1px] bg-gray-700 rounded-md shadow-xl"
    >
      <div>
        <span className="text-lg font-medium text-gray-300">
          {TODO_CATEGORY_ICON[todo.category]}
        </span>
        <div>
          <h2
            data-test="title"
            className="mb-0 text-lg font-bold text-gray-100 uppercase"
          >
            {todo.title}
          </h2>
          <p className="mt-2 text-base text-gray-200">{todo.summary}</p>
        </div>
      </div>
      <div className="flex items-center gap-1">
        <NewModal>
          <NewModal.Open>
            <IconButton onClick={() => open(true)} icon={"✏️"} />
          </NewModal.Open>
          <NewModal.Dialog>
            <TodoForm actionTitle={"수정"} todo={todo} />
          </NewModal.Dialog>
        </NewModal>
        <IconButton
          data-cy="recycle-bin"
          onClick={() => dispatch({ type: "DELETE", id: todo.id })}
          icon={"🗑"}
        />
      </div>
    </li>
  );
};
export default TodoItem;
