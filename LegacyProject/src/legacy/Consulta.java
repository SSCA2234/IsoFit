
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;

// Clase Consulta
class Consulta {
    private String contenido;
    private String remitente;

    public Consulta(String contenido, String remitente) {
        this.contenido = contenido;
        this.remitente = remitente;
    }

    public String getContenido() {
        return contenido;
    }

    public String getRemitente() {
        return remitente;
    }
}

