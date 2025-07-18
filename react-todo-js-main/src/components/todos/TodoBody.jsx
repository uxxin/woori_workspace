import { useTodos } from '../../contexts/TodoContext'
import TodoItem from './TodoItem'

const TodoBody = () => {
  
  const todos = useTodos();
  const filterTodos = (todos, selectedCategory) => selectedCategory === 'ALL' ? todos : todos.filter(todo => todo.category === selectedCategory);
  const filteredTodos = filterTodos(todos.data, todos.category);

  return (
    <ul>
        {filteredTodos.map(todo => 
          <TodoItem 
            todo={todo} 
            key={todo.id}/>)}
    </ul>
  )
}

export default TodoBody