/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiciosDB;

import Datos.BaseDatos;
import Model.Pizza;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author sebas
 */
public class ServicioDBPizza {

    public List<Pizza> obtenerListaPizza() {
        List<Pizza> r = new ArrayList<>();
        try (Connection cnx = obtenerConexion();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(CMD_LISTAR)) {
            while (rs.next()) {
                Pizza c = new Pizza(
                        rs.getString("nombre"),
                        rs.getString("tamaño"),
                        rs.getString("codigo"),
                        rs.getDouble("precio")
                );
                r.add(c);
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

    public void agregarPizza(Pizza u) {

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR)) {
            stm.clearParameters();
            stm.setString(1, u.getNombre());
            stm.setString(2, u.getTamaño());
            stm.setString(3, u.getCodigo());
            stm.setDouble(4, u.getPrecio());

            if (stm.executeUpdate() != 1) {
                throw new Exception("Error no determinado");
            }
        } catch (Exception ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }

    public boolean eliminarPizza(String codigo) {

        boolean r = false;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ELIMINAR);) {
            stm.clearParameters();
            stm.setString(1, codigo);
            try (ResultSet rs = stm.executeQuery()) {
                r = true;
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

    private static final String CMD_ELIMINAR
            = "DELETE FROM Pizza where "
            + "codigo='?'; ";

    private static final String CMD_LISTAR
            = "SELECT nombre, tamaño, codigo, precio FROM Pizza; ";
    private static final String CMD_AGREGAR
            = "INSERT INTO Pizza "
            + "(nombre, tamaño, codigo, precio) "
            + "VALUES(?, ?, ?); ";
}
