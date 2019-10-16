package com.project.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Date;

@Controller
public class AttendanceController {
    private String appMode;

    @Autowired
    public AttendanceController(Environment environment){
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("/index2")
    public String index(Model model){
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", "Ömerrrr");
        model.addAttribute("mode", appMode);

        return "header";
    }
}