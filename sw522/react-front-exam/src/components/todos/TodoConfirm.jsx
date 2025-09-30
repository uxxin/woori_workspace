import React from "react";
import { useTodosDispatch } from "@/contexts/TodoContext";
//여기 원래 children 있었음 h3 내용으로
const TodoConfirm = ({ todo, onClose }) => {
  const dispatch = useTodosDispatch();

  const todoDeleteHandler = () => {
    dispatch({ type: "DELETE", id: todo.id });
  };
  return (
    <div>
      <h3 className="text-3xl text-red-200">정말 삭제할까요?</h3>
      <div className="flex justify-end gap-4">
        <button
          className="text-xl text-white"
          type="button"
          data-cy="process-remove"
          onClick={todoDeleteHandler}
        >
          삭제
        </button>
        <button
          className="px-6 py-3 text-xl text-red-200"
          type="button"
          data-cy="process-cancel"
          onClick={onClose}
        >
          취소
        </button>
      </div>
    </div>
  );
};

export default TodoConfirm;
