package com.eis.transportetotal.dtos;

public class RegisterGasto {

  public Double monto;
  public String nombre;

  public RegisterGasto(Double monto, String nombre, Long id_viaje) {
    this.monto = monto;
    this.nombre = nombre;
    this.id_viaje = id_viaje;
  }

  public Long id_viaje;

}
