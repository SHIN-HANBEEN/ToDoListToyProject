package green.guemjjoki.repository;

import green.guemjjoki.entitiy.TodoBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoBoardRepository extends JpaRepository<TodoBoard, Long> {


}
