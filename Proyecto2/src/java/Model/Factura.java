/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author sebas
 */
public class Factura {
    private Integer idFactura;

    private Date fecha;

    private Cliente clienteid;

    private Detalle detalleidDetalle;

    private Restaurante restaurante;

    public Factura() {
    }

    public Factura(Integer idFactura, Date fecha) {
        this.idFactura = idFactura;
        this.fecha = fecha;
    }
    

    public Factura(Integer idFactura, Date fecha, Cliente clienteid, Detalle detalleidDetalle, Restaurante restaurante) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.clienteid = clienteid;
        this.detalleidDetalle = detalleidDetalle;
        this.restaurante = restaurante;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getClienteid() {
        return clienteid;
    }

    public void setClienteid(Cliente clienteid) {
        this.clienteid = clienteid;
    }

    public Detalle getDetalleidDetalle() {
        return detalleidDetalle;
    }

    public void setDetalleidDetalle(Detalle detalleidDetalle) {
        this.detalleidDetalle = detalleidDetalle;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
    
    
}
