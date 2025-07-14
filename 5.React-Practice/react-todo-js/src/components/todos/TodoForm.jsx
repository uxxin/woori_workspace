import { TODO_CATEGORY_ICON } from "@/constants/icon";
import { useState } from "react";

const TodoForm = ({ actionTitle, buttonText, onAction, onClose, todo }) => {
  // todo가 존재하면 수정, 없으면 등록
  const isEditMode = !!todo?.id;

  // 상태 초기화: todo가 없을 경우 빈 값으로 처리
  const [title, setTitle] = useState(todo?.title ?? "");
  const [summary, setSummary] = useState(todo?.summary ?? "");
  const [category, setCategory] = useState(todo?.category ?? "TODO");

  const todoActionHandler = () => {
    if (isEditMode) {
      // 수정 모드
      const updatedTodo = {
        id: todo.id,
        title,
        summary,
        category,
      };
      onAction(updatedTodo);
    } else {
      // 등록 모드
      const newTodo = {
        title,
        summary,
        category,
      };
      onAction(newTodo);
    }

    onClose();
  };

  return (
    <>
      <h3 className="text-3xl text-red-200">할일 {actionTitle}</h3>
      <form className="my-2">
        <div>
          <label className="block mb-2 text-xl text-white" htmlFor="title">
            Title
          </label>
          <input
            value={title}
            onChange={(event) => setTitle(event.target.value)}
            className="w-full p-2 border-[1px] border-gray-300 bg-gray-200 text-gray-900 rounded"
            type="text"
            id="title"
          />
        </div>
        <div>
          <label className="block mb-2 text-xl text-white" htmlFor="summary">
            Summary
          </label>
          <textarea
            value={summary}
            onChange={(event) => setSummary(event.target.value)}
            className="w-full p-2 border-[1px] border-gray-300 bg-gray-200 text-gray-900 rounded"
            id="summary"
            rows="5"
          />
        </div>
        <div>
          <label className="block mb-2 text-xl text-white" htmlFor="category">
            Category
          </label>
          <select
            value={category}
            onChange={(event) => setCategory(event.target.value)}
            className="w-full p-2 border-[1px] border-gray-300 bg-gray-200 text-gray-900 rounded"
            id="category"
          >
            <option value="TODO">{TODO_CATEGORY_ICON.TODO} To do</option>
            <option value="PROGRESS">
              {TODO_CATEGORY_ICON.PROGRESS} On progress
            </option>
            <option value="DONE">{TODO_CATEGORY_ICON.DONE} Done</option>
          </select>
        </div>

        <div className="flex justify-end gap-4 mt-4">
          <button
            onClick={onClose}
            className="text-xl text-white"
            type="button"
          >
            Cancel
          </button>
          <button
            onClick={todoActionHandler}
            className="px-6 py-3 text-xl text-red-200"
            type="button"
          >
            {buttonText}
          </button>
        </div>
      </form>
    </>
  );
};

export default TodoForm;

// - 할일 등록 처리
//   사용자가 모달에 렌더링된 폼(TodoForm)에 입력한 Title, Summary 값을 받는 처리
//   Add 버튼을 클릭했을 때, 입력받은 값들을 App.jsx로 전달
//   App.jsx에 있는 todos 상태를 업데이트(dummyTodos 배열의 가장 뒤쪽 요소로 추가)
//     - TodoForm에 입력한 값들을 이벤트로 감지하여 JS에서 조작할 수 있게 처리
//     - 입력된 값들을 App.jsx(최상위)로 전달하는 처리
//     - App.jsx에서 전달받은 값들을 가지고 배열의 요소로(하나의 Todo) 추가
//     - 새롭게 추가된 할일 목록 데이터를 가지고 화면을 다시 렌더링하는 처리
