import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TecladoInteractivo extends JPanel {
    private JButton[] botonesTeclado;
    private boolean mayusculasActivadas;
    private boolean ocultarLetras;
    private JTextField campoEntrada;
    private PanelRetoCitas panelReto;

    public TecladoInteractivo(JTextField campoEntrada, PanelRetoCitas panelReto) {
        this.campoEntrada = campoEntrada;
        this.panelReto = panelReto;
        setLayout(new GridLayout(4, 10));
        inicializarTeclado();
    }

    private void inicializarTeclado() {
        String[] letras = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
                           "A", "S", "D", "F", "G", "H", "J", "K", "L", ";",
                           "Z", "X", "C", "V", "B", "N", "M", "Ñ", ",", ".", "/",
                           "Caps", "Space", "Enter", "Borrar", " ", " ", " "};

        botonesTeclado = new JButton[letras.length];

        for (int i = 0; i < letras.length; i++) {
            botonesTeclado[i] = new JButton(letras[i]);
            final int index = i;
            botonesTeclado[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String tecla = letras[index];
                    procesarTecla(tecla);
                }
            });
            add(botonesTeclado[i]);
        }
    }

    private void procesarTecla(String tecla) {
        switch (tecla) {
            case "Caps":
                mayusculasActivadas = !mayusculasActivadas;
                break;
            case "Space":
                tecla = " ";
                break;
            case "Enter":

                break;
            case "Borrar":
                tecla = "Borrar";
                break;
            default:
                tecla = mayusculasActivadas ? tecla.toUpperCase() : tecla.toLowerCase();
                break;
        }

        boolean resultado = panelReto.procesarCaracter(tecla);
        if (resultado && !ocultarLetras) {
            ocultarLetras = true;
            ocultarLetrasTeclado();
        }
    }

    private void ocultarLetrasTeclado() {
        for (JButton boton : botonesTeclado) {
            if (!boton.getText().equals("Caps") && !boton.getText().equals("Space") && !boton.getText().equals("Enter") && !boton.getText().equals("Borrar")) {
                boton.setText(" ");
            }
        }
    }
}

