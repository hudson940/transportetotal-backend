package com.eis.transportetotal.repositories;

import com.eis.transportetotal.entity.Ruta;
import com.eis.transportetotal.entity.Viaje;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IViajeRepository extends CrudRepository<Viaje, Long> {
    @Override
    List<Viaje> findAll();
}
