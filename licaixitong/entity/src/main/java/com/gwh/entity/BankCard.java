package com.gwh.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class BankCard {
    @Id
    private String id;

    private String bankCardNum;

    private  String password;

    private BigDecimal balance ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankCardNum() {
        return bankCardNum;
    }

    public void setBankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    //1.	id
//2.	银行卡号 bankCardNum
//3.	密码 password
//4.	余额 BigDecimal balance decimal(15,3)

}
