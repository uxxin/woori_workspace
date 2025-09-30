import { TODO_CATEGORY_ICON } from "@/constants/icon";
import { useState } from "react";
import { useTodosDispatch } from "@/contexts/TodoContext";
const TodoForm = ({ actionTitle, onClose, todo }) => {
  const isNewTodoForm = actionTitle.startsWith("등록") ? true : false;

  const [title, setTitle] = useState(isNewTodoForm ? "" : todo.title);
  const [summary, setSummary] = useState(isNewTodoForm ? "" : todo.summary);
  const [category, setCategory] = useState(
    isNewTodoForm ? "TODO" : todo.category
  );

  // 문제2. 14번 라인에는 새롭게 추가할 날짜 타입을 React의 상태값으로 입력받고 저장할 상태 변수인 deadline이 정의되어 있음
  // -> 정의된 상태(deadline, setDeadline)를 활용하여 문제 2번을 구현하세요
  const [deadline, setDeadline] = useState(isNewTodoForm ? "" : todo.deadline);

  const dispatch = useTodosDispatch();
  //let todayDate = new Date(2025, 7, 29);

  const todoActionHandler = () => {
    if (!isNewTodoForm) {
      // 할일 수정일 경우,
      dispatch({
        type: "UPDATE",
        updateTodo: { id: todo.id, title, summary, category, deadline },
      });
    } else {
      // 할일 추가일 경우,
      dispatch({
        type: "ADD",
        newTodo: {
          id: self.crypto.randomUUID(),
          title,
          summary,
          category,
          deadline,
        },
      });
    }

    onClose();
  };

  return (
    <>
      <h3 data-cy="todo-form-title" className="text-3xl text-red-200">
        할일 {actionTitle}
      </h3>
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

        {/* 문제2. 하단 라인에는 마감기한(Deadline) 엘리먼트 기본 템플릿이 제공되어 있음 */}
        <div>
          <label className="block my-2 text-xl text-white" htmlFor="deadline">
            마감기한(Deadline)
          </label>
          <input
            className="w-full p-2 border-[1px] border-gray-300 bg-gray-200 text-gray-900 rounded"
            data-cy="deadline-input"
            // 문제2. tip: 현재 input tag에는 별도의 'type' attribute가 작성되어 있지 않은데, type attribute로 무엇을 작성해야 날짜 값을 입력받을 수 있을지?
            type="date"
            // min을 동적으로 불러오기-TODO2
            min="2025-07-29"
            name=""
            id="deadline"
            value={deadline}
            onChange={(event) => setDeadline(event.target.value)}
          />
        </div>
        {/* 문제2. ------ */}

        <div className="flex justify-end gap-4">
          <button
            onClick={onClose}
            className="text-xl text-white"
            type="button"
          >
            취소
          </button>
          <button
            data-cy="process-add-or-update"
            onClick={todoActionHandler}
            className="px-6 py-3 text-xl text-red-200"
            type="button"
          >
            {actionTitle}
          </button>
        </div>
      </form>
    </>
  );
};
export default TodoForm;
