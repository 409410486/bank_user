package edu.tku.web.controller.system;

import edu.tku.db.model.Role;
import edu.tku.db.model.User;
import edu.tku.db.repository.RoleRepository;
import edu.tku.db.repository.UserRepository;
import lombok.extern.log4j.Log4j2;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
@Log4j2
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/user")
    public String page(Model model, @RequestParam(name = "userId", required = false) String userId) {
        List<User> users = new ArrayList<>();
        if(userId != null && !userId.equals("")) {
            userRepository.findById(userId).ifPresent(user -> users.add(user));
        }else{
            users.addAll(userRepository.findAll());
        }
        model.addAttribute("users", users);
        return "system/user";
    }
    @GetMapping("/user/detail")
    public String pageDetail(Model model, @RequestParam(name = "userId", required = false) String userId) {
        User user = userRepository.findById(StringUtils.defaultString(userId, "")).orElse(new User());
        model.addAttribute("user", user);

        return "system/userDetail";
    }
    @PostMapping("/user")
    public String pageDetail(Model model, @ModelAttribute User user) {
        if(user.getAction().equals("D")) {
            userRepository.deleteById(user.getUserId());
        }else {
            String pwd = new BCryptPasswordEncoder().encode(user.getPassword());
            String RoleId = "";
            if(user.getRoleId() == null){
                RoleId = UUID.randomUUID().toString();
                user.setRoleId(RoleId);
            }else{
                RoleId = user.getRoleId();
            }
            user.setPassword(pwd);
            user.setEnabled(true);
            user.setRoleId(RoleId);
            user.setDepId(" ");
            user.setBranchId(" ");
            user.setEmail(" ");
            user.setLastLoginIp(" ");
            
            user.setToken(" ");
            userRepository.save(user);

            Role role = new Role();
            role.setRoleId(RoleId);
            role.setRoleName(" ");
            role.setRoleDesc(" ");
            roleRepository.save(role);
        }
        model.addAttribute("users", userRepository.findAll());
        
        return "system/user";
    }

}
