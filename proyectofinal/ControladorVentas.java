package controlador;

import modelo.*;
import vista.VistaGrafica;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ControladorVentas {
    private VistaGrafica vista;
    private Prenda prendaActual;
    private static final DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ControladorVentas(VistaGrafica vista) {
        this.vista = vista;
        this.prendaActual = null;
        configurarListeners();
    }

    private void configurarListeners() {
        vista.setListenerCrearPrenda(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    prendaActual = vista.crearPrendaDesdeCampos();
                    vista.mostrarMensaje("Prenda creada. Ahora agrega estampados.");
                } catch (Exception ex) {
                    vista.mostrarMensaje("Error al crear prenda: " + ex.getMessage());
                }
            }
        });

        vista.setListenerAgregarEstampado(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (prendaActual == null) {
                        vista.mostrarMensaje("Primero crea una prenda.");
                        return;
                    }
                    int indice = vista.getIndiceEstampado();
                    Estampado estampado = Estampado.values()[indice];
                    prendaActual.agregarEstampado(estampado);
                    vista.agregarEstampadoALista(estampado.getDescripcion());
                } catch (Exception ex) {
                    vista.mostrarMensaje("Error al agregar estampado: " + ex.getMessage());
                }
            }
        });

        vista.setListenerRegistrarVenta(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (prendaActual == null) {
                        vista.mostrarMensaje("Crea una prenda primero.");
                        return;
                    }
                    double precio = vista.getPrecioVenta();
                    if (precio <= 0) throw new IllegalArgumentException("Precio debe ser positivo");
                    prendaActual.setPrecioVenta(precio);
                    registrarVenta(prendaActual);  // Llama al método de registro
                    vista.mostrarMensaje("Venta registrada exitosamente.");
                    vista.mostrarResultado("Ganancia: " + prendaActual.calcularGanancia());
                } catch (Exception ex) {
                    vista.mostrarMensaje("Error: " + ex.getMessage());
                }
            }
        });

        vista.setListenerNuevaVenta(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prendaActual = null;
                vista.limpiarCampos();
                vista.mostrarMensaje("Nueva venta iniciada.");
            }
        });
    }

    private void registrarVenta(Prenda prenda) {
        String fecha = LocalDateTime.now().format(formateador);
        double costoTotal = prenda.getCostoTotalProduccion();
        double ganancia = prenda.calcularGanancia();
        String estampados = prenda.getEstampadosSeleccionados().toString();  // Lista de estampados seleccionados

        String recibo = String.format(
            "Fecha: %s\nTipo: %s\nNombre: %s\nTalla: %s\nEstampados: %s\nCosto Producción Base: %.0f\nCosto Total Estampados: %.0f\nCosto Total: %.0f\nPrecio Venta: %.0f\nGanancia: %.0f\n---\n",
            fecha, prenda.getClass().getSimpleName(), prenda.getNombre(), prenda.getTalla(),
            estampados, prenda.getCostoProduccion(), prenda.getCostoTotalEstampados(), costoTotal, prenda.getPrecioVenta(), ganancia
        );

        try (FileWriter escritor = new FileWriter("ventas.txt", true)) {  // Append para consistencia
            escritor.write(recibo);
        } catch (IOException e) {
            vista.mostrarMensaje("Error al guardar en archivo: " + e.getMessage());
        }
    }
}