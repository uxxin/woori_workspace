import { createContext, useContext, useReducer } from "react";

const dummyTodos = [
  {
    id: 1,
    title: 'React 공부',
    summary: 'React를 공부한다.',
    category: 'TODO',
  },
  {
    id: 2,
    title: '점심 먹기',
    summary: '점심을 먹는다.',
    category: 'PROGRESS',
  },
  {
    id: 3,
    title: '커피 마시기',
    summary: '커피를 마신다.',
    category: 'DONE',
  }
];

export const TodoContext = createContext();
export const TodoDispatchContext = createContext();

export const TodoProvider = ({ children }) => {

    const [todos, dispatch] 
        = useReducer(reducer, { data: dummyTodos, category: 'ALL' });

    return (
      <TodoContext.Provider value={todos}>
        <TodoDispatchContext.Provider value={dispatch}>
            { children }
        </TodoDispatchContext.Provider>
      </TodoContext.Provider>
    )
}

export const useTodos = () => useContext(TodoContext);
export const useTodosDispatch = () => useContext(TodoDispatchContext);

const reducer = (todos, action) => {
    const { data, category } = todos;

    switch (action.type) {
      case 'ADD': 
        const { newTodo } = action;
        return { data: [...data, newTodo], category };
  
      case 'UPDATE':
        const { updateTodo } = action;
        const updatedTodos = data.map(todo => todo.id === updateTodo.id ? { ...updateTodo } : todo);
        return { data: updatedTodos, category }
  
      case 'DELETE': 
        const { id } = action;
        const deletedTodos = data.filter(todo => todo.id !== id);
        return { data: deletedTodos, category } 

      case 'FILTER':  
        return { data, category: action.selectedCategory }
    }
  }