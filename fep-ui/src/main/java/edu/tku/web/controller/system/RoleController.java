package edu.tku.web.controller.system;

import edu.tku.db.model.Role;
import edu.tku.db.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/role")
    public String page(Model model, @RequestParam(name = "roleId", required = false) String roleId) {
        List<Role> roles = new ArrayList<>();
        if(roleId != null && !roleId.equals("")) {
            roleRepository.findById(roleId).ifPresent(role -> roles.add(role));
        }else{
            roles.addAll(roleRepository.findAll());
        }
        model.addAttribute("roles", roles);
        return "system/role";
    }
    @GetMapping("/role/detail")
    public String pageDetail(Model model, @RequestParam(name = "roleId", required = false) String roleId) {
        Role role = roleRepository.findById(StringUtils.defaultString(roleId, "")).orElse(new Role());
        model.addAttribute("role", role);
        return "system/roleDetail";
    }
    @PostMapping("/role")
    public String pageDetail(Model model, @ModelAttribute Role role) {
        if(role.getAction().equals("D")) {
            roleRepository.deleteById(role.getRoleId());
        }else {
            roleRepository.save(role);
        }
        model.addAttribute("roles", roleRepository.findAll());
        return "system/role";
    }

}
