import { TODO_CATEGORY_ICON } from "@/constants/icon";
import { useContext, useState } from "react";
import MyContext from "../../context/TodoContext";

const TodoForm = ({ actionTitle, buttonText, onClose, todo }) => {
  const isEditMode = !!todo?.id;

  const [title, setTitle] = useState(todo?.title ?? "");
  // const [summary, setSummary] = useState(todo?.summary ?? "");
  //const [category, setCategory] = useState(todo?.category ?? "TODO");

  const [subtasks, setSubtasks] = useState(todo?.subtasks ?? []);
  const [newSubtask, setNewSubtask] = useState("");

  const { addTodo, updateTodo } = useContext(MyContext);

  const addSubtask = () => {
    if (newSubtask.trim() !== "") {
      const newItem = {
        id: self.crypto.randomUUID(),
        title: newSubtask,
        done: false,
      };
      setSubtasks([...subtasks, newItem]);
      setNewSubtask("");
    }
  };

  const removeSubtask = (id) => {
    setSubtasks(subtasks.filter((task) => task.id !== id));
  };

  const todoActionHandler = () => {
    const baseTodo = {
      title,
      category: "TODO",
      subtasks,
    };

    if (isEditMode) {
      updateTodo({ ...baseTodo, id: todo.id });
    } else {
      addTodo(baseTodo);
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
            onChange={(e) => setTitle(e.target.value)}
            className="w-full p-2 border border-gray-300 bg-gray-200 text-gray-900 rounded"
            type="text"
            id="title"
          />
        </div>

        {/* Subtasks 입력 영역 */}
        <div className="mt-4">
          <label className="block mb-2 text-xl text-white" htmlFor="subtasks">
            Subtasks
          </label>
          <div className="flex gap-2 mb-2">
            <input
              value={newSubtask}
              onChange={(e) => setNewSubtask(e.target.value)}
              className="w-full p-2 border border-gray-300 bg-gray-200 text-gray-900 rounded"
              type="text"
              placeholder="Enter subtask"
            />
            <button
              type="button"
              onClick={addSubtask}
              className="px-4 text-white bg-blue-500 rounded"
            >
              Add
            </button>
          </div>
          <ul className="pl-5 list-disc space-y-1">
            {subtasks.map((task) => (
              <li key={task.id} className="text-sm text-gray-100 list-item">
                <div className="flex justify-between items-center">
                  <span>{task.title}</span>
                  <button
                    type="button"
                    className="ml-2 px-2 py-1 text-sm font-medium text-red-600 bg-white  rounded hover:bg-red-100"
                    onClick={() => removeSubtask(task.id)}
                  >
                    삭제
                  </button>
                </div>
              </li>
            ))}
          </ul>
        </div>

        {/* 카테고리 자동화 할건지에 따라 비활성화 시키기 */}
        {/* <div className="mt-4">
          <label className="block mb-2 text-xl text-white" htmlFor="category">
            Category
          </label>
          <select
            value={category}
            onChange={(e) => setCategory(e.target.value)}
            className="w-full p-2 border border-gray-300 bg-gray-200 text-gray-900 rounded"
            id="category"
            disabled
          >
            <option value="TODO">{TODO_CATEGORY_ICON.TODO} To do</option>
            <option value="PROGRESS">
              {TODO_CATEGORY_ICON.PROGRESS} On progress
            </option>
            <option value="DONE">{TODO_CATEGORY_ICON.DONE} Done</option>
          </select>
        </div> */}

        <div className="flex justify-end gap-4 mt-6">
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
