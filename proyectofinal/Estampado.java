package modelo;

public enum Estampado {
    TABLOIDE("Tabloide 28x43 cm", 16000),
    OFICIO("Oficio 23x34 cm", 8000),
    CARTA("Carta 28x20 cm", 6000),
    MEDIA_CARTA("Media Carta 20x14 cm", 4000),
    PUNTO_CORAZON("Punto Corazón 9x8 cm", 3000);

    private String descripcion;
    private double costo;

    Estampado(String descripcion, double costo) {
        this.descripcion = descripcion;
        this.costo = costo;
    }

    public String getDescripcion() { return descripcion; }
    public double getCosto() { return costo; }
}