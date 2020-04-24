package com.gwh.seller.params;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gwh.seller.sign.SignText;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 下单的请求参数
 * 实现SignText接口
 * 对传入的参数进行按字典排序和非空处理
 */
public class OrderParam implements SignText{
    //调用方需要传递的参数
    //渠道id
    private String id;
    private String chanId;

    private String chanUserId;

    private String productId;

    private String productName;
    private  Integer productLockTerm;
    private BigDecimal amount;

    private String outerOrderId;

    private String memo;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date createAt;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductLockTerm() {
        return productLockTerm;
    }

    public void setProductLockTerm(Integer productLockTerm) {
        this.productLockTerm = productLockTerm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChanId() {
        return chanId;
    }

    public void setChanId(String chanId) {
        this.chanId = chanId;
    }

    public String getChanUserId() {
        return chanUserId;
    }

    public void setChanUserId(String chanUserId) {
        this.chanUserId = chanUserId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOuterOrderId() {
        return outerOrderId;
    }

    public void setOuterOrderId(String outerOrderId) {
        this.outerOrderId = outerOrderId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
