package com.gwh.manager.repositories;

import com.gwh.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String>,JpaSpecificationExecutor<Product>{
  Product findProductByName(String name);
  Product findProductById(String id);
}
