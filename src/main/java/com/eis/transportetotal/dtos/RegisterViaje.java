package com.eis.transportetotal.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

public class RegisterViaje {
    public Double distancia_recorrida;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    public LocalDate fecha;
    public Long id_ruta;
    public Long id_vehiculo;

    public RegisterViaje(Double distancia_recorrida, LocalDate fecha, Long id_ruta, Long id_vehiculo) {
        this.distancia_recorrida = distancia_recorrida;
        this.fecha = fecha;
        this.id_ruta = id_ruta;
        this.id_vehiculo = id_vehiculo;
    }
}