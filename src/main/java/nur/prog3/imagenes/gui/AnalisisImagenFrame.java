package nur.prog3.imagenes.gui;

import nur.prog3.imagenes.objetos.Imagen;
import nur.prog3.imagenes.operaciones.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class AnalisisImagenFrame extends JFrame implements PropertyChangeListener {
    private Imagen modelo;
    private JLabel status;
    private static final Logger logger = LogManager.getRootLogger();
    public AnalisisImagenFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        modelo = new Imagen(600,400);
        modelo.addObserver(this);

        AnalisisImagenPanel panel = new AnalisisImagenPanel(modelo);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel, BorderLayout.CENTER);

        status = new JLabel();
        this.getContentPane().add(status, BorderLayout.SOUTH);

        JMenuBar bar = new JMenuBar();
        this.setJMenuBar(bar);

        // Menu Archivo
        JMenu menu = new JMenu("Archivo");
        bar.add(menu);

        JMenuItem item = new JMenuItem("Cargar imagen");
        menu.add(item);

        item.addActionListener(e -> menuArchivo_CargarImagen());

        menu.addSeparator();

        item = new JMenuItem("Salir");
        item.addActionListener(e -> menuArchivo_Salir());
        menu.add(item);

        // Operaciones
        menu = new JMenu("Operaciones");
        bar.add(menu);

        item = new JMenuItem("Convertir B/N");
        item.addActionListener(e -> menuOperaciones_BN());
        menu.add(item);

        item = new JMenuItem("Convertir Grises");
        item.addActionListener(e -> menuOperaciones_Grises());
        menu.add(item);

        item = new JMenuItem("Dither Floyd Steinberg");
        item.addActionListener(e -> menuOperaciones_FloydSteinberg());
        menu.add(item);

        item = new JMenuItem("Convertir Rojos");
        item.addActionListener(e -> menuOperaciones_Rojos());
        menu.add(item);

        this.pack();
        this.setVisible(true);
    }

    private void menuOperaciones_FloydSteinberg() {
        OperacionImagen op = new FloydSteinberg(modelo);
        op.ejecutar();
    }

    private void menuOperaciones_Rojos() {
        OperacionImagen op = new ConvertirTonosRojo(modelo);
        op.ejecutar();
    }

    private void menuOperaciones_Grises() {
        OperacionImagen op = new ConvertirGris(modelo);
        op.ejecutar();
    }

    private void menuOperaciones_BN() {
        OperacionImagen op = new ConvertirBN(modelo);
        op.ejecutar();
    }

    private void menuArchivo_Salir() {
        logger.info("Salir");
        System.exit(0);
    }

    private void menuArchivo_CargarImagen() {
        JFileChooser chooser = new JFileChooser("E:/Prog3/imgs");
        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this)) {
            File f = chooser.getSelectedFile();
            logger.info("Tratando de abrir archivo " + f.getName());

            this.modelo.cargarImagen(f);

            this.pack();
        }
    }

    public static void main(String[] args) {
        new AnalisisImagenFrame();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("MENSAJE")) {
            status.setText(modelo.getMensaje());
        }
    }
}
