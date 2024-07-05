package com.eis.transportetotal.repositories;

import com.eis.transportetotal.entity.Ruta;
import com.eis.transportetotal.entity.Vehiculo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IRutaRepository extends CrudRepository<Ruta, Long> {
    @Override
    List<Ruta> findAll();

}
