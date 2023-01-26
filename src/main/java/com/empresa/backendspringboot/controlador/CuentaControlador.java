package com.empresa.backendspringboot.controlador;

import com.empresa.backendspringboot.excepciones.ResourceNotFoundException;
import com.empresa.backendspringboot.modelo.Cuenta;
import com.empresa.backendspringboot.repositorio.ClienteRepositorio;
import com.empresa.backendspringboot.repositorio.CuentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class CuentaControlador {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private CuentaRepositorio cuentaRepositorio;

    @GetMapping("/clientes/{id}/cuentas")
    public ResponseEntity<List<Cuenta>> listarTodasLasCuentasPorIdCliente(@PathVariable(value = "id") Long id) {
        if (!clienteRepositorio.existsById(id)) {
            throw new ResourceNotFoundException("No se encontro el cliente con ID " + id);
        }
        List<Cuenta> cuentas = cuentaRepositorio.findAllByCliente_IdCliente(id); // error
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }

    // Metodo sirve para listar todas los cuentas
    @GetMapping("/cuentas")
    public List<Cuenta> listarTodasLasCuentas() {
        return (List<Cuenta>) cuentaRepositorio.findAll();
    }

    // Metodo sirve para guardar la cuenta
    @PostMapping("/clientes/{id}/cuentas")
    public ResponseEntity<Cuenta> guardarCuenta(@PathVariable(value = "id") Long id, @RequestBody Cuenta cuenta) {
        Cuenta nuevaCuenta = clienteRepositorio.findById(id).map(cliente -> {
            cuenta.setCliente(cliente);
            return cuentaRepositorio.save(cuenta);
        }).orElseThrow(() -> new ResourceNotFoundException("No se encontro cliente con ir " + id));
        return new ResponseEntity<>(nuevaCuenta, HttpStatus.CREATED);
    }

    // Metodo sirve para buscar una cuenta
    @GetMapping("/cuentas/{id}")
    public ResponseEntity<Cuenta> obtenerCuentaPorId(@PathVariable Long id) {
        Cuenta cuenta = cuentaRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la cuenta con el ID " + id));
        return ResponseEntity.ok(cuenta);
    }

    @PutMapping("/cuentas/{id}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long id, @RequestBody Cuenta detallesCuenta) {
        Cuenta cuenta = cuentaRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la cuenta con el ID " + id));
        cuenta.setTipoCuenta(detallesCuenta.getTipoCuenta());
        cuenta.setNroCuenta(detallesCuenta.getNroCuenta());
        cuenta.setMoneda(detallesCuenta.getMoneda());
        cuenta.setMonto(detallesCuenta.getMonto());
        cuenta.setFechaCreacion(detallesCuenta.getFechaCreacion());
        cuenta.setSucursal(detallesCuenta.getSucursal());
        // cuenta.setContent(cuenta.getContent()); // Se podria solucionar con solo un metodo que haga todo lo anterior
        Cuenta cuentaActualizada = cuentaRepositorio.save(cuenta);
        return ResponseEntity.ok(cuentaActualizada);
    }

    //este metodo sirve para eliminar una cuenta
    @DeleteMapping("/cuentas/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarCuenta(@PathVariable Long id){
        Cuenta cuenta = cuentaRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la cuenta con el ID : " + id));

        cuentaRepositorio.delete(cuenta);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/clientes/{id}/cuentas")
    public ResponseEntity<List<Cuenta>> eliminarCuentaPorCliente(@PathVariable(value = "id") Long id) {
        if(!clienteRepositorio.existsById(id)) {
            throw new ResourceNotFoundException("No se encontro cliente con id " + id);
        }
        cuentaRepositorio.deleteAllByCliente_IdCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/cuentas-pageable")
    List<Cuenta> listarCuentasPageable(@RequestParam int page, @RequestParam int size) {
        return cuentaRepositorio.findAll(PageRequest.of(page, size)).getContent();
    }

}
