package com.gwh.seller.controller;

import com.gwh.entity.Order;
import com.gwh.seller.params.OrderParam;
import com.gwh.seller.service.OrderService;
import com.gwh.seller.service.UserService;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单相关
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    static Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;


//    @RequestMapping(value = "/apply", method = RequestMethod.POST)
//    public Order apply(@RequestBody Order order) {
//        order = orderService.apply(order);
//        return order;
//    }
    /**
     * 下单
     * 强制要求在请求头中要设置authId和sign属性
     *从请求头中获取 authId，sign
     * @param param
     * @return
     */
    @PostMapping("/apply")
    public String apply(@RequestHeader String authId, @RequestHeader String sign, @RequestHeader String token, @RequestBody OrderParam param,
                         @RequestHeader String password, @RequestParam String amount) {
        LOG.info("申购请求:{}", param);
        //先判断账号余额是否大于订单金额
        String userResult  = userService.userApply(token,amount,password);
        if(userResult=="用户购买订单成功;"){
            //创建一个订单对象
            Order order = new Order();
            //将参数param值赋值给订单对象order
            BeanUtils.copyProperties(param,order);
            order = orderService.apply(order);
            LOG.info("申购结果:{}", order);
            return userResult;
        }else if(userResult=="密码不正确"){
            return userResult;
        }else {
            return userResult;
        }
    }

    //赎回
    @PostMapping( "/redeem")
    public String redeem(@RequestHeader String authId, @RequestHeader String sign,@RequestHeader String token, @RequestParam String orderId,@RequestParam String redeemAmount) {
//        LOG.info("赎回请求:{}", param);
        //创建一个订单对象
//        Order order = new Order();
        //将参数param值赋值给订单对象order
//        BeanUtils.copyProperties(param,order);
        String  userResult = userService.userRedeem(token,redeemAmount);
        if(userResult == "赎回订单成功" ){
           String orderResult = orderService.redeem(orderId);
            if(orderResult == "订单赎回成功")
            {
                return "订单赎回成功";
            }
            else if(orderResult=="未过锁定期，无法赎回")
            {
                return "未过锁定期，无法赎回";
            }return "";
        }else if(userResult == "token错误"){
            return "token错误";
        }else if(userResult == "token错误"){
            return "token错误";
        }
        else{
            return "用户密码错误";
        }
//        LOG.info("赎回结果:{}", order);

    }

    //按订单拥有者token查看所有订单
    @GetMapping("searchByOwnerId")
    public List<Order> searchByOwnerId (@RequestHeader String authId,  @RequestHeader String sign,@RequestHeader String token){
        return orderService.searchByOwnerId(token);

    }
    //按订单拥有者id查看申购状态订单
//    @GetMapping("searchByOwnerIdAndOrderType")
//    public List<Order> searchByOwnerIdAndOrderType (@RequestHeader String authId, @RequestHeader String sign,@RequestParam String ownerId,@RequestParam String orderType){
//        return orderService.searchByOwnerIdAndOrderType(ownerId,orderType);
//
//    }
}
