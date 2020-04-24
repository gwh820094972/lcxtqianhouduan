package com.gwh.manager.repositories;


import com.gwh.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator,String>, JpaSpecificationExecutor<Administrator> {
    Administrator findAdministratorByUsernameAndPassword(String username,String password);
    Administrator findAdministratorByUsername(String username);
}

