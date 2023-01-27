package com.empresa.backendspringboot.controlador;

import com.empresa.backendspringboot.excepciones.ResourceNotFoundException;
import com.empresa.backendspringboot.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.empresa.backendspringboot.repositorio.ClienteRepositorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"http://localhost:4200", "https://soft-custard-a1a7cb.netlify.app"})
public class ClienteControlador {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    // Metodo sirve para listar todos los clientes
    @GetMapping("/clientes")
    public List<Cliente> listarTodosLosClientes() {
        return (List<Cliente>) clienteRepositorio.findAll();
    }

    // Metodo sirve para guardar el cliente
    @PostMapping("/clientes")
    public Cliente guardarCliente(@RequestBody Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    // Metodo sirve para buscar un cliente
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el cliente con el ID " + id));
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente detallesCliente) {
        Cliente cliente = clienteRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el cliente con el ID " + id));
        cliente.setNombre(detallesCliente.getNombre());
        cliente.setPaterno(detallesCliente.getPaterno());
        cliente.setMaterno(detallesCliente.getMaterno());
        cliente.setTipoDocumento(detallesCliente.getTipoDocumento());
        cliente.setDocumento(detallesCliente.getDocumento());
        cliente.setFechaNacimiento(detallesCliente.getFechaNacimiento());
        cliente.setGenero(detallesCliente.getGenero());
        Cliente clienteActualizado = clienteRepositorio.save(cliente);
        return ResponseEntity.ok(clienteActualizado);
    }

    //este metodo sirve para eliminar un cliente
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarCliente(@PathVariable Long id){
        Cliente cliente = clienteRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el cliente con el ID : " + id));

        clienteRepositorio.delete(cliente);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/clientes-pageable")
    List<Cliente> listarClientesPageable(@RequestParam int page, @RequestParam int size) {
        return clienteRepositorio.findAll(PageRequest.of(page, size)).getContent();
    }
}
