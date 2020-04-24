package com.gwh.seller.repositories;

import com.gwh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
    //验证是否存在用户名
     User  findUserByUsername(String username);
    //登录
     User  findUserByUsernameAndPassword(String username,String password);
     //账户充值
     User  findUserById(int id);
}
