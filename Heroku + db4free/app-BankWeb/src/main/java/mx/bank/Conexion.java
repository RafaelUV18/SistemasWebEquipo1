package mx.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static String url="jdbc:mysql://db4free.net:3306/bancoweb2?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
    private static String driverName= "com.mysql.cj.jdbc.Driver";
    private static String username ="johnny_18014493";
    private static String password ="administrador";
    private static Connection conexion =null;

    public Connection getConexion(){
        try {
            Class.forName(driverName);
            conexion=DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Failed to create connection");
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            System.out.println("Driver not found");
        }
        return conexion;
    }
}
