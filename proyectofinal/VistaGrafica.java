package vista;

import modelo.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaGrafica extends JFrame {
    private JComboBox<String> comboPrendas;
    private JTextField campoNombre;
    private JComboBox<String> comboTallas;
    private JComboBox<String> comboEstampados;
    private JList<String> listaEstampadosSeleccionados;
    private DefaultListModel<String> modeloLista;
    private JTextField campoPrecioVenta;
    private JButton btnCrearPrenda;
    private JButton btnAgregarEstampado;
    private JButton btnRegistrarVenta;
    private JButton btnNuevaVenta;
    private JTextArea areaResultado;

    public VistaGrafica() {
        setTitle("Sistema de Ventas de Prendas");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tema oscuro: Colores
        Color fondoOscuro = new Color(30, 30, 30); // Negro/gris oscuro
        Color grisOscuro = new Color(50, 50, 50);
        Color textoBlanco = Color.WHITE;
        Color botonGris = new Color(70, 70, 70);

        getContentPane().setBackground(fondoOscuro);

        // Panel superior: Selección de prenda
        JPanel panelPrenda = new JPanel(new GridLayout(5, 2));
        panelPrenda.setBackground(grisOscuro);

        JLabel lblTipo = new JLabel("Tipo de Prenda:");
        lblTipo.setForeground(textoBlanco);
        panelPrenda.add(lblTipo);
        comboPrendas = new JComboBox<>(new String[]{"Camiseta", "Sudadera", "Pantalón", "Esqueleto"});
        comboPrendas.setBackground(grisOscuro);
        comboPrendas.setForeground(textoBlanco);
        panelPrenda.add(comboPrendas);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(textoBlanco);
        panelPrenda.add(lblNombre);
        campoNombre = new JTextField();
        campoNombre.setBackground(grisOscuro);
        campoNombre.setForeground(textoBlanco);
        panelPrenda.add(campoNombre);

        JLabel lblTalla = new JLabel("Talla:");
        lblTalla.setForeground(textoBlanco);
        panelPrenda.add(lblTalla);
        comboTallas = new JComboBox<>(new String[]{"S", "M", "L", "XL"});
        comboTallas.setBackground(grisOscuro);
        comboTallas.setForeground(textoBlanco);
        panelPrenda.add(comboTallas);

        btnCrearPrenda = new JButton("Crear Prenda");
        btnCrearPrenda.setBackground(botonGris);
        btnCrearPrenda.setForeground(textoBlanco);
        panelPrenda.add(btnCrearPrenda);

        panelPrenda.add(new JLabel()); // Espacio vacío

        add(panelPrenda, BorderLayout.NORTH);

        // Panel central: Estampados
        JPanel panelEstampados = new JPanel(new BorderLayout());
        panelEstampados.setBackground(fondoOscuro);

        JLabel lblEstampado = new JLabel("Selecciona Estampado:");
        lblEstampado.setForeground(textoBlanco);
        panelEstampados.add(lblEstampado, BorderLayout.NORTH);

        comboEstampados = new JComboBox<>(new String[]{
            "Tabloide 28x43 cm - $16.000",
            "Oficio 23x34 cm - $8.000",
            "Carta 28x20 cm - $6.000",
            "Media Carta 20x14 cm - $4.000",
            "Punto Corazón 9x8 cm - $3.000"
        });
        comboEstampados.setBackground(grisOscuro);
        comboEstampados.setForeground(textoBlanco);
        panelEstampados.add(comboEstampados, BorderLayout.WEST);

        btnAgregarEstampado = new JButton("Agregar Estampado");
        btnAgregarEstampado.setBackground(botonGris);
        btnAgregarEstampado.setForeground(textoBlanco);
        panelEstampados.add(btnAgregarEstampado, BorderLayout.CENTER);

        modeloLista = new DefaultListModel<>();
        listaEstampadosSeleccionados = new JList<>(modeloLista);
        listaEstampadosSeleccionados.setBackground(grisOscuro);
        listaEstampadosSeleccionados.setForeground(textoBlanco);
        JScrollPane scrollLista = new JScrollPane(listaEstampadosSeleccionados);
        panelEstampados.add(scrollLista, BorderLayout.EAST);

        add(panelEstampados, BorderLayout.CENTER);

        // Panel inferior: Precio, botones y resultado
        JPanel panelInferior = new JPanel(new FlowLayout());
        panelInferior.setBackground(fondoOscuro);

        JLabel lblPrecio = new JLabel("Precio de Venta:");
        lblPrecio.setForeground(textoBlanco);
        panelInferior.add(lblPrecio);
        campoPrecioVenta = new JTextField(10);
        campoPrecioVenta.setBackground(grisOscuro);
        campoPrecioVenta.setForeground(textoBlanco);
        panelInferior.add(campoPrecioVenta);

        btnRegistrarVenta = new JButton("Registrar Venta");
        btnRegistrarVenta.setBackground(botonGris);
        btnRegistrarVenta.setForeground(textoBlanco);
        panelInferior.add(btnRegistrarVenta);

        btnNuevaVenta = new JButton("Nueva Venta");
        btnNuevaVenta.setBackground(botonGris);
        btnNuevaVenta.setForeground(textoBlanco);
        panelInferior.add(btnNuevaVenta);

        areaResultado = new JTextArea(5, 50);
        areaResultado.setBackground(grisOscuro);
        areaResultado.setForeground(textoBlanco);
        JScrollPane scrollResultado = new JScrollPane(areaResultado);
        panelInferior.add(scrollResultado);

        add(panelInferior, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Getters
    public String getTipoPrendaSeleccionado() { return (String) comboPrendas.getSelectedItem(); }
    public String getNombre() { return campoNombre.getText(); }
    public String getTalla() { return (String) comboTallas.getSelectedItem(); }
    public int getIndiceEstampado() { return comboEstampados.getSelectedIndex(); }
    public double getPrecioVenta() throws NumberFormatException { return Double.parseDouble(campoPrecioVenta.getText()); }

    // Métodos para lista
    public void agregarEstampadoALista(String descripcion) { modeloLista.addElement(descripcion); }
    public void limpiarListaEstampados() { modeloLista.clear(); }
    public void limpiarCampos() {
        campoNombre.setText("");
        campoPrecioVenta.setText("");
        limpiarListaEstampados();
    }

    // Método para crear prenda
    public Prenda crearPrendaDesdeCampos() throws Exception {
        String tipo = getTipoPrendaSeleccionado();
        String nombre = getNombre();
        String talla = getTalla();

        if (nombre.isEmpty()) throw new IllegalArgumentException("Nombre no puede estar vacío");

        switch (tipo) {
            case "Camiseta": return new Camiseta(nombre, talla);
            case "Sudadera": return new Sudadera(nombre, talla);
            case "Pantalón": return new Pantalon(nombre, talla);
            case "Esqueleto": return new Esqueleto(nombre, talla);
            default: throw new IllegalArgumentException("Tipo inválido");
        }
    }

    // Mensajes y resultado
    public void mostrarMensaje(String mensaje) { JOptionPane.showMessageDialog(this, mensaje); }
    public void mostrarResultado(String resultado) { areaResultado.setText(resultado); }

    // Listeners
    public void setListenerCrearPrenda(ActionListener listener) { btnCrearPrenda.addActionListener(listener); }
    public void setListenerAgregarEstampado(ActionListener listener) { btnAgregarEstampado.addActionListener(listener); }
    public void setListenerRegistrarVenta(ActionListener listener) { btnRegistrarVenta.addActionListener(listener); }
    public void setListenerNuevaVenta(ActionListener listener) { btnNuevaVenta.addActionListener(listener); }
}