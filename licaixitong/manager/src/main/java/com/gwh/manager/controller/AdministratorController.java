package com.gwh.manager.controller;

import com.gwh.entity.Administrator;
import com.gwh.manager.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {
    @Autowired
    private AdministratorService  administratorService;

//    注册
//    @PostMapping("/register")
//    public Administrator registor(@RequestHeader String authId, @RequestHeader String sign, @RequestBody Administrator administrator){
//       return administratorService.register(administrator);
//
//    }

//    登入
    @PostMapping("login")
    public String login(@RequestHeader String authId, @RequestHeader String sign,@RequestHeader  String username,  @RequestHeader  String password){

        return administratorService.login(username,password);

    }

}
