package flashcards.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {

    @RequestMapping
    public String main() {
        return "main";
    }

    @RequestMapping("/signin")
    public String signin() {
        return "signin";
    }


}
