package nma.todolistapp.repository;

import nma.todolistapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Integer> {

    @Query(value = "SELECT * FROM tasks WHERE status = ?1" , nativeQuery = true)
    List<Task> listOfTasksByStatus(String Status);

    @Query(value = "UPDATE tasks SET status = ?1 where  id = ?2" , nativeQuery = true)
    boolean updateTaskByIdAndStatus(String status , int id);
}


