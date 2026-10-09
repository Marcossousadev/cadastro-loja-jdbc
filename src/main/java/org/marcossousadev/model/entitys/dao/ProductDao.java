package org.marcossousadev.model.entitys.dao;

import org.marcossousadev.model.entitys.ProductEntity;

import java.util.List;

public interface ProductDao {
    void insert(ProductEntity product);
    ProductEntity findById(Integer id);
    List<ProductEntity> findAll();
    int deleteById(Integer id);
    int updateStockByIdProduct(ProductEntity product);
}
