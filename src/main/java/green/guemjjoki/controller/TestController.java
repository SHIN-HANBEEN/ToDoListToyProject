package green.guemjjoki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/layout/basic")
    public String ex1(){
        return "/layout/basic";
    }
}
