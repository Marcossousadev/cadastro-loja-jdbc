package org.marcossousadev;

import org.marcossousadev.model.entitys.dao.DaoFactory;
import org.marcossousadev.model.entitys.ProductEntity;
import org.marcossousadev.model.entitys.dao.ProductDao;

public class MainFindById {
    public static void main(String[] args) {
        ProductDao productDao = DaoFactory.createProductDao();
        ProductEntity product = productDao.findById(1);

        System.out.println(product);
    }
}
