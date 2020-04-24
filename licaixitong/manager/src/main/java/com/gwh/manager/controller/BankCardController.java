package com.gwh.manager.controller;


import com.gwh.entity.BankCard;
import com.gwh.manager.repositories.BankCardRepository;
import com.gwh.manager.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
