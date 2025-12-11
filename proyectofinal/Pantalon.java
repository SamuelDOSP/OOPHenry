
package modelo;
public class Pantalon extends Prenda {
    public Pantalon(String nombre, String talla) {
        super(nombre, talla, 35000);
    }
    @Override
    public void mostrarDetalles() {
        System.out.println("Tipo: Pantalón");
    }
}
