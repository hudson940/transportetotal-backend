package com.eis.transportetotal.repositories;


import com.eis.transportetotal.entity.Gasto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IGastoRepository extends CrudRepository<Gasto, Long> {
    @Override
    List<Gasto> findAll();
}
