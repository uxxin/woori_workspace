import TodoBody from "./components/todos/TodoBody";
import TodoHeader from "./components/todos/TodoHeader";
import DefaultLayout from "./layouts/DefaultLayout";
import { TodoProvider } from "./contexts/TodoContext";
import NewModal from "./components/ui/NewModal";
import TodoForm from "./components/todos/TodoForm";

function App() {
  return (
    <DefaultLayout>
      <NewModal>
        {/* 모달 창을 열기 위한 컴포넌트 작성 부분 */}
        <NewModal.Open>
          <button className="px-6 py-2 font-semibold text-gray-100 bg-green-500 border-none rounded cursor-pointer">
            New Add Todo
          </button>
        </NewModal.Open>

        {/* 모달 내부에 들어갈 컨텐츠용 컴포넌트 작성 부분 */}
        <NewModal.Dialog>
          <TodoForm actionTitle={"등록"} buttonText={"Add"} />
          {/* <header>
                <h1 className='pt-8 mx-auto text-red-200 max-w-max text-7xl'>
                  <img className='ml-4' src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Thought%20Balloon.png" alt="Thought Balloon" width="75" height="75" />
                  <img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Animals/Seal.png" alt="Seal" width="75" height="75" />
                  </h1>
          </header>
          <section className='max-w-xl m-4 mx-auto'>
            <TodoProvider>
              <TodoHeader />
              <TodoBody />
            </TodoProvider>
          </section> */}
        </NewModal.Dialog>
      </NewModal>
    </DefaultLayout>
  );
}

export default App;
