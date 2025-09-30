import { TODO_CATEGORY_ICON } from "@/constants/icon";
import { useContext, useState } from "react";
import MyContext from "../../context/TodoContext";

const TodoForm = ({ actionTitle, buttonText, onClose, todo }) => {
  // todo가 존재하면 수정, 없으면 등록
  const isEditMode = !!todo?.id;

  // 상태 초기화: todo가 없을 경우 빈 값으로 처리
  const [title, setTitle] = useState(todo?.title ?? "");
  const [summary, setSummary] = useState(todo?.summary ?? "");
  const [category, setCategory] = useState(todo?.category ?? "TODO");
  const { addTodo, updateTodo } = useContext(MyContext);
  const [newSubtask, setNewSubtask] = useState("");
  const [subtasks, setSubtasks] = useState(todo?.subtasks ?? []);

  const addSubTask = () => {
    if (newSubtask.trim() !== "") {
      const newItem = {
        id: self.crypto.randomUUID,
        title: newSubtask,
        done: false,
      };
      setSubtasks([...subtasks, newItem]);
      setNewSubtask("");
    }
  };

  const removeSubTask = (id) => {
    setSubtasks(subtasks.filter((task) => task.id !== id));
  };

  const todoActionHandler = () => {
    if (isEditMode) {
      // 수정 모드
      const updatedTodo = {
        id: todo.id,
        title,
        summary,
        category,
      };
      updateTodo(updatedTodo);
    } else {
      // 등록 모드
      const newTodo = {
        title,
        summary,
        category,
      };
      addTodo(newTodo);
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
        <div className="mt-4">
          {/* 여기에 subtasks입력 받는 칸  */}
          {/* Add 클릭하면 밑에 뜨고 추가 되는 로직 */}
          {/* 삭제 로직 구현 */}
          {/* TODO: Item에 추가, 삭제 로직 */}
          <label className="block mb-2 text-xl text-white" htmlFor="subtasks">
            Subtasks
          </label>
          <div className="flex gap-2 mb-2">
            <input
              value={newSubtask}
              type="text"
              onChange={(e) => setNewSubtask(e.target.value)}
              className="w-full p-2 border border-gray-300 bg-gray-200 text-gray-900 rounded"
              placeholder="enter subtask"
            />
            <button
              type="button"
              onClick={addSubTask}
              className=" p-2 bg-white rounded"
            >
              Add
            </button>
          </div>
          <ul className="pl-5 list-disc space-y-1">
            {subtasks.map((subtask) => (
              // 여기에 이제 list 형태로 출력할 내용과 삭제 버튼 넣기
              <li key={subtask.id} className="text-sm text-gray-100 list-item">
                <div className="flex justify-between items-center">
                  <span className="text-white">{subtask.title}</span>
                  <button
                    type="button"
                    className="ml-2 px-2 py-1 text-sm font-medium text-red-600 bg-white  rounded hover:bg-red-100"
                    onClick={() => removeSubTask(subtask.id)}
                  >
                    삭제
                  </button>
                </div>
              </li>
            ))}
          </ul>
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
