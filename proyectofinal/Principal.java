package main;  

import controlador.ControladorVentas;
import vista.VistaGrafica;
public class Principal {
    public static void main(String[] args) {
        VistaGrafica vista = new VistaGrafica();
        new ControladorVentas(vista);  
    }
}