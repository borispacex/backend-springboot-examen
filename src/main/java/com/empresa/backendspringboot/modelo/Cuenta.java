package com.empresa.backendspringboot.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @Column(name = "id_cuenta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuenta;

    @Column(name = "tipo_cuenta", length = 50, nullable = false)
    private String tipoCuenta;
    @Column(name = "nro_cuenta", length = 50, nullable = false)
    private String nroCuenta;
    @Column(name = "moneda", length = 2, nullable = false)
    private String moneda;
    @Column(name = "monto")
    private Long monto;
    @Column(name = "fecha_creacion", length = 10)
    private String fechaCreacion;
    @Column(name = "sucursal", length = 100)
    private String sucursal;

    @ManyToOne(fetch = FetchType.LAZY)
    // @JsonIgnoreProperties(value = "cuentas")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    public void agregarCliente(Cliente cliente) {
        this.setCliente(cliente);
    }

    public Cuenta() {  }

    public Cuenta(Long idCuenta, String tipoCuenta, String nroCuenta, String moneda, Long monto, String fechaCreacion, String sucursal, Cliente cliente) {
        super();
        this.idCuenta = idCuenta;
        this.tipoCuenta = tipoCuenta;
        this.nroCuenta = nroCuenta;
        this.moneda = moneda;
        this.monto = monto;
        this.fechaCreacion = fechaCreacion;
        this.sucursal = sucursal;
        this.cliente = cliente;
    }

    public Long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Long getMonto() {
        return monto;
    }

    public void setMonto(Long monto) {
        this.monto = monto;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "idCuenta=" + idCuenta +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", nroCuenta='" + nroCuenta + '\'' +
                ", moneda='" + moneda + '\'' +
                ", monto=" + monto +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", sucursal='" + sucursal + '\'' +
                '}';
    }
}
