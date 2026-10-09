package org.marcossousadev;

import org.marcossousadev.model.entitys.ProductEntity;
import org.marcossousadev.model.entitys.dao.DaoFactory;
import org.marcossousadev.model.entitys.dao.ProductDao;

import java.math.BigDecimal;

public class MainUpdateStockByIdProduct {
    public static void main(String[] args) {
        ProductEntity product = new ProductEntity();
        product.setPrice(BigDecimal.valueOf(25.00));
        product.setQuantity(20);
        product.setId(7);
        ProductDao productDao = DaoFactory.createProductDao();
        productDao.updateStockByIdProduct(product);
    }
}
