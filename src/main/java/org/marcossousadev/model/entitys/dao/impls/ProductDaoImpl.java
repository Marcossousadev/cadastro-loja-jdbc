package org.marcossousadev.model.entitys.dao.impls;

import org.marcossousadev.db.DB;
import org.marcossousadev.exception.DBException;
import org.marcossousadev.exception.NotFoundProductException;
import org.marcossousadev.model.entitys.ProductEntity;
import org.marcossousadev.model.entitys.dao.ProductDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private final Connection connection;

    public ProductDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(ProductEntity product) {
        PreparedStatement preparedStatement = null;
        try {
            String sqlInsert = "INSERT INTO products (name, description, price, quantity, category) VALUES ( ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setBigDecimal(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setString(5, product.getCategory());
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException) {
            throw new RuntimeException(sqlException.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public ProductEntity findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sqlConsultById = "SELECT * FROM products WHERE ?";
            preparedStatement = connection.prepareStatement(sqlConsultById);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            // .next() avançar, pois o cursor fica antes da primeira linha
            // se houver um registro dísponivel ele retorna true, quando não há mais, ele retorna false
            if(resultSet.next()) {
                return getProduct(resultSet);
            }
            else {
                throw new NotFoundProductException("Esse produto não foi encontrado.");
            }

        }
        catch (SQLException sqlException) {
            throw new RuntimeException(sqlException.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public List<ProductEntity> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sqlFindAll = "SELECT * FROM products";
            preparedStatement = connection.prepareStatement(sqlFindAll);
            resultSet = preparedStatement.executeQuery();
            List<ProductEntity> products = new ArrayList<>();

            while (resultSet.next()) {
                products.add(getProduct(resultSet));
            }
            return products;
        }
        catch (SQLException sqlException) {
            throw new RuntimeException(sqlException.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public int deleteById(Integer id) {
        PreparedStatement preparedStatement = null;
        findById(id);
        try {
            String sqlDeleteById = "DELETE FROM products WHERE id = ?";
            preparedStatement = connection.prepareStatement(sqlDeleteById);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException) {
            throw new RuntimeException(sqlException.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public int updateStockByIdProduct(ProductEntity product) {
        PreparedStatement preparedStatement = null;

        try {
            String sqlUpdateById = "UPDATE products SET price = ?, quantity = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sqlUpdateById);
            preparedStatement.setBigDecimal(1, product.getPrice());
            preparedStatement.setInt(2, product.getQuantity());
            preparedStatement.setInt(3, product.getId());

            return preparedStatement.executeUpdate(); // retorna linhas afetadas, em inteiro
        }
        catch (SQLException sqlException) {
            throw new RuntimeException(sqlException.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
        }
    }

    public ProductEntity getProduct(ResultSet resultSet) throws SQLException {
        ProductEntity product = new ProductEntity();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setQuantity(resultSet.getInt("quantity"));
        product.setCategory(resultSet.getString("category"));
        product.setCreated_at(resultSet.getTimestamp("created_at").toLocalDateTime());
        return product;
    }
}
