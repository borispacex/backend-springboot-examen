package com.empresa.backendspringboot.repositorio;

import com.empresa.backendspringboot.modelo.Cuenta;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CuentaRepositorio extends PagingAndSortingRepository<Cuenta, Long> {

    List<Cuenta> findAll();

    List<Cuenta> findAllByCliente_IdCliente(Long idCliente);

    @Transactional
    void deleteAllByCliente_IdCliente(long idCliente);

}
