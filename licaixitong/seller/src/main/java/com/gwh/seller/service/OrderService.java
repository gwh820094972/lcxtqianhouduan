package com.gwh.seller.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.gwh.entity.Order;
import com.gwh.entity.User;
import com.gwh.entity.enums.OrderStatus;
import com.gwh.entity.enums.OrderType;
import com.gwh.seller.repositories.OrderRepository;
import com.gwh.seller.repositories.UserRepository;
import com.gwh.util.JWTUtil;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.security.PublicKey;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 订单服务
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    UserRepository repository;

    /**下单
     * 申购订单
     * @param order
     * @return
     */
    public Order apply(Order order){
        //数据校验
        checkOrder(order);
        //完善订单数据
        completeOrder(order);
        order = orderRepository.saveAndFlush(order);
        return order;
    }

    private void checkOrder(Order order) {
        //必填字段
//        Assert.notNull(order.getOuterOrderId(),"需要外部订单编号");
//        Assert.notNull(order.getChanId(),"需要渠道编号");
        Assert.notNull(order.getChanUserId(),"需要订单拥有者用户编号");
        Assert.notNull(order.getProductId(),"需要产品编号");
        Assert.notNull(order.getProductName(),"需要产品名称");
        Assert.notNull(order.getProductLockTerm(),"需要产品锁定期");
        Assert.notNull(order.getAmount(),"需要购买金额");
//        Assert.notNull(order.getCreateAt(),"需要订单时间");

        //产品是否存在及金额是否符合要求
//        Product product = productService.findOne(order.getProductId());
//        Assert.notNull(product,"产品不存在");
        //金额要满足如果有起投金额时，要大于等于起投金额，如果有投资步长时，超过起投金额的部分要是投资步长的整数倍

    }

    /**
     * 完善订单数据
     * @param order
     */
    private void completeOrder(Order order) {
        //随机生成订单id
        order.setOrderId(UUID.randomUUID().toString().replaceAll("-",""));
        order.setOrderType(OrderType.APPLY.name());
        order.setOrderStatus(OrderStatus.SUCCESS.name());
        order.setUpdateAt(new Date());
        order.setCreateAt(new Date());
//        order.setChanId("xx理财平台");
//        order.setOuterOrderId(order.getProductId());
    }

    //赎回
    public String redeem(String orderid){
//        order.setOrderId(order.getChanUserId());
        Order order = orderRepository.findOrderTByOrderId(orderid);
        int productLockTerm = order.getProductLockTerm();
        Date redeemDate = new Date();
        Date CreateDate = order.getCreateAt();
        long day=(redeemDate.getTime()-CreateDate.getTime())/(24*60*60*1000);
        if(day>=productLockTerm){
            Assert.notNull(order, "orderid不可为空或错误");
            order.setOrderType(OrderType.REDEEM.name());
            order.setOrderStatus(OrderStatus.SUCCESS.name());
            order.setUpdateAt(new Date());
            order = orderRepository.saveAndFlush(order);
            return "订单赎回成功";
        }else {
            return "未过锁定期，无法赎回";
        }

    }

    // //按订单拥有者(token中的用户id)查看所有订单
    public List<Order> searchByOwnerId (String token){
        // 根据token获取user对象
        User user = getUserByToken(token);
        //验证token
        boolean result = JWTUtil.checkToken(user,token);
        if (result == true){
            return orderRepository.findOrderTByChanUserId((user.getId()+""));
        } else {
            return null;
        }

    }

    // 根据token获取user对象
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


    // //按订单拥有者id查看申购状态订单
//    public List<Order> searchByOwnerIdAndOrderType(String ownerId,String orderType){
//       return orderRepository.findOrderTByChanUserIdAndOrderType(ownerId,orderType);
//    }

}
