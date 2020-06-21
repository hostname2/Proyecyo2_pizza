/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;


/**
 *
 * @author sebas
 */
public class Cliente {

    private String id;

    private String nombre;

    private String apellidos;

    private String direccion;

    private int telefono;
    
    private String idUsuario;

    private Usuario usuarioidUsuario;

    private List<Factura> facturaCollection;

    public Cliente() {
    }

    public Cliente(String id, String nombre, String apellidos, String direccion, int telefono, String idUsuario, Usuario usuarioidUsuario, List<Factura> facturaCollection) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.idUsuario = idUsuario;
        this.usuarioidUsuario = usuarioidUsuario;
        this.facturaCollection = facturaCollection;
    }

    public Cliente(String id, String nombre, String apellidos, String direccion, int telefono, String idUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.idUsuario = idUsuario;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getUsuarioidUsuario() {
        return usuarioidUsuario;
    }

    public void setUsuarioidUsuario(Usuario usuarioidUsuario) {
        this.usuarioidUsuario = usuarioidUsuario;
    }

    public List<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(List<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
    }
    
    
}
