package ru.nsu.ccfit.radeev.commonclient.database;

import ru.nsu.ccfit.radeev.commonclient.config.DatabaseConfig;

import java.sql.*;

public class DatabaseConnector {
    private static DatabaseConnector instance = new DatabaseConnector();

    private Connection connection;
    private Statement statement;

    public static DatabaseConnector getInstance(){
        return instance;
    }

    private DatabaseConnector(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch(ClassNotFoundException e){
            System.out.println("DB driver error");
            e.printStackTrace();
        }

        try{
            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_LOGIN, DatabaseConfig.DB_PASSWORD);
            if(!connection.isClosed()){
                System.out.println("Connected to SQL server.");
            }
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }catch(SQLException e){
            System.out.println("SQL connection error");
            e.printStackTrace();
        }
    }

    public ResultSet DoSelect(String query) throws SQLException{
        ResultSet rs = null;
        rs = statement.executeQuery(query);
        return rs;
    }

    public Connection getConnection(){
        return connection;
    }

    public static void dispose(){
        synchronized (DatabaseConnector.class){
            try{
                DatabaseConnector locInstance = DatabaseConnector.instance;
                if(locInstance != null){
                    locInstance.statement.close();
                    locInstance.connection.close();
                }
            }catch(SQLException e){
                System.out.println("SQL dispose error");
                e.printStackTrace();
            }finally{
                DatabaseConnector.instance = null;
            }
        }
    }
}
