package org.marcossousadev;

import org.marcossousadev.model.entitys.ProductEntity;
import org.marcossousadev.model.entitys.dao.DaoFactory;
import org.marcossousadev.model.entitys.dao.ProductDao;

import java.math.BigDecimal;

public class MainInsert {
    public static void main(String[] args) {
        ProductEntity product = new ProductEntity();
        product.setName("Carregador");
        product.setDescription("50W de potência.");
        product.setPrice(BigDecimal.valueOf(50.00));
        product.setQuantity(50);
        product.setCategory("Utilitários");
        ProductDao productDao = DaoFactory.createProductDao();
        productDao.insert(product);
    }
}
