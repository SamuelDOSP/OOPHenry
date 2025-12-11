
package modelo;
public class Camiseta extends Prenda {
    public Camiseta(String nombre, String talla) {
        super(nombre, talla, 12000); // Costo fijo
    }
    @Override
    public void mostrarDetalles() {
        System.out.println("Tipo: Camiseta");
    }
}
