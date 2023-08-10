package green.guemjjoki.service;

import green.guemjjoki.repository.TodoBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoBoardService {

    private final TodoBoardRepository todoBoardRepository;

}
