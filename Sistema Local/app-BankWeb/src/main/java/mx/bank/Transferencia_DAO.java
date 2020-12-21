package mx.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

public class Transferencia_DAO {

    public static boolean create(Transferencia transferencia) throws Exception {
        Connection con = null;
        String query = "insert into transferencia VALUES (null, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        Conexion cc = new Conexion();
        try {
            con = cc.getConexion();
            ps = con.prepareStatement(query);

            ps.setString(1, transferencia.getOrigen());
            ps.setString(2, transferencia.getDestino());
            ps.setDouble(3, transferencia.getMonto());
            ps.setString(4, transferencia.getReferencia());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en update SQLException: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en update Exception " + e.getMessage());
        }
        return true;
    }
    
    public static List<Transferencia> readAll(Tarjeta tarjeta) throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        List<Transferencia> transferencias = new ArrayList<>();
        String sql = "select * from transferencia where origen = '" + tarjeta.getNumero() + "' or destino = '" + tarjeta.getNumero() + "'";
        try {
            con = new Conexion().getConexion();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Transferencia transferencia = new Transferencia(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5));
                transferencias.add(transferencia);
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
        return transferencias;
    }
}