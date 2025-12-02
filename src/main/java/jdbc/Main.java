package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args){
        String sql = "SELECT * FROM users ORDER BY id OFFSET 2";
        try (
                var connection = DB.connect()
        ) {
            assert connection != null;
            try (var statement = connection.createStatement();
                 var resultSet = statement.executeQuery(sql)
                    ){
                System.out.println("Connected to the PostgreSQL database.");

                while (resultSet.next()) {
                    System.out.println(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println("Disconnected from the PostgreSQL database.");
        }
    }
}