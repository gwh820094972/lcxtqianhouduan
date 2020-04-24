package com.gwh.seller.controller;


import com.gwh.seller.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bankCard")
public class BankCardController {
    @Autowired
    BankCardService bankCardService;
    //用户充值,扣除银行卡金额
    @PostMapping("/userRecharge")
    public String UserRecharge(@RequestHeader String authId, @RequestHeader String sign,
                                 @RequestHeader String cardNum, @RequestHeader String cardPassword,
                                 @RequestHeader String amount){

            return bankCardService.UserRecharge(cardNum,cardPassword,amount);
    }
    //用户提现，增加银行卡金额
    @PostMapping("/userWithdraw")
    public String userWithdraw(@RequestHeader String authId, @RequestHeader String sign,
                                 @RequestHeader String cardNum, @RequestHeader String amount){
        return bankCardService.userWithdraw(cardNum,amount);
    }

}
