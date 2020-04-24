package com.gwh.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Administrator {
    //
//    1.	id
//    2.	账号username
//    3.	密码 password
//    4.	权限 authority
    @Id
    private String id;

    private String username;

    private String password;

    private String authority;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }



}
