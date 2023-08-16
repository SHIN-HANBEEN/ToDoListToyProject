package green.guemjjoki.controller;

import green.guemjjoki.dto.TodolistDetailResponseDTO;
import green.guemjjoki.dto.TodolistResponseDTO;
import green.guemjjoki.entitiy.TodoBoard;
import green.guemjjoki.service.TodoBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/todolist")
@RequiredArgsConstructor
public class TodoBoardViewController {

    private final TodoBoardService todoBoardService;

    @GetMapping(value = "/board")
    public String getTodolist(Model model){
        List<TodolistResponseDTO> list = todoBoardService.getTodoList().
                stream()
                .map(TodolistResponseDTO::new)
                .toList();
        model.addAttribute("list", list);
        return "todolist/listView";
    }

    @GetMapping("/read/{no}")
    public String getDetailView(@PathVariable("no") Long no, Model model){
        TodoBoard detailView = todoBoardService.getDetailView(no);
        model.addAttribute("dto",new TodolistDetailResponseDTO(detailView));
        return "todolist/read";
    }


}
