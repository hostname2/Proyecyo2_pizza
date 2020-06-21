/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiciosDB;

import Datos.BaseDatos;
import Model.Cliente;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author sebas
 */
public class ServicioDBCliente {

    public Cliente obtenerClientebyUser(String user) {
        Cliente r = null;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR_BY_USER);) {
            stm.clearParameters();
            stm.setString(1, user);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    r = new Cliente(
                            rs.getString("id"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("direccion"),
                            rs.getInt("telefono"),
                            user
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

    public void agregarCliente(Cliente u) {

        ServicioDBUsuario su = new ServicioDBUsuario();
        su.agregarUser(u.getUsuarioidUsuario());
        
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_INSERTAR)) {
            stm.clearParameters();
            stm.setString(1, u.getId());
            stm.setString(2, u.getNombre());
            stm.setString(3, u.getApellidos());
            stm.setString(4, u.getDireccion());
            stm.setInt(5, u.getTelefono());
            stm.setString(6, u.getIdUsuario());

            if (stm.executeUpdate() != 1) {
                throw new Exception("Error no determinado");
            }
        } catch (Exception ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }

    }

    public void actualizarCliente(Cliente u) {

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ACTUALIZAR)) {
            stm.clearParameters();
            stm.setString(1, u.getNombre());
            stm.setString(2, u.getApellidos());
            stm.setString(3, u.getDireccion());
            stm.setInt(4, u.getTelefono());
            stm.setString(5,u.getId());

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

    public static void main(String[] args) {
        ServicioDBCliente su = new ServicioDBCliente();

    }

    private static final String CMD_ACTUALIZAR
            = "UPDATE Cliente SET nombre='?', apellidos='?', direccion='?', telefono=? where id='?'; ";

    private static final String CMD_INSERTAR
            = "INSERT INTO Cliente "
            + "(id, nombre, apellidos, direccion, telefono, Usuario_idUsuario) "
            + "VALUES(?, ?, ?, ?, ?, ?); ";

    private static final String CMD_RECUPERAR
            = "SELECT id, nombre, apellidos, direccion, telefono, Usuario_idUsuario FROM Cliente; ";

    private static final String CMD_RECUPERAR_BY_USER
            = "SELECT id, nombre, apellidos, direccion, telefono FROM Cliente where Usuario_idUsuario =`?`; ";
}
