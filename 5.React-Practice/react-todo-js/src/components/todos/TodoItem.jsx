import React, { useContext } from "react";
import { TODO_CATEGORY_ICON } from "@/constants/icon";
import IconButton from "../ui/IconButton";
import { createPortal } from "react-dom";
import Modal from "../ui/Modal";
import TodoForm from "./TodoForm";
import { useState } from "react";
import MyContext from "../../context/TodoContext";

// TodoBody에서 todo라는 이름의 props를 전달(내려줬음)
const TodoItem = ({ todo }) => {
  const [openModal, open] = useState(false);
  const openModalHandler = () => open(true);
  const { deleteTodo, updateSubtask } = useContext(MyContext);

  return (
    <li className="flex flex-col gap-2 my-4 py-4 px-4 border-[1px] bg-gray-700 rounded-md shadow-xl">
      <div className="flex justify-between items-start">
        <div>
          {/* 카테고리 아이콘 + 텍스트 */}
          <span className="text-sm text-gray-300">
            {TODO_CATEGORY_ICON[todo.category]} {todo.category}
          </span>
          <h2 className="mt-1 text-lg font-bold text-gray-100 uppercase">
            {todo.title}
          </h2>
          <p className="text-base text-gray-200">{todo.summary}</p>
        </div>
        <div className="flex items-center gap-1">
          <IconButton onClick={openModalHandler} icon={"✏️"} />
          <IconButton onClick={() => deleteTodo(todo.id)} icon={"🗑"} />
        </div>
      </div>

      {/* 서브태스크 목록 */}
      {todo.subtasks?.length > 0 && (
        <ul className="pl-4 mt-2 space-y-1">
          {todo.subtasks.map((subtask) => (
            <li key={subtask.id} className="flex items-center gap-2">
              <input
                type="checkbox"
                checked={subtask.done}
                onChange={(e) =>
                  updateSubtask(todo.id, subtask.id, e.target.checked)
                }
              />
              <span
                className={`text-sm ${
                  subtask.done ? "line-through text-gray-400" : "text-gray-100"
                }`}
              >
                {subtask.title}
              </span>
            </li>
          ))}
        </ul>
      )}

      {openModal &&
        createPortal(
          <Modal onClose={() => open(false)}>
            <TodoForm
              actionTitle={"수정"}
              buttonText={"Update"}
              onClose={() => open(false)}
              todo={todo}
            />
          </Modal>,
          document.body
        )}
    </li>
  );
};

export default TodoItem;
