import React from "react";
import { TODO_CATEGORY_ICON } from "@/constants/icon";
import IconButton from "../ui/IconButton";
import { createPortal } from "react-dom";
import Modal from "../ui/Modal";
import TodoForm from "./TodoForm";
import { useState } from "react";

// TodoBody에서 todo라는 이름의 props를 전달(내려줬음)
const TodoItem = ({ todo, onUpdate, onChange, onDelete }) => {
  const [openModal, open] = useState(false);
  const openModalHandler = () => open(true);
  //console.log("TodoItem,", todo);

  return (
    <li className="flex gap-4 justify-between my-4 py-4 px-4 border-[1px] bg-gray-700 rounded-md shadow-xl">
      <div>
        {/* <span className="text-lg font-medium text-gray-300">
          {TODO_CATEGORY_ICON[todo.category]}
        </span> */}

        {/* 드롭다운은 TodoFilter.jsx 그대로 + onChange로 바뀐 값 넘기게  */}
        <select
          value={todo.category}
          className="p-2 text-gray-100 bg-gray-800 rounded"
          onChange={(e) => onChange(todo.id, e.target.value)}
        >
          <option value="TODO">{TODO_CATEGORY_ICON.TODO} To do</option>
          <option value="PROGRESS">
            {TODO_CATEGORY_ICON.PROGRESS} On progress
          </option>
          <option value="DONE">{TODO_CATEGORY_ICON.DONE} Done</option>
        </select>

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
        {/* IconButton은 우리가 만든 component임. 실제 우리가 만든 onClick임/ */}
        <IconButton onClick={openModalHandler} icon={"✏️"} />
        <IconButton onClick={() => onDelete(todo.id)} icon={"🗑"} />
        {/* Modal이 생성되는 위치 */}
        {openModal &&
          createPortal(
            <Modal onClose={() => open(false)}>
              <TodoForm
                actionTitle={"수정"}
                buttonText={"Update"}
                onAction={onUpdate}
                onClose={() => open(false)}
                todo={todo}
              />
            </Modal>,
            document.body
          )}
      </div>
    </li>
  );
};
export default TodoItem;
