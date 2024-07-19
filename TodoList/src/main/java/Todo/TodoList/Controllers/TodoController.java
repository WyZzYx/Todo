package Todo.TodoList.Controllers;

import Todo.TodoList.Controllers.Repository.TodoRepository;
import Todo.TodoList.Model.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {


    TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        todoRepository.init();
        this.todoRepository = todoRepository;
    }

    @GetMapping(" ")
    public String Todo(Model model){
        model.addAttribute("todo", todoRepository.findAllTodo());
//        model.addAttribute("todos", new Todo(0, " 11", false));
        System.out.println(todoRepository.findAllTodo());

        return "index";
    }

    @GetMapping("/create")
    public String TodoCreate(Model model){

        model.addAttribute("todo", new Todo(0, " ", false));

        model.addAttribute("todos", todoRepository.findAllTodo());

        return "create";
    }

    @PostMapping("/save")
    public String TodoSave(Model model,
                           @RequestParam String name,
                           @RequestParam(required = false) boolean isDone){

        model.addAttribute("todo", new Todo(0, name, isDone));

        long id = todoRepository.findAllTodo().stream().mapToInt(todo -> (int) todo.id()).max().orElse(0) + 1;
        Todo todo = new Todo(id, name, isDone);
        todoRepository.addTodo(todo);

        return "redirect:";
    }

    @GetMapping("/delete/{id}")
    public String todoDelete(@PathVariable long id){

        System.out.println("deleted");
        todoRepository.deleteTodo(id);

        return "redirect:/";
    }
}
