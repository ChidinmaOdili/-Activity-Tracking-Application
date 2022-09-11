package nma.todolistapp.controller;

import nma.todolistapp.dto.TaskDTO;
import nma.todolistapp.dto.UserDTO;
import nma.todolistapp.enums.Status;
import nma.todolistapp.model.Task;
import nma.todolistapp.model.User;
import nma.todolistapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping()//value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
//        List<Task> allTasks = userService.viewAllTasks();
//        model.addAttribute("tasks", allTasks);
        return "index";
    }

    @GetMapping(value = "/login")
    public String displayLoginPage(Model model) {
        model.addAttribute("userDetails", new UserDTO());
        return "login";
    }

    @GetMapping(value = "/taskPage")
    public String displayTaskPage(Model model, Model model2, HttpSession session) {
        Long id = (Long) session.getAttribute("id");
        List<Task> allTasksOfThisUser = userService.allTaskByUserId((Long)session.getAttribute("id"));
        model.addAttribute("taskDetails", new TaskDTO());
        model2.addAttribute("tasks", allTasksOfThisUser);
        return "task";
    }

    @PostMapping(value = "/loginUser")
    public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        String message = userService.loginUser(email, password);

        if (message.equals("Success")) {
            User user = userService.getUserByEmail(email);
            session.setAttribute("email", user.getEmail());
            session.setAttribute("id", user.getId());
            session.setAttribute("name", user.getName());
            return "redirect:/taskPage";
        } else {
            model.addAttribute("errorMessage", message);
            return "redirect:/login";
        }


    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRegistrationDetails", new UserDTO());
        return "registration";
    }

    @PostMapping(value = "/userRegistration")
    public String registration(@ModelAttribute UserDTO userDTO) {

        User registeredUser = userService.registerUser(userDTO);
        if (registeredUser != null) {

            return "redirect:/login";
        } else {
            return "redirect:/register";
        }
    }

    @GetMapping(value = "/task/{status}")
    public String taskByStatus(@PathVariable(name = "status") String status, Model model,HttpSession session) {
        List<Task> listOfTaskByStatus = userService.findAllByUser_idAndStatus(status,(Long)session.getAttribute("id"));
        model.addAttribute("tasksByStatus", listOfTaskByStatus);
        return "task_by_status";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable(name = "id") Long id) {
        userService.deleteById(id);
        return "redirect:/taskPage";
    }

    @GetMapping(value = "/edit/{id}")
    public String showEditPage(@PathVariable(name = "id") Long id, Model model) {
        Task task = userService.getTaskById(id);
        model.addAttribute("singleTask", task);
        model.addAttribute("taskBody", new TaskDTO());
        return "editTask";
    }

    @PostMapping(value = "/edit/{id}")
    public String editTask(@PathVariable(name = "id") Long id, @ModelAttribute TaskDTO taskDTO) {
        userService.updateTitleAndDescription(taskDTO, id);
        return "redirect:/taskPage";
    }


    @GetMapping(value = "/addNewTask")
    public String addTask(Model model) {
        model.addAttribute("newTask", new TaskDTO());
        return "addTask";
    }


    @PostMapping(value = "/addTask")
    public String CreateTask(@ModelAttribute TaskDTO taskDTO, HttpSession session) {
        //session.setAttribute("user_id",taskDTO);
       // Long id = (Long) session.getAttribute("id");
        userService.createTask(taskDTO);
        return "redirect:/taskPage";
    }

    @GetMapping(value = "/start/{id}")
    public String startTask(@PathVariable(name = "id")Long id, HttpSession session) {
        session.setAttribute("start", Status.IN_PROGRESS);
        userService.updateStatus(id);
        return "redirect:/taskPage";
    }

    @GetMapping(value = "/end/{id}")
    public String endTask(@PathVariable(name = "id") Long id) {
        userService.markTaskCompleted( id);
        return "redirect:/taskPage";
    }


    @GetMapping("/log-out")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}





























