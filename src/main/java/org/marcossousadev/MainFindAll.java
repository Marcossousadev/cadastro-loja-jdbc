package org.marcossousadev;

import org.marcossousadev.model.entitys.ProductEntity;
import org.marcossousadev.model.entitys.dao.DaoFactory;
import org.marcossousadev.model.entitys.dao.ProductDao;

import java.util.List;

public class MainFindAll {
    public static void main(String[] args) {
        ProductDao productDao = DaoFactory.createProductDao();
        List<ProductEntity> products = productDao.findAll();

        products.forEach(System.out::println);
    }
}
