package mx.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import java.util.ArrayList;
import java.util.List;

public class Cuenta_DAO {
    public static boolean create(Cuenta cuenta) throws Exception {
        Connection con = null;
        String query = "insert into cuenta VALUES (?)";
        PreparedStatement ps = null;
        Conexion cc = new Conexion();
        try {
            con = cc.getConexion();
            ps = con.prepareStatement(query);
            ps.setString(1, cuenta.getNo_Cuenta());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en update SQLException: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en update Exception " + e.getMessage());
        }
        return true;
    }

    public static List<Cuenta> readAll() throws Exception {
        Statement stm=null;
        Connection con=null;
        ResultSet rs=null;
        List<Cuenta> resultado = new ArrayList<>();
        String sql = "select * from cuenta";
        try {
            con = new Conexion().getConexion();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Cuenta cuenta = new Cuenta(rs.getString(1));
                resultado.add(cuenta);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            throw new Exception("Error: Método readAll" + e.getMessage());
        }
        return resultado;
    }

    public static Cuenta read(String no_Cuenta) throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select * from cuenta where no_cuenta = '" + no_Cuenta + "'";
        Cuenta cuenta = new Cuenta();
        try {
            con = new Conexion().getConexion();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                cuenta.setNo_Cuenta(rs.getString(1));
            }
            stm.close();
            rs.close();
            con.close();
        } catch (CommunicationsException e) {
            throw new Exception("Error en read ComunicationsException: " + e.getCause().toString());
        } catch (SQLException e){
            throw new Exception("Error en read SQLException: " + e.getCause().toString());
        }catch(Exception e){
            throw new Exception("Error en read Exception: " + e.getMessage());
        }
        return cuenta;
    }

    public static boolean update(Cuenta cuenta, String anterior) throws Exception {
        Connection con = null;
        boolean actualizar = false;
        PreparedStatement ps = null;
        String sql2= "UPDATE cuenta SET no_cuenta = " + cuenta.getNo_Cuenta() + " WHERE no_cuenta = " + anterior + "";
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

    public static int delete(String numero) throws Exception {
        Connection con = null;
        Statement stmn = null;
        String sql = "delete from cuenta where no_cuenta = '" + numero+"'";
        int borrar;
        try {
            con = new Conexion().getConexion();
            stmn = con.createStatement();
            borrar = stmn.executeUpdate(sql);
            stmn.close();
            con.close();
        } catch (CommunicationsException e) {
            throw new Exception("Error en read ComunicationsException: " + e.getCause().toString());
        } catch (SQLException e){
            throw new Exception("Error en read SQLException: " + e.getCause().toString());
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Error en read Exception: " + e.getMessage());
        }
        return borrar;
    }
}