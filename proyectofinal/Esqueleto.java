
package modelo;
public class Esqueleto extends Prenda {
    public Esqueleto(String nombre, String talla) {
        super(nombre, talla, 12000);
    }
    @Override
    public void mostrarDetalles() {
        System.out.println("Tipo: Esqueleto");
    }
}
