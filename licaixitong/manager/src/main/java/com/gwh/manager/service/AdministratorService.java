package com.gwh.manager.service;

import com.gwh.entity.Administrator;
import com.gwh.manager.repositories.AdministratorRepository;
import com.gwh.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepository repository;
//    注册
//    public Administrator register(Administrator administrator){
//        Administrator result = repository.save(administrator);
//        return result;
//    }
    //登录
    public String login(String username, String password){
        Administrator hasAdministrator =repository.findAdministratorByUsername(username);
        Administrator administrator = repository.findAdministratorByUsernameAndPassword(username,password);
        if(hasAdministrator == null){
            return "账号不存在";
        } else {
            if (administrator != null){
                //账号密码正确
                //生成token的方法
                return JWTUtil.getTokenAdministrator(administrator);
            }
            else{
                return "账号或密码错误。";
            }
        }
    }

}
