package nma.todolistapp.repository;

import nma.todolistapp.dto.TaskDTO;
import nma.todolistapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    @Query(value = "SELECT * FROM task  WHERE status = ?1 AND user_id = ?2" , nativeQuery = true)
    List<Task> listOfTasksByStatus(String Status,Long id);

//    @Query(value = "UPDATE tasks SET status = ?1 where  id = ?2" , nativeQuery = true)
//    boolean updateTaskByIdAndStatus(String status , int id);
@Modifying
@Transactional
@Query(value = "UPDATE task t SET t.status = ? where  t.id = ?" , nativeQuery = true)
int updateTaskByIdAndStatus(String status , Long id);

    @Query(value = "SELECT * FROM task t WHERE t.user_id = ?" , nativeQuery = true)
    List<Task> findAllByUser_id(Long user_id);

    @Query(value = "SELECT * FROM task t WHERE t.user_id = ?1" , nativeQuery = true)
    List<Task> findAllByUser_idAndStatus(Long user_id);










//    int countAllByCompleted(boolean completed);
//
//    List<Task> findAllByCompleted(boolean completed);
}



