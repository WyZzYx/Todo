package Todo.TodoList.Controllers.Repository;

import Todo.TodoList.Model.Todo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepository {

    private List<Todo> todoList = new ArrayList<>();

    public void addTodo(Todo todo){
        todoList.add(todo);
    }

    public void deleteTodo(long id){
        todoList.removeIf(todo -> todo.id() == id);
    }

    public void updateTodo(long id, Todo todo){

        Optional<Todo> existingTodo = Optional.ofNullable(findById(id));
        if(existingTodo.isPresent()){
            todoList.set(todoList.indexOf(existingTodo.get()), todo);
        }

    }

    public Todo findById(long id){
        return todoList.stream()
                .filter(todo -> todo.id() == id)
                .findFirst().orElse(null);
    }

    public List<Todo> findAllTodo(){
        return todoList;
    }

    public void init(){
        todoList.add(new Todo(1, "Create something", false));

        todoList.add(new Todo(2, "Create something2", false));

        todoList.add(new Todo(3, "Create something3", true));
    }


}
