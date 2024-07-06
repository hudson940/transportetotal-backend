package com.eis.transportetotal.dtos;

public class RegisterRuta {

        public RegisterRuta(String ciudad_origen, String ciudad_destino, Double distancia) {
                this.ciudad_origen = ciudad_origen;
                this.ciudad_destino = ciudad_destino;
                this.distancia = distancia;
        }

        public String ciudad_origen;
        public String ciudad_destino;
        public Double distancia;
}
