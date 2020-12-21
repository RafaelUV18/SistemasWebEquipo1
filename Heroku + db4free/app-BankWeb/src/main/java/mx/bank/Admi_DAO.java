package mx.bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Admi_DAO {
    public static Admi readAll() throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select * from administrador";
                Admi admi = new Admi();

        try {
            con = new Conexion().getConexion();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                admi.setId(rs.getInt(1));
                admi.setCorreo(rs.getString(2));
                admi.setPass(rs.getString(3));    
            }
            stm.close();
            rs.close();
            con.close();
        }catch (SQLException e){
            throw new Exception("Error en read SQLException: " + e.getCause().toString());
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Error en read Exception: " + e.getMessage());
        }
        return admi;
    }
}
