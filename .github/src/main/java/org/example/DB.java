package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static String url = "jdbc:mariadb://localhost:3306/db_empleados";
    private static String user = "root";
    private static String password = "";
    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa");
            System.out.println(conn.getCatalog());
        }
        return conn;
    }

}