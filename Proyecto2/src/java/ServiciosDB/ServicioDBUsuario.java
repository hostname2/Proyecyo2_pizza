/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiciosDB;

import Datos.BaseDatos;
import Model.Cliente;
import Model.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author sebas
 */
public class ServicioDBUsuario {
    
        public Usuario obtenerUsuariobyUser(String user) {
        Usuario r = null;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR_BY_USER);) {
            stm.clearParameters();
            stm.setString(1, user);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    r = new Usuario(
                           user,
                            rs.getString("clave"),
                            rs.getShort("tipo")
                    );
                }
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return r;
    }

    public void agregarUser(Usuario u) {

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_INSERTAR)) {
            stm.clearParameters();
            stm.setString(1, u.getIdUsuario());
            stm.setString(2, u.getClave());
            stm.setInt(3, u.getTipo());

            if (stm.executeUpdate() != 1) {
                throw new Exception("Error no determinado");
            }
        } catch (Exception ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }

    }

    public void actualizarUser(Usuario u) {

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ACTUALIZAR)) {
            stm.clearParameters();
            stm.setString(1, u.getClave());
            stm.setString(2, u.getIdUsuario());

            if (stm.executeUpdate() != 1) {
                throw new Exception("Error no determinado");
            }
        } catch (Exception ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }

    }

    public Connection obtenerConexion() throws
            ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            IOException,
            SQLException {
        BaseDatos bd = BaseDatos.obtenerInstancia();
        Properties cfg = bd.obtenerConfiguracion();
        Connection cnx = bd.obtenerConexion(
                cfg.getProperty("database"),
                cfg.getProperty("user"),
                cfg.getProperty("password")
        );
        return cnx;
    }

    private static final String CMD_ACTUALIZAR
            = "UPDATE Cliente SET clave='?' where idUsuario='?'; ";

    private static final String CMD_INSERTAR
            = "INSERT INTO Usuario "
            + "(idUsuario, clave, tipo) "
            + "VALUES(?, ?, ?); ";

    private static final String CMD_RECUPERAR_BY_USER
            = "SELECT  clave, tipo FROM Usuario where idUsuario =`?`; ";
}
