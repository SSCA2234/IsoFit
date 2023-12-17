package legacy;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.JToggleButton;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

/*Pestaña, la cual envia una señál, para añadir una consulta a nuestra base de datos*/
public class JFrameAñadirConsulta extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPasswordField textFieldPassword;
    private JTextPane textPane;
    private JTextField textFieldConsulta;
    private JTextField textFieldLogin;
    private JToggleButton btnMostrarContraseña;

    public JFrameAñadirConsulta() {
        setTitle("Realizar Consulta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 420, 285);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(200, 221, 242)); // Cambié el color del fondo
        setContentPane(contentPane);
        
        contentPane.setLayout(null);

        
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(6, 5, 69, 28);
        contentPane.add(lblEmail);
        textFieldLogin = new JTextField();
        textFieldLogin.setBounds(87, 5, 134, 28);
        contentPane.add(textFieldLogin);

        JLabel lblTexto = new JLabel("Realice su consulta y le responderemos lo antes posible");
        lblTexto.setBounds(20, 240, 400, 28); 
        lblTexto.setFont(new Font("Arial", Font.BOLD, 12)); 
        lblTexto.setForeground(new Color(255, 69, 0)); // cambiamos el color del tecto, modificando los valores del RGB
        contentPane.add(lblTexto);
        
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(6, 40, 69, 28);
        contentPane.add(lblPassword);
        textFieldPassword = new JPasswordField();
        textFieldPassword.setBounds(87, 45, 134, 28);
        contentPane.add(textFieldPassword);

        // Text label and text field for Cuestión
        JLabel label = new JLabel("Cuestión:");
        label.setBounds(6, 80, 69, 16);
        contentPane.add(label);
        textFieldConsulta = new JTextField();
        textFieldConsulta.setColumns(10);
        textFieldConsulta.setBounds(87, 75, 134, 28);
        contentPane.add(textFieldConsulta);

        // Button to send the form
        JButton btnAltaUsuario = new JButton("ENVIAR CONSULTA");
        btnAltaUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String login = textFieldLogin.getText();
                    String password = new String(textFieldPassword.getPassword());

                    Usuario usuario = Usuario.read(login, password);
                    String consulta = getTextFieldConsulta().getText();
                    String respuesta = "";
                    int resultado = usuario.agregarConsulta(consulta, respuesta, login);//Ennviamos la consulta
                    if (resultado > 0) {
                        textPane.setText("Consulta Añadida");
                    } else {
                        textPane.setText("Consulta Añadida");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnAltaUsuario.setBounds(253, 76, 141, 29);
        contentPane.add(btnAltaUsuario);

        // Status label
        JLabel label_1 = new JLabel("Estado");
        label_1.setForeground(Color.RED);
        label_1.setBounds(6, 126, 61, 16);
        contentPane.add(label_1);

        // Terminal text area
        textPane = new JTextPane();
        textPane.setToolTipText("Terminal");
        textPane.setEditable(false);
        textPane.setBounds(6, 154, 407, 102);
        contentPane.add(textPane);
    }

    public JTextField getTextFieldConsulta() {
        return textFieldConsulta;
    }
}

/*Pestaña, la cual envia una señál, para añadir una respuesta a nuestra base de datos*/
    class JFrameResponder_consulta extends JFrame {

        private JPanel contentPane;
        private JTextField textFieldLogin;
        private JPasswordField textFieldPassword;
        private JTextPane textPane;
        private JComboBox<String> comboBoxCuestiones;
        private JTextField textFieldRespuesta;
        private JToggleButton btnMostrarContraseña;


            public JFrameResponder_consulta() {
                    setTitle("Responder Consulta");
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    setBounds(100, 100, 420, 285);

                    contentPane = new JPanel();
                    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                    setContentPane(contentPane);
                    contentPane.setLayout(null);

                    JLabel lblEmail = new JLabel("Email:");
                    lblEmail.setBounds(20, 20, 69, 28);
                    contentPane.add(lblEmail);

                    textFieldLogin = new JTextField();
                    textFieldLogin.setBounds(100, 20, 134, 28);
                    contentPane.add(textFieldLogin);

                    JLabel lblPassword = new JLabel("Contraseña:");
                    lblPassword.setBounds(20, 60, 90, 28);
                    contentPane.add(lblPassword);

                    textFieldPassword = new JPasswordField();
                    textFieldPassword.setBounds(130, 60, 134, 28);
                    contentPane.add(textFieldPassword);

                    btnMostrarContraseña = new JToggleButton("Mostrar");
                    btnMostrarContraseña.setBounds(270, 60, 90, 28);
                    btnMostrarContraseña.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (btnMostrarContraseña.isSelected()) {
                                // Mostrar la contraseña
                                textFieldPassword.setEchoChar((char) 0);
                            } else {
                                // Ocultar la contraseña
                                textFieldPassword.setEchoChar('\u2022');
                            }
                        }
                    });
                    contentPane.add(btnMostrarContraseña);

                    JLabel lblCuestion = new JLabel("Cuestión:");
                    lblCuestion.setBounds(20, 100, 69, 16);
                    contentPane.add(lblCuestion);

                    comboBoxCuestiones = new JComboBox<>();
                    comboBoxCuestiones.setBounds(100, 95, 200, 28);
                    try {
                        String login = textFieldLogin.getText();
                        String password = new String(textFieldPassword.getPassword());

                        // Crear un objeto Usuario con el login y la contraseña de elm usuario en concreto
                        Usuario usuario = new Usuario(login, password);
                        // Llenar el ComboBox con las cuestiones en nuestra base de datos
                        for (int i = 0; i < usuario.obtenerCuestionesSinRespuesta2().size(); i++) {
                            comboBoxCuestiones.addItem(usuario.obtenerCuestionesSinRespuesta2().get(i));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    contentPane.add(comboBoxCuestiones);

                    JLabel lblRespuesta = new JLabel("Respuesta:");
                    lblRespuesta.setBounds(20, 140, 69, 16);
                    contentPane.add(lblRespuesta);

                    textFieldRespuesta = new JTextField();
                    textFieldRespuesta.setBounds(100, 135, 200, 28);
                    contentPane.add(textFieldRespuesta);
                    textFieldRespuesta.setColumns(10);

                    JButton btnEnviar = new JButton("ENVIAR");
                    btnEnviar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            try {
                                String login = textFieldLogin.getText();
                                String password = new String(textFieldPassword.getPassword());
                                Usuario usuario = new Usuario(login, password);
                                String respuesta = textFieldRespuesta.getText();
                                String consulta = (String) comboBoxCuestiones.getSelectedItem();
                                int resultado = usuario.agregarRespuesta(respuesta, consulta,login);
                                if (resultado > 0) {
                                    textPane.setText("Respuesta añadida");
                                } else {
                                    textPane.setText("Respuesta añadida");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();

                                textPane.setText("Ha ocurrido un error: " + e.getMessage());
                            }
                        }
                    });
                    btnEnviar.setBounds(100, 180, 200, 29);
                    contentPane.add(btnEnviar);

                    JLabel lblEstado = new JLabel("Estado");
                    lblEstado.setForeground(Color.RED);
                    lblEstado.setBounds(20, 190, 61, 16);
                    contentPane.add(lblEstado);

                    textPane = new JTextPane();
                    textPane.setToolTipText("Terminal");
                    textPane.setEditable(false);
                    textPane.setBounds(20, 220, 360, 28);
                    contentPane.add(textPane);
                }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrameResponder_consulta frame = new JFrameResponder_consulta();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
}

