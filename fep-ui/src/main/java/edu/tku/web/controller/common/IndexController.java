package edu.tku.web.controller.common;

import edu.tku.db.model.Func;
import edu.tku.db.model.User;
import edu.tku.db.repository.FuncRepository;
import edu.tku.db.repository.UserRepository;
import edu.tku.web.entity.CustomUserDetails;
import edu.tku.web.entity.MenuItem;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Log4j2
public class IndexController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FuncRepository funcRepository;
    @RequestMapping("/")
    public String index(Model model, @RequestParam(required = false) String name, HttpSession session){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("name", customUserDetails.getUsername());

        Map<String, List<Func>> funcs = funcRepository.findAll().stream().collect(Collectors.groupingBy(Func::getParentId));
        List<MenuItem> menuItems = getMenu("root", funcs);
        session.setAttribute("funcs", menuItems);
        return "index";
    }

    private List<MenuItem> getMenu(String funcId, Map<String, List<Func>> funcs) {
        List<MenuItem> items = new ArrayList<>();
        List<Func> funcList = funcs.get(funcId);
        if(funcList != null) {
            for(Func func : funcList) {
                MenuItem menuItem = new MenuItem();
                menuItem.setFuncId(func.getFuncId());
                menuItem.setFuncUrl(func.getFuncUrl());
                menuItem.setFuncName(func.getFuncName());
                if(funcs.containsKey(func.getFuncId())) {
                    menuItem.setSubMenu(getMenu(func.getFuncId(), funcs));
                }
                items.add(menuItem);
            }
        }
        return items;
    }
}
