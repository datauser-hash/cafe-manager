package com.datauser.cafemanager.controller;

import com.datauser.cafemanager.models.Table;
import com.datauser.cafemanager.models.User;
import com.datauser.cafemanager.service.TableService;
import com.datauser.cafemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private UserService userService;

    @Autowired
    private TableService tableService;

    public ManagerController(UserService userService, TableService tableService) {
        this.userService = userService;
        this.tableService = tableService;
    }

    @GetMapping("/userList")
    public String listUser(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/tableList")
    public String tableUser(Model model) {
        List<Table> tables = tableService.findAll();
        model.addAttribute("tables", tables);
        return "table-list";
    }
}
