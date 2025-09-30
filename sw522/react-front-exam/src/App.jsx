import DefaultLayout from "./layouts/DefaultLayout";
import NewModal from "@/components/ui/NewModal";
import TodoBody from "./components/todos/TodoBody";
import TodoHeader from "./components/todos/TodoHeader";
import TodoList from "./components/todos/TodoList";
import TodoForm from "./components/todos/TodoForm";
import TodoFilter from "./components/todos/TodoFilter";
import Tooltip from "./components/ui/ToolTip";

import { TodoProvider } from "./contexts/TodoContext";
import Logo from "./components/Logo";

function App() {
  return (
    <DefaultLayout>
      <Logo />
      <TodoProvider>
        <TodoHeader>
          <div>
            <NewModal>
              <NewModal.Open>
                <div className="group/item">
                  <Tooltip />
                  {/* data-cy = cypress 테스트용 식별자 */}
                  <button
                    data-cy="add-todo-button"
                    className="group/tooltip px-6 py-2 font-semibold text-gray-100 bg-green-500 border-none rounded cursor-pointer"
                  >
                    Add Todo
                  </button>
                </div>
              </NewModal.Open>
              <NewModal.Dialog>
                <TodoForm actionTitle={"등록"} />
              </NewModal.Dialog>
            </NewModal>
          </div>
          <TodoFilter />
        </TodoHeader>

        <TodoBody>
          <TodoList />
        </TodoBody>
      </TodoProvider>
    </DefaultLayout>
  );
}

export default App;
