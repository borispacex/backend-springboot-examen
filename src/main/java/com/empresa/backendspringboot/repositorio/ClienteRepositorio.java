package com.empresa.backendspringboot.repositorio;

import com.empresa.backendspringboot.modelo.Cliente;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepositorio extends PagingAndSortingRepository<Cliente, Long> {

    List<Cliente> findAll();
}
