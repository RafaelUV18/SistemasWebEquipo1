package mx.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import java.util.ArrayList;
import java.util.List;

public class Tarjeta_DAO {
    public static boolean create(Tarjeta tarjeta) throws Exception {
        Connection con = null;
        String query = "insert into tarjeta VALUES (?, ?, ?, 499.99, ?)";
        PreparedStatement ps = null;
        Conexion cc = new Conexion();
        try {
            con = cc.getConexion();
            ps = con.prepareStatement(query);

            ps.setString(1, tarjeta.getNumero());
            ps.setString(2, tarjeta.getVencimiento());
            ps.setString(3, tarjeta.getCvc());
            ps.setString(4, tarjeta.getNo_Cuenta());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en update SQLException: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en update Exception " + e.getMessage());
        }
        return true;
    }

    public static Tarjeta read(String numero) throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select * from tarjeta where numero = '" + numero + "'";
        Tarjeta tarjeta = new Tarjeta();
        try {
            con = new Conexion().getConexion();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                tarjeta.setNumero(rs.getString(1));
                tarjeta.setVencimiento(rs.getString(2));
                tarjeta.setCvc(rs.getString(3));
                tarjeta.setSaldo(rs.getDouble(4));
                tarjeta.setNo_Cuenta(rs.getString(5));
            }
            stm.close();
            rs.close();
            con.close();
        } catch (CommunicationsException e) {
            throw new Exception("Error en read ComunicationsException: " + e.getCause().toString());
        } catch (SQLException e) {
            throw new Exception("Error en read SQLException: " + e.getCause().toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en read Exception: " + e.getMessage());
        }
        return tarjeta;
    }

    public static List<Tarjeta> readAll() throws Exception {
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        List<Tarjeta> resultado = new ArrayList<>();
        String sql = "select * from tarjeta";
        try {
            con = new Conexion().getConexion();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Tarjeta tarjeta = new Tarjeta(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5));
                resultado.add(tarjeta);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            throw new Exception("Error: Método readAll" + e.getMessage());
        }
        return resultado;
    }

    public static int delete(String numero) throws Exception {
        Connection con = null;
        Statement stmn = null;
        String sql = "delete from tarjeta where numero = '" + numero + "'";
        int borra;
        try {
            con = new Conexion().getConexion();
            stmn = con.createStatement();
            borra = stmn.executeUpdate(sql);
            stmn.close();
            con.close();
        } catch (CommunicationsException e) {
            throw new Exception("Error en read ComunicationsException: " + e.getCause().toString());
        } catch (SQLException e) {
            throw new Exception("Error en read SQLException: " + e.getCause().toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en read Exception: " + e.getMessage());
        }
        return borra;
    }

    public static boolean transferencia(Tarjeta tarjeta) throws Exception {
        Connection con = null;
        boolean actualizar = false;
        PreparedStatement ps = null;
        String sql2= "UPDATE tarjeta SET saldo = " + tarjeta.getSaldo() + " WHERE numero = '" + tarjeta.getNumero() + "'";
        Conexion cc = new Conexion();
        try {
            con = cc.getConexion();
            ps=con.prepareStatement(sql2);
            actualizar =true;
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en update SQLException: " + e.getMessage());
        } catch(Exception e){
            throw new Exception("Error en update Exception " + e.getMessage());
        }
        return actualizar; 
    }

    public static Tarjeta readTarjetas(String noCuenta) throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select * from tarjeta where no_cuenta = '" + noCuenta + "'";
        Tarjeta tarjeta = new Tarjeta();
        try {
            con = new Conexion().getConexion();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                tarjeta.setNumero(rs.getString(1));
                tarjeta.setVencimiento(rs.getString(2));
                tarjeta.setCvc(rs.getString(3));
                tarjeta.setSaldo(rs.getDouble(4));
                tarjeta.setNo_Cuenta(rs.getString(5));
            }
            stm.close();
            rs.close();
            con.close();
        } catch (CommunicationsException e) {
            throw new Exception("Error en read ComunicationsException: " + e.getCause().toString());
        } catch (SQLException e) {
            throw new Exception("Error en read SQLException: " + e.getCause().toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en read Exception: " + e.getMessage());
        }
        return tarjeta;
    }

    public static List<Tarjeta> readAllTarjetas(String noCuenta) throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        List<Tarjeta> tarjetas = new ArrayList<>();
        String sql = "select * from tarjeta where no_cuenta = '" + noCuenta + "'";
        try {
            con = new Conexion().getConexion();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Tarjeta tarjeta = new Tarjeta(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5));
                tarjetas.add(tarjeta);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (CommunicationsException e) {
            throw new Exception("Error en read ComunicationsException: " + e.getCause().toString());
        } catch (SQLException e) {
            throw new Exception("Error en read SQLException: " + e.getCause().toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en read Exception: " + e.getMessage());
        }
        return tarjetas;
    }
}