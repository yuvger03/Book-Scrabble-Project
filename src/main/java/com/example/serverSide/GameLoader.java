package com.example.serverSide;
import java.sql.*;
import java.util.*;
public class GameLoader {
    static final String JDBC_DRIVER = "com.mysql.jbdc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost";

    static  final String USER = "username";
    static  final String PASS = "password";
    public static List<Integer> createTable() throws SQLException, ClassNotFoundException {
        List<Integer> list = new LinkedList<Integer>();
        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        String createTable = "CREATE TABLE GAME " +
                "(id INTEGER not NULL)";
        ResultSet rs = stmt.executeQuery(createTable);

//        while (rs.next()){
//            int x =1;
//            rs.getInt(x);
//            Integer number = x;
//            list.add(number);
//        }
        rs.close();
        return list;
    }
    public static void main(String[] args) {
        try {
            createTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
