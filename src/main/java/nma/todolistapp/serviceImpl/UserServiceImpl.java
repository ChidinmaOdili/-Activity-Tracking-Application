package nma.todolistapp.serviceImpl;

import nma.todolistapp.dto.TaskDTO;
import nma.todolistapp.dto.UserDTO;
import nma.todolistapp.exception.TaskNotFoundException;
import nma.todolistapp.exception.UserNotFoundException;
import nma.todolistapp.model.Task;
import nma.todolistapp.model.User;
import nma.todolistapp.repository.TaskRepo;
import nma.todolistapp.repository.UserRepo;
import nma.todolistapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
//    @Override
//    public User save(UserDTO userRegistration) {
//        return null;
//    }
//
//    @Override
//    public boolean login(String email, String password) {
//        return false;
//    }
//
//    Task createTask(TaskDTO taskDTO);
//
//    Task updateTitleAndDescription(TaskDTO taskDTO , int id);
//
//    Task getTaskById(int id);
//
//    List<Task> viewAllTasks();
//
//    boolean updateTaskStatus(String status, int id);
//
//    List<Task> viewAllTaskByStatus(String status);
//
//    boolean deleteById(int id);
//    User getUserByEmail(String email);
//
//
//}


    private final UserRepo userRepository;

    private  final TaskRepo taskRepository;

    @Autowired
    public UserServiceImpl(UserRepo userRepository, TaskRepo taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }


    @Override
    public User registerUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return  userRepository.save(user);
    }

    @Override
    public String loginUser(String email, String password) {
        String message = "";
        User user = getUserByEmail(email);
        if (user.getPassword().equals(password)){
            message = "Success";
        }else {
            message = "incorrect password";
        }
        return message;
    }


    @Override
    public Task createTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        return taskRepository.save(task);
    }

    @Override
    public Task updateTitleAndDescription(TaskDTO taskDTO , int id) {
        Task task = getTaskById(id);
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        return taskRepository.save(task);
    }

    @Override
    public List<Task> viewAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> viewAllTaskByStatus(String status) {
        return taskRepository.listOfTasksByStatus(status);
    }

    @Override
    public boolean deleteById(int id) {
        taskRepository.deleteById(id);
        return  true;
    }

    @Override
    public boolean updateTaskStatus(String status, int id){
        return taskRepository.updateTaskByIdAndStatus(status , id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email + "not found in the database"));
    }

    @Override
    public Task getTaskById(int id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException( "Task not found in the database"));
    }
}