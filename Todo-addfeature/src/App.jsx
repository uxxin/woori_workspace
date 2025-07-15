
import TodoBody from "./components/todos/TodoBody"
import TodoHeader from "./components/todos/TodoHeader"
import DefaultLayout from "./layouts/DefaultLayout"
import { TodoProvider } from "./contexts/TodoContext"

function App() {
                                    
  return (
        <DefaultLayout>
          <header>
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
          </section>
        </DefaultLayout>
  )
}

export default App
