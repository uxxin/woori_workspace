import React, { useState } from "react";
import TodoFilter from "./TodoFilter";
import Modal from "../ui/Modal";
import { createPortal } from "react-dom";
import TodoForm from "./TodoForm";

const TodoHeader = () => {
  // Modal의 열기/ 닫기 여부를 관리하는 상태값
  const [openModal, open] = useState(false);
  const openModalHandler = () => open(true);
  // 닫을 때는 Modal 에서 닫아야 하니까 open을 props로 Modal에 내려줘야 함.

  return (
    <div className="flex items-center justify-between mb-2" id="task-control">
      <button
        onClick={openModalHandler}
        className="px-6 py-2 font-semibold text-gray-100 bg-gray-800 border-none rounded cursor-pointer"
        data-cy="add-todo-button"
      >
        Add Todo
      </button>

      {/* Modal이 생성되는 위치 */}
      {openModal &&
        createPortal(
          <Modal onClose={() => open(false)}>
            <TodoForm
              actionTitle={"등록"}
              buttonText={"Add"}
              onClose={() => open(false)}
            />
          </Modal>,
          document.body
        )}
      {/* Modal 컴포넌트에게 onClick이라는 이름의 props로 open 함수를 전달 */}
      {/* <Modal>여기에 넣고 싶은 컴포넌트를 넣어서 모달을 띄움</Modal> */}
      <TodoFilter />
    </div>
  );
};

export default TodoHeader;
