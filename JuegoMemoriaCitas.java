import javax.swing.SwingUtilities;

public class JuegoMemoriaCitas {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PanelRetoCitas().setVisible(true);
            }
        });
    }
}

