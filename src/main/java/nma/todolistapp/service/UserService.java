package nma.todolistapp.service;

import nma.todolistapp.dto.TaskDTO;
import nma.todolistapp.dto.UserDTO;
import nma.todolistapp.model.Task;
import nma.todolistapp.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserService {
//    User save(UserDTO userRegistration);

   // boolean login(String email, String password);
User registerUser(UserDTO userDTO);

    String loginUser(String email, String password);

    Task createTask(TaskDTO taskDTO);

    User getUserById(Long id);

    Task updateTitleAndDescription(TaskDTO taskDTO , Long id);

    Task markTaskCompleted(Long id);

    List<Task> allTaskByUserId(Long id);


    Task getTaskById(Long id);

    List<Task> viewAllTasks();

//    int updateTaskStatus(String status, int id);

    List<Task> viewAllTaskByStatus(String status, Long user_id);

    //List<Task> findAllByUser_idAndStatus(Long user_id , String status);

    boolean deleteById(Long id);

    List<Task> findAllByUser_idAndStatus(String status , Long user_id);

    User getUserByEmail(String email);


    void updateStatus(Long id);
}






