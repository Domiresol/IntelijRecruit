package com.recruit.kr.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping("/all")
    public void exAll() {
        log.info("exAll..........");
    }

    @GetMapping("/member")
    @PreAuthorize("hasRole('USER')")
    public String Member() {
        log.info("exMember..........");
        return "/member.html";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String exAdmin() {
        log.info("exAdmin..........");
        return "/admin.html";
    }


}
