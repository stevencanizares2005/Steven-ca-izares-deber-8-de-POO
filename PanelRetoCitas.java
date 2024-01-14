import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelRetoCitas extends JFrame {
    private JTextField campoEntrada;
    private JButton botonIniciar, botonVerificar;
    private JLabel etiquetaCita, etiquetaEstado;
    private ControladorReto controlador;
    private TecladoInteractivo teclado;

    public PanelRetoCitas() {
        controlador = new ControladorReto();
        configurarComponentes();
    }

    private void configurarComponentes() {
        setTitle("Reto de Recordatorio de Citas");
        setSize(800, 300);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        campoEntrada = new JTextField(20);
        botonIniciar = new JButton("Iniciar Reto");
        botonVerificar = new JButton("Verificar");
        etiquetaCita = new JLabel("Presiona 'Iniciar Reto' para comenzar");
        etiquetaEstado = new JLabel("");

        botonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cita = controlador.iniciarNuevoReto();
                etiquetaCita.setText(cita);
                campoEntrada.setText("");
                campoEntrada.requestFocusInWindow();
            }
        });

        botonVerificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String intento = campoEntrada.getText();
                boolean correcto = intento.equalsIgnoreCase(controlador.getCitaActual());
                etiquetaEstado.setText(correcto ? "¡Correcto!" : "Incorrecto. Intenta de nuevo.");
                if (correcto) {
                    controlador.mostrarInformeFinal();
                }
            }
        });

        teclado = new TecladoInteractivo(campoEntrada, this);

        add(botonIniciar);
        add(etiquetaCita);
        add(campoEntrada);
        add(botonVerificar);
        add(etiquetaEstado);
        add(teclado);

        setVisible(true); // Agregar esta línea para hacer visible la ventana
    }

    public boolean procesarCaracter(String caracter) {
        if (caracter.equals("Borrar") && campoEntrada.getText().length() > 0) {
            campoEntrada.setText(campoEntrada.getText().substring(0, campoEntrada.getText().length() - 1));
            controlador.retrocederPosicion();
            return true;
        } else if (caracter.length() == 1 && controlador.verificarCaracter(caracter.charAt(0))) {
            campoEntrada.setText(campoEntrada.getText() + caracter);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        new PanelRetoCitas(); // Para iniciar la aplicación
    }
}

