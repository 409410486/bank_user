package edu.tku.web.controller.common;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Controller
@Log4j2
public class LoginController {


    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) boolean error){
        if(error) {
            model.addAttribute("error", "account or password is incorrect.");
        }
        return "login";
    }
}
