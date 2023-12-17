package legacy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameNuevoUsuario extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldLogin;
    private JTextField textFieldPassword;
    private JComboBox<String> comboBoxEspecialista;
    private JTextPane textPane;
/*Crea un nuevo usuario en nuestra base de datos a partir de un correro, una contraseña y si es especialista o no*/
    public JFrameNuevoUsuario() {
        setTitle("Dar de alta a un nuevo usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 420, 285);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblLogin = new JLabel("Contraseña:");
        lblLogin.setBounds(6, 81, 69, 16);
        contentPane.add(lblLogin);

        JLabel label = new JLabel("Gmail:");
        label.setBounds(6, 37, 69, 16);
        contentPane.add(label);

        textFieldLogin = new JTextField();
        textFieldLogin.setBounds(87, 31, 134, 28);
        contentPane.add(textFieldLogin);
        textFieldLogin.setColumns(10);

        textFieldPassword = new JTextField();
        textFieldPassword.setColumns(10);
        textFieldPassword.setBounds(87, 75, 134, 28);
        contentPane.add(textFieldPassword);

        JLabel labelEspecialista = new JLabel("Especialista:");
        labelEspecialista.setBounds(6, 121, 80, 16);
        contentPane.add(labelEspecialista);

        // Usar un JComboBox para el campo de especialista
        comboBoxEspecialista = new JComboBox<>(new String[]{"No", "Sí"});
        comboBoxEspecialista.setBounds(87, 117, 134, 28);
        contentPane.add(comboBoxEspecialista);

        JButton btnAltaUsuario = new JButton("Alta usuario");
        btnAltaUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                boolean insertado = false;
                try {
                    // Obtener el valor seleccionado del JComboBox
                    boolean esEspecialista = comboBoxEspecialista.getSelectedItem().equals("Sí");
                    // Crear un nuevo usuario con el constructor adicional
                    Usuario u = new Usuario(textFieldLogin.getText(), textFieldPassword.getText(), esEspecialista);
                    // Llamar al método insert() para insertar el usuario en la base de datos
                    if (u.insert() == 1)
                        insertado = true;

                    if (insertado) {
                        textPane.setText("Usuario creado correctamente");
                    } else {
                        textPane.setText("No se ha podido insertar el usuario");
                    }

                } catch (Exception e) {
                    textPane.setText("No se ha podido crear el usuario. Tal vez ya existe?");
                    e.printStackTrace();
                }
            }
        });
        btnAltaUsuario.setBounds(253, 118, 117, 29);
        contentPane.add(btnAltaUsuario);

        JLabel label_1 = new JLabel("Estado");
        label_1.setForeground(Color.RED);
        label_1.setBounds(6, 168, 61, 16);
        contentPane.add(label_1);

        textPane = new JTextPane();
        textPane.setToolTipText("Terminal");
        textPane.setEditable(false);
        textPane.setBounds(6, 196, 407, 79);
        contentPane.add(textPane);
    }
}

		
		textPane = new JTextPane();
		textPane.setToolTipText("Panel para mostrar el restultado de la comprobaci\u00F3n de login o las excepciones lanzadas");
		textPane.setEditable(false);
		textPane.setBounds(6, 154, 407, 102);
		contentPane.add(textPane);
	}
}
