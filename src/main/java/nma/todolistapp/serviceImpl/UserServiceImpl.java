package nma.todolistapp.serviceImpl;

import nma.todolistapp.dto.TaskDTO;
import nma.todolistapp.dto.UserDTO;
import nma.todolistapp.enums.Status;
import nma.todolistapp.exception.TaskNotFoundException;
import nma.todolistapp.exception.UserNotFoundException;
import nma.todolistapp.model.Task;
import nma.todolistapp.model.User;
import nma.todolistapp.repository.TaskRepo;
import nma.todolistapp.repository.UserRepo;
import nma.todolistapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


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
        task.setStatus(Status.PENDING.name());
        User user = getUserById(taskDTO.getUser_id());
        task.setUser(user);
        return taskRepository.save(task);
    }

    @Override
    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("user not found"));
    }

    @Override
    public Task updateTitleAndDescription(TaskDTO taskDTO ,Long id) {
        Task task = getTaskById(id);
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(Status.PENDING.name());
        if (task.equals(allTaskByUserId(id))) {

        }
            return taskRepository.save(task);

    }

    @Override
    public List<Task> viewAllTasks() {
        return taskRepository.findAll();
    }


    @Override
    public List<Task> allTaskByUserId(Long id){
        return  taskRepository.findAllByUser_id(id);
    }


    @Override
    public List<Task> viewAllTaskByStatus(String status, Long user_id) {
        return taskRepository.listOfTasksByStatus(status, user_id);
    }

    @Override
    public boolean deleteById(Long id) {
        taskRepository.deleteById(id);
        return  true;
    }

//    @Override
//    public int updateTaskStatus(String status, int id){
//        return taskRepository.updateTaskByIdAndStatus(status , id);
//    }


    @Override
    public Task markTaskCompleted(Long id){
        Task task = getTaskById(id);
        if (task.getStatus().equals(Status.IN_PROGRESS.name())){
            task.setCompletedAt(LocalDateTime.now());
            task.setStatus(Status.COMPLETED.name());
        }

        return  taskRepository.save(task);
    }

    @Override
    public List<Task> findAllByUser_idAndStatus(String status , Long user_id){
        return taskRepository.listOfTasksByStatus(status, user_id);
    }






    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email + "not found in the database"));
    }

    @Override
    public void updateStatus(Long id) {
        Task task = getTaskById(id);
        task.setStatus(Status.IN_PROGRESS.name());
        System.out.println("1 Task: " + task);
        taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException( "Task not found in the database"));
    }
}