package org.marcossousadev.model.entitys.dao;

import org.marcossousadev.db.DB;
import org.marcossousadev.model.entitys.dao.impls.ProductDaoImpl;

public class DaoFactory {

    public static ProductDao createProductDao() {
        return new ProductDaoImpl(DB.getConnections());
    }
}
