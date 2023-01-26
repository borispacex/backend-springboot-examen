package com.empresa.backendspringboot.modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;
    @Column(name = "paterno", length = 50, nullable = false)
    private String paterno;
    @Column(name = "materno", length = 50, nullable = false)
    private String materno;
    @Column(name = "tipo_documento", length = 50, nullable = false)
    private String tipoDocumento;
    @Column(name = "documento" ,length = 20, unique = true ,nullable = false)
    private String documento;
    @Column(name = "fecha_nacimiento", length = 10)
    private String fechaNacimiento;
    @Column(name = "genero", length = 100)
    private String genero;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cuenta> cuentas;

    public void agregarCuentas(Cuenta cuenta) {
        if (cuentas == null) {
            cuentas = new ArrayList<>();
        }
        cuentas.add(cuenta);
        cuenta.setCliente(this);
    }

    public Cliente() { }

    public Cliente(Long idCliente, String nombre, String paterno, String materno, String tipoDocumento, String documento, String fechaNacimiento, String genero) {
        super();
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}
