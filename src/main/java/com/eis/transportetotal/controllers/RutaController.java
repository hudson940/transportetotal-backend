package com.eis.transportetotal.controllers;

import com.eis.transportetotal.dtos.RegisterRuta;
import com.eis.transportetotal.dtos.RegisterVehiculo;
import com.eis.transportetotal.entity.Ruta;
import com.eis.transportetotal.entity.Vehiculo;
import com.eis.transportetotal.repositories.IRutaRepository;
import com.eis.transportetotal.repositories.IVehiculoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/rutas")
@RestController
public class RutaController {
    private final IRutaRepository rutaRepository;

    public RutaController(IRutaRepository rutaRepository) {
        this.rutaRepository = rutaRepository;
    }

    @GetMapping
    public ResponseEntity findAll() {
        try{
            final List<Ruta> rutas = rutaRepository.findAll();
            return ResponseEntity.ok(rutas);
        }
        catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity save(@RequestBody RegisterRuta ruta) {
        try{

            final Ruta newRuta = new Ruta();
            newRuta.setCiudadDestino(ruta.ciudad_destino);
            newRuta.setCiudadOrigen(ruta.ciudad_origen);
            newRuta.setDistancia(ruta.distancia);
            var saveRuta = rutaRepository.save(newRuta);
            return ResponseEntity.ok(saveRuta);
        }
        catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
