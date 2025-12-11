package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Prenda {
    private String nombre;
    private String talla;
    private double costoProduccion; // Fijo por hija
    private double precioVenta; // Ingresado por usuario
    private List<Estampado> estampadosSeleccionados; // Lista para múltiples estampados

    // Constructor
    public Prenda(String nombre, String talla, double costoProduccion) {
        this.nombre = nombre;
        this.talla = talla;
        this.costoProduccion = costoProduccion;
        this.estampadosSeleccionados = new ArrayList<>();
    }

    // Getters y Setters (Encapsulación)
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTalla() { return talla; }
    public void setTalla(String talla) { this.talla = talla; }

    public double getCostoProduccion() { return costoProduccion; }

    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }

    public List<Estampado> getEstampadosSeleccionados() { return estampadosSeleccionados; }
    public void agregarEstampado(Estampado estampado) { this.estampadosSeleccionados.add(estampado); }

    // Método para calcular costo total de estampados
    public double getCostoTotalEstampados() {
        return estampadosSeleccionados.stream().mapToDouble(Estampado::getCosto).sum();
    }

    // Método para calcular costo total producción (base + estampados)
    public double getCostoTotalProduccion() {
        return costoProduccion + getCostoTotalEstampados();
    }

    // Método polimórfico para calcular ganancia (precioVenta - costoTotal)
    public double calcularGanancia() {
        return precioVenta - getCostoTotalProduccion();
    }

    // Método abstracto para que hijas lo implementen si es necesario
    public abstract void mostrarDetalles();
}