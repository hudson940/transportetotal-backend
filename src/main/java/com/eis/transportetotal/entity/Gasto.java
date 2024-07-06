package com.eis.transportetotal.entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gastos")
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;




    @ManyToMany
    @JoinTable(
            name = "viajes_gastos",
            joinColumns = @JoinColumn(name = "viaje_id"),
            inverseJoinColumns = @JoinColumn(name = "gasto_id")
    )
    private List<Viaje> viajes = new ArrayList<>();



    public void setViajes(Viaje viaje) {
        this.viajes.add(viaje);
    }

    private double monto;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
