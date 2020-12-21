package mx.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import java.util.ArrayList;
import java.util.List;

public class Cliente_DAO {
    public static boolean create(Cliente cliente) throws Exception {
        Connection con = null;
        String query = "insert into cliente VALUES (null,?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        Conexion cc = new Conexion();
        try {
            con = cc.getConexion();
            ps = con.prepareStatement(query);
 
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getEdad());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, cliente.getContrasena());
            ps.setString(7, cliente.getTelefono());
            ps.setString(8, cliente.getCuenta());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en update SQLException: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en update Exception " + e.getMessage());
        }
        return true;
    }

    public static List<Cliente> readAll() throws Exception {
        Statement stm=null;
        Connection con=null;
        ResultSet rs=null;
        List<Cliente> resultado=new ArrayList<>();
        String sql = "select * from cliente";
        try {
            con = new Conexion().getConexion();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Cliente cliente=new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
                resultado.add(cliente);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            throw new Exception("Error: Método readAll" + e.getMessage());
        }
        return resultado;
    }

    public static Cliente read(int id) throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select * from cliente where id = " + id;
        Cliente cliente = new Cliente();
        try {
            con = new Conexion().getConexion();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                cliente.setId(rs.getInt(1));
                cliente.setNombre(rs.getString(2));
                cliente.setApellidos(rs.getString(3));
                cliente.setEdad(rs.getString(4));
                cliente.setCorreo(rs.getString(5));
                cliente.setDireccion(rs.getString(6));
                cliente.setContrasena(rs.getString(7));
                cliente.setTelefono(rs.getString(8));
                cliente.setCuenta(rs.getString(9));
            }
            stm.close();
            rs.close();
            con.close();
        } catch (CommunicationsException e) {
            throw new Exception("Error en read ComunicationsException: " + e.getCause().toString());
        } catch (SQLException e){
            throw new Exception("Error en read SQLException: " + e.getCause().toString());
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Error en read Exception: " + e.getMessage());
        }
        return cliente;
    }

    public static boolean update(Cliente cliente) throws Exception {
        Connection con = null;
        boolean actualizar = false;
        PreparedStatement ps = null;
        String sql2= "UPDATE cliente SET nombre=?,apellidos=?,edad=?,"
             + "correo=?,direccion=?,contrasena=?, telefono=?, no_cuenta=?"
                + " WHERE id=?";
        Conexion cc = new Conexion();
        try {
            con = cc.getConexion();
            ps=con.prepareStatement(sql2);
            
            ps.setString(1,cliente.getNombre());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getEdad());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5,cliente.getDireccion());
            ps.setString(6, cliente.getContrasena());
            ps.setString(7, cliente.getTelefono());
            ps.setString(8,cliente.getCuenta());
            ps.setInt(9,cliente.getId());
            actualizar =true;
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en update SQLException: " + e.getMessage());
        } catch(Exception e){
            throw new Exception("Error en update Exception " + e.getMessage());
        }
        return actualizar; 
    }

    public static int delete(int id) throws Exception {
        Connection con = null;
        Statement stmn = null;
        String sql = "delete from cliente where id = " + id;
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

    public static Cliente readLogin(String email) throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select * from cliente where correo = '" + email+"'";
                Cliente cliente = new Cliente();

        try {
            con = new Conexion().getConexion();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                cliente.setId(rs.getInt(1));
                cliente.setNombre(rs.getString(2));
                cliente.setApellidos(rs.getString(3));
                cliente.setEdad(rs.getString(4));
                cliente.setCorreo(rs.getString(5));
                cliente.setDireccion(rs.getString(6));
                cliente.setContrasena(rs.getString(7));
                cliente.setTelefono(rs.getString(8));
                cliente.setCuenta(rs.getString(9));
            }
            stm.close();
            rs.close();
            con.close();
        } catch (CommunicationsException e) {
            throw new Exception("Error en read ComunicationsException: " + e.getCause().toString());
        } catch (SQLException e){
            throw new Exception("Error en read SQLException: " + e.getCause().toString());
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Error en read Exception: " + e.getMessage());
        }
        return cliente;
    }
}