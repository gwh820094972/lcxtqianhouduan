package com.gwh.manager.repositories;

import com.gwh.entity.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCardRepository extends JpaRepository<BankCard,String> {
    BankCard findBankByBankCardNum(String cardNum);
    BankCard findBankByBankCardNumAndPassword(String cardNum,String password);
}
