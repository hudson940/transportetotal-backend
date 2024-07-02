package com.eis.transportetotal.controllers;


import com.eis.transportetotal.dtos.RegisterVehiculo;
import com.eis.transportetotal.entity.Vehiculo;
import com.eis.transportetotal.repositories.IVehiculoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/vehiculos")
@RestController
public class VehiculoController {

    private final IVehiculoRepository vehiculoRepository;

    public VehiculoController(IVehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @GetMapping
    public ResponseEntity findAll() {
        try{
            final List<Vehiculo> vehiculos = vehiculoRepository.findAll();
            return ResponseEntity.ok(vehiculos);
        }
        catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody RegisterVehiculo vehiculo) {
        try{

            final Vehiculo newVehiculo = new Vehiculo();
            newVehiculo.setPlaca(vehiculo.placa);
            newVehiculo.setMarca(vehiculo.marca);
            newVehiculo.setModelo(vehiculo.modelo);
            var saveVehiculo = vehiculoRepository.save(newVehiculo);
            return ResponseEntity.ok(saveVehiculo);
        }
        catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
