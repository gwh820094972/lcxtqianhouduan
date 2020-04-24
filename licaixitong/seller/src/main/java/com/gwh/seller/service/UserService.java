package com.gwh.seller.service;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.gwh.entity.User;
import com.gwh.seller.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.gwh.util.JWTUtil;
import java.math.BigDecimal;
import com.auth0.jwt.JWT;


@Service
public class UserService {
    @Autowired
    UserRepository repository;


    //注册 返回token
    public String register(User user) {
        //判断是否用户名已经存在
        if(hasUsername(user)=="用户名不存在，可以注册"){
            //判断是否用户名已经存在
            //数据校验
            checkUser(user);

            //设置默认值
            setDefault(user);

            //入库
            User result = repository.saveAndFlush(user);
            //生成token的方法
            return JWTUtil.getToken(result);
        }else {
            System.out.println("户名已经存在");
            return "户名已经存在";
        }
    }

    //用户修改用户信息modify
    public String modify(String email,String bankCardNum ,String token,String newPassword,String password) {

        // 根据token获取user对象
        User  user = getUserByToken(token);
       String oldPassword =user.getPassword();
        User userResult = repository.findUserByUsernameAndPassword(user.getUsername(),password);
        if(userResult!=null ){
            //判断是否token正确 即，token查询的oldUser的id是否和传进的 newUser 的id相同
//       if( newUser.getId() == oldUser.getId()){
//           //入库
//           User result = repository.saveAndFlush(newUser);
//          // 重新获取新的token
//           return JWTUtil.getToken(result);
//       }
//           else {return "token验证错误";}
                //将传入的新user入库
               if(email != null&&email.length()>0){
                   user.setEmail(email);
               }
               if ( bankCardNum!= null&&bankCardNum.length()>0){
                   user.setBankCardNum(bankCardNum);
               }
                if (newPassword != null&&newPassword.length()>0){
                    user.setPassword(newPassword);
                }else {
                    user.setPassword(oldPassword);

                }
                 repository.saveAndFlush(user);
                // 重新获取新的token
                return JWTUtil.getToken(user);

        }else {
            return "用户密码输入错误";
        }

    }




    //登录 返回token
    public String login(String username, String  password){
        User hasUsername =repository.findUserByUsername(username);
        User user = repository.findUserByUsernameAndPassword(username,password);
        if(hasUsername == null){
            return "账号不存在";
        } else {
            if (user != null){
                //账号密码正确
                //生成token的方法
                return JWTUtil.getToken(user);
            }
            else{
                return "账号或密码错误。";
            }
        }
    }


    //根据token获取用户信息
    public User userInfo(String  token){

        // 根据token获取user对象
       User user =  getUserByToken(token);

        //验证token
       boolean result = JWTUtil.checkToken(user,token);
       if (result == true){
           return user;
       } else {
            return null;
        }
    }

    // 方法:根据token获取user对象
    public User getUserByToken(String token){
        // 获取 token 中的 user username
        String  username;
        try {
            username = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("不存在该token");
        }
        //获取token的user
        return repository.findUserByUsername(username);
    }

    //账户充值 根据token
    public String recharge(String token,String amount){
        // 根据token获取user对象
        User user = getUserByToken(token);
        //验证token
        boolean result = JWTUtil.checkToken(user,token);
        if(result){
            //尽量用字符串的形式初始化，金额为BigDecimal类型
            BigDecimal amountBigDecimal = new BigDecimal(amount);
            //BigDecimal加法
            BigDecimal addStr =user.getBalance().add(amountBigDecimal);
            user.setBalance(addStr);
            repository.saveAndFlush(user);
            return user.getBalance()+"";
        }else
            {return "充值失败，token错误";}

    }


    //账户提现 根据用户token
    public String withdraw (String  token,String amount,String userPassword){
       User user = getUserByToken(token);
        //验证token
        boolean result = JWTUtil.checkToken(user,token);
        if (result == true){
         User passwordCheck = repository.findUserByUsernameAndPassword(user.getUsername(),userPassword);
            if( passwordCheck != null){
                //将金额转换为BigDecimal类型，尽量用字符串的形式初始化，金额为BigDecimal类型
                BigDecimal amountBigDecimal = new BigDecimal(amount);

                BigDecimal UserBalance = user.getBalance();

                if(UserBalance.compareTo(amountBigDecimal)>=0){
                    // a.compareTo(b) : a>b时返回1， a=b时返回0 ，a<b时返回-1
                    // 判断当用户余额>=提现金额时才能进行提现操作
                    BigDecimal subStr =UserBalance.subtract(amountBigDecimal);
                    user.setBalance(subStr);
                    repository.saveAndFlush(user);
                    return user.getBalance()+"";
                } else {
                    return "账号余额不足";
                }
            }else {
                return "用户密码错误";
            }
        } else {return "token错误";}
    }

    //申购产品 根据用户id
    public String userApply (String token,String amount,String password){
        // 根据token获取user对象
        User user = getUserByToken(token);
       User userResult =repository.findUserByUsernameAndPassword(user.getUsername(),password);
        if (userResult!=null) {
            //验证token
            boolean result = JWTUtil.checkToken(user,token);
            if (result == true){
                //尽量用字符串的形式初始化，金额为BigDecimal类型
                BigDecimal amountBigDecimal = new BigDecimal(amount);

                BigDecimal UserBalance = user.getBalance();

                if(UserBalance.compareTo(amountBigDecimal)>=0){
                    // a.compareTo(b) : a>b时返回1， a=b时返回0 ，a<b时返回-1
                    // 判断当用户余额>=申购金额时才能进行申购操作
                    BigDecimal subStr =UserBalance.subtract(amountBigDecimal);
                    user.setBalance(subStr);
                    repository.saveAndFlush(user);
                    return "用户购买订单成功;";
                }
                else {
                    //返回原本的user
                    return "用户金额不足请充值后操作";
                }
            } else {
                return "token不正确";
            }
        }else {
            return "用户密码不正确";
        }



    }

    //用户赎回 根据用户id
    public String userRedeem(String token,String amount){
        // 根据token获取user对象
        User user = getUserByToken(token);
//        User userResult = repository.findUserByUsernameAndPassword(user.getUsername(),password);
        if(true){
            //验证token
            boolean result = JWTUtil.checkToken(user,token);
            if (result == true){
                //尽量用字符串的形式初始化，金额为BigDecimal类型
                BigDecimal amountBigDecimal = new BigDecimal(amount);
                //BigDecimal加法
                BigDecimal addStr =user.getBalance().add(amountBigDecimal);
                user.setBalance(addStr);
                repository.saveAndFlush(user);
                return "赎回订单成功";
            } else {
                return "token错误";
            }

        }else  {
            return "用户密码错误";
        }

    }

    //判断用户名是否已经存在
    private String hasUsername(User user){
        User result = repository.findUserByUsername(user.getUsername());
        if(result==null){
            //用户名不存在，可以注册
           String str ="用户名不存在，可以注册";
            return str;
        }else {
            //用户名已经存在，不可以注册
            String str ="用户名已经存在，不可以注册";
            return str;
        }

    }

    /*
     * 产品数据校验
     */
    private void checkUser(User user) {
        Assert.notNull(user.getUsername(), "用户名不可为空");
        Assert.notNull(user.getPassword(), "密码");
        Assert.notNull(user.getBankCardNum(), "绑定银行卡号不可为空");
        Assert.notNull(user.getEmail(), "邮箱");
    }

    //完善数据
    private void setDefault (User user){
        if (user.getBalance() == null) {
            user.setBalance(BigDecimal.ZERO);
        }
        user.setAuthority("user");


    }

}
