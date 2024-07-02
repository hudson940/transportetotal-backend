package com.eis.transportetotal.repositories;


import com.eis.transportetotal.entity.User;
import com.eis.transportetotal.entity.Vehiculo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IVehiculoRepository extends CrudRepository<Vehiculo, Long> {
    @Override
    List<Vehiculo> findAll();
}
