package legacy;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class JFrameLogin extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldLog;
    private JTextField textFieldPass;
    private JTextPane textPaneEstado;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrameLogin frame = new JFrameLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
/*Muestra la pantalla principal con los frames para rellenar y los botones iniciales*/
    	   public JFrameLogin() {
    	        setTitle("ISOFIT READY!!");
    	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	        setBounds(100, 100, 438, 385);
    	        
    	        // Cambia el color del fondo del contentPane
    	        contentPane = new JPanel();
    	        contentPane.setBackground(new Color(255, 255, 204)); // Fondo amarillo claro
    	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	        setContentPane(contentPane);
    	        contentPane.setLayout(null);

    	        JLabel lblIntroduzcaElLogin = new JLabel("Bienvenido a tu aplicación más FITNESS!");
    	        lblIntroduzcaElLogin.setBounds(50, 19, 386, 20);
    	        lblIntroduzcaElLogin.setForeground(new Color(0, 128, 0)); // Color verde
    	        Font font = new Font("Amasis MT Pro Black", Font.BOLD, 16);
    	        lblIntroduzcaElLogin.setFont(font);
    	        contentPane.add(lblIntroduzcaElLogin);

    	        textFieldLog = new JTextField();
    	        textFieldLog.setBounds(86, 68, 134, 28);
    	        contentPane.add(textFieldLog);
    	        textFieldLog.setColumns(10);

    	        JLabel label = new JLabel("Gmail:");
    	        label.setBounds(6, 74, 61, 16);
    	        contentPane.add(label);

    	        JLabel lblLogin = new JLabel("Contraseña:");
    	        lblLogin.setBounds(6, 122, 73, 16);
    	        contentPane.add(lblLogin);

    	        textFieldPass = new JTextField();
    	        textFieldPass.setColumns(10);
    	        textFieldPass.setBounds(86, 116, 134, 28);
    	        contentPane.add(textFieldPass);

    	        JButton buttonAceptar = new JButton("Aceptar");
    	        buttonAceptar.addActionListener(new ActionListener() {
    	            public void actionPerformed(ActionEvent arg0) {
    	                autenticarUsuario();
    	            }
    	        });
    	        buttonAceptar.setBounds(264, 69, 148, 29);
    	        contentPane.add(buttonAceptar);

    	        JLabel lblEstado = new JLabel("Estado");
    	        lblEstado.setForeground(Color.RED);
    	        lblEstado.setBounds(6, 208, 61, 16);
    	        contentPane.add(lblEstado);

    	        textPaneEstado = new JTextPane();
    	        textPaneEstado.setToolTipText("Terminal");
    	        textPaneEstado.setEditable(false);
    	        textPaneEstado.setBounds(6, 235, 406, 102);
    	        contentPane.add(textPaneEstado);

    	        JButton buttonLimpiar = new JButton("Limpiar");
    	        buttonLimpiar.addActionListener(new ActionListener() {
    	            public void actionPerformed(ActionEvent arg0) {
    	                textPaneEstado.setText("");
    	            }
    	        });
    	        buttonLimpiar.setBounds(264, 117, 148, 29);
    	        contentPane.add(buttonLimpiar);

    	        JButton btnNuevoUsuario = new JButton("Nuevo Usuario");
    	        btnNuevoUsuario.addActionListener(new ActionListener() {
    	            public void actionPerformed(ActionEvent arg0) {
    	                JFrameNuevoUsuario frame = new JFrameNuevoUsuario();
    	                frame.setVisible(true);
    	            }
    	        });
    	        btnNuevoUsuario.setBounds(264, 157, 148, 29);
    	        contentPane.add(btnNuevoUsuario);

    	        JButton btnMostrarConsultas = new JButton("Mostrar Consultas");
    	        btnMostrarConsultas.addActionListener(new ActionListener() {
    	            public void actionPerformed(ActionEvent arg0) {
    	                try {
    	                    Usuario usuario = Usuario.read(textFieldLog.getText(), textFieldPass.getText());
    	                    List<String> consultas = usuario.getConsultas();
    	                    textPaneEstado.setText("Las consultas son: " + consultas);
    	                } catch (Exception e) {
    	                    textPaneEstado.setText("No se han podido mostrar las consultas");
    	                    e.printStackTrace();
    	                }
    	            }
    	        });
    	        btnMostrarConsultas.setBounds(264 - 160, 197, 148, 28);
    	        contentPane.add(btnMostrarConsultas);

    	        JButton btnAgregarConsulta = new JButton("Agregar Consulta");
    	        btnAgregarConsulta.addActionListener(new ActionListener() {
    	            public void actionPerformed(ActionEvent e) {
    	                JFrameAñadirConsulta frame = new JFrameAñadirConsulta();
    	                frame.setVisible(true);
    	            }
    	        });
    	        btnAgregarConsulta.setBounds(264, 197, 148, 28);
    	        contentPane.add(btnAgregarConsulta);

    	        JButton btnAgregarRespuesta = new JButton("Agregar Respuesta");
    	        btnAgregarRespuesta.addActionListener(new ActionListener() {
    	            public void actionPerformed(ActionEvent e) {
    	                JFrameResponder_consulta frame = new JFrameResponder_consulta();
    	                frame.setVisible(true);
    	            }
    	        });
    	        btnAgregarRespuesta.setBounds(264 - 160, 237, 148, 28);
    	        contentPane.add(btnAgregarRespuesta);
    	    }
/*Autentica el usuairo, para compribar si existe en la base de datos y si es un especialista relaizar un caso, y si no lo es, otro distinto*/
    private void autenticarUsuario() {
        boolean existe = false;
        try {
            Usuario usuario = Usuario.read(textFieldLog.getText(), textFieldPass.getText());
            if (usuario != null) {
                existe = true;
                textPaneEstado.setText("El login ha sido correcto");
                // Verificar si es especialista
                if (usuario.isEspecialista(usuario.mLogin)) {
                	List<String> consultas = usuario.getConsultas();
                    textPaneEstado.setText("¡Bienvenido Especialista.");
                    textPaneEstado.setText("Las consultas son: " + consultas);
                    JFrameResponder_consulta frame = new JFrameResponder_consulta();
                    frame.setVisible(true);
                    //Si no es espcialista realiza esto, para añadir una consulta, por lo que sera un usuriao vip
                } else {
                    textPaneEstado.setText("¡Bienvenido Cliente Vip!");
                    JFrameAñadirConsulta frame = new JFrameAñadirConsulta();
        	        frame.setVisible(true);
                }

            } else {
                textPaneEstado.setText("El login ha sido incorrecto");
            }
        } catch (Exception e) {
            textPaneEstado.setText("Ha ocurrido un error, vuelva a intentarlo" + e.toString());
        }
    }
}
