package modelo;
public class Sudadera extends Prenda {
    public Sudadera(String nombre, String talla) {
        super(nombre, talla, 48000);
    }
    @Override
    public void mostrarDetalles() {
        System.out.println("Tipo: Sudadera");
    }
}
