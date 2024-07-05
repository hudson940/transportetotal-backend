package com.eis.transportetotal.controllers;

import com.eis.transportetotal.dtos.RegisterGasto;
import com.eis.transportetotal.dtos.RegisterVehiculo;
import com.eis.transportetotal.dtos.RegisterViaje;
import com.eis.transportetotal.entity.*;
import com.eis.transportetotal.repositories.IGastoRepository;
import com.eis.transportetotal.repositories.IRutaRepository;
import com.eis.transportetotal.repositories.IVehiculoRepository;
import com.eis.transportetotal.repositories.IViajeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RequestMapping("/viajes")
@RestController
public class ViajeController {
    private final IViajeRepository viajeRepository;
    private final IGastoRepository gastoRepository;


    public ViajeController(IViajeRepository viajeRepository,IGastoRepository gastoRepository) {
        this.viajeRepository = viajeRepository;
        this.gastoRepository = gastoRepository;

    }

    @GetMapping
    public ResponseEntity findAll() {
        try{
            final List<Viaje> viajes = viajeRepository.findAll();
            return ResponseEntity.ok(viajes);
        }
        catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity getViaje(@PathVariable Long id) {
        try{
            final Optional<Viaje> viaje = viajeRepository.findById(id);
            if(!viaje.isPresent()){
                return ResponseEntity.status(400).body("Viaje no encontrado");
            }
            return ResponseEntity.ok(viaje);
        }
        catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }



    @PostMapping
    public ResponseEntity save(@RequestBody RegisterViaje viaje) {
        try{

            final Viaje newViaje = new Viaje();
            final Ruta ruta = new Ruta();
            final Vehiculo vehiculo = new Vehiculo();
            ruta.setId(viaje.id_ruta);
            vehiculo.setId(viaje.id_vehiculo);
            newViaje.setFecha(viaje.fecha);
            newViaje.setDistanciaRecorrida(viaje.distancia_recorrida);
            newViaje.setRuta(ruta);
            newViaje.setVehiculo(vehiculo);
            var saveViaje = viajeRepository.save(newViaje);
            return ResponseEntity.ok(saveViaje);
        }
        catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/add_gasto")
    public ResponseEntity saveGasto(@RequestBody RegisterGasto gasto) {
        try{

            final Gasto newGasto = new Gasto();
            newGasto.setMonto(gasto.monto);
            newGasto.setNombre(gasto.nombre);
            var viaje = viajeRepository.findById(gasto.id_viaje);
            if(!viaje.isPresent()){
                return ResponseEntity.status(400).body("Viaje no encontrado");
            }
            newGasto.setViajes(viaje.get());
            var saveGasto = gastoRepository.save(newGasto);
            return ResponseEntity.ok(saveGasto);
        }
        catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
