/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author sebas
 */
public class Complemento {

    private Integer idComplemento;

    private String nombre;

    private double precio;
    
    private String tipo;

//    private Collection<Detalle> detalleCollection;

    public Complemento() {
    }

    public Complemento(Integer idComplemento, String nombre, double precio, String tipo) {
        this.idComplemento = idComplemento;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    public Integer getIdComplemento() {
        return idComplemento;
    }

    public void setIdComplemento(Integer idComplemento) {
        this.idComplemento = idComplemento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
