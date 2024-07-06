package com.eis.transportetotal.dtos;

public class RegisterVehiculo {

    public String placa;
    public String modelo;
    public String marca;
    public String conductor;

    public RegisterVehiculo(String placa, String modelo, String marca, String conductor) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.conductor = conductor;
    }
}
