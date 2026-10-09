package org.marcossousadev.db;

import org.marcossousadev.exception.DBException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection connection = null;

    public static Connection getConnections() {
       try {
           if(connection == null) {
               Properties properties = loadProperties();
               // buscando a URL do banco de dados pela chave
               String url = properties.getProperty("dburl");
               // realizando conexão através das chaves de acesso
               connection = DriverManager.getConnection(url, properties);
           }
       }
       catch (SQLException sqlException) {
           throw new DBException(sqlException.getMessage());
       }
        return connection;
    }
    // FileInputStream serve para ler dados de um arquivo em Java
    private static Properties loadProperties() { // falo para ele ler os dados do meu arquivo
        try(FileInputStream fileInputStream = new FileInputStream("db.properties")) {
            Properties properties = new Properties();
            // salva as configurações em chave e valor
            properties.load(fileInputStream);
            return properties;
        }
        catch (IOException exception) {
            throw new RuntimeException("Erro ao ler dados do arquivo de conexão do banco de dados");
        }
    }

    public static void closeStatement(Statement st) {
        try {
            if(st != null) {
                st.close();
            }
        }
        catch (SQLException sqlException) {
            throw new DBException(sqlException.getMessage());
        }
    }
    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (SQLException sqlException) {
            throw new DBException(sqlException.getMessage());
        }
    }
}
