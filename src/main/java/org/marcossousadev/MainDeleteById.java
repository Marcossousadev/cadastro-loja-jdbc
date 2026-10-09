package org.marcossousadev;

import org.marcossousadev.model.entitys.dao.DaoFactory;
import org.marcossousadev.model.entitys.dao.ProductDao;

public class MainDeleteById {
    public static void main(String[] args) {
        ProductDao productDao = DaoFactory.createProductDao();
        productDao.deleteById(7);
    }
}
