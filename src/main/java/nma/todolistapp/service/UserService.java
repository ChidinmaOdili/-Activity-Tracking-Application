package nma.todolistapp.service;

import nma.todolistapp.dto.TaskDTO;
import nma.todolistapp.dto.UserDTO;
import nma.todolistapp.model.Task;
import nma.todolistapp.model.User;

import java.util.List;

public interface UserService {
//    User save(UserDTO userRegistration);

   // boolean login(String email, String password);
User registerUser(UserDTO userDTO);

    String loginUser(String email, String password);

    Task createTask(TaskDTO taskDTO);

    Task updateTitleAndDescription(TaskDTO taskDTO , int id);

    Task getTaskById(int id);

    List<Task> viewAllTasks();

    boolean updateTaskStatus(String status, int id);

    List<Task> viewAllTaskByStatus(String status);

    boolean deleteById(int id);
    User getUserByEmail(String email);

}






