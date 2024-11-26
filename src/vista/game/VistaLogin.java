package vista.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import vista.components.Button;
import vista.uiConstants.Colors;

import javax.swing.JSeparator;

public class VistaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfUsername;
	private JTextField tfPassword;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private Button btnLogin;
	private JSeparator separatorButtons;
	private Button btnRegistrarse;
	private JLabel lblError;
	private Button btnVolver;


	/**
	 * Create the dialog.
	 */
	public VistaLogin() {
		setBounds(100, 100, 314, 247);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		initComponents();
	}

	private void initComponents() {
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 39, 85, 14);
		contentPanel.add(lblUsername);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(80, 36, 208, 20);
		contentPanel.add(tfUsername);
		tfUsername.setColumns(10);
		
		lblPassword = new JLabel("Contrasena");
		lblPassword.setBounds(10, 84, 85, 14);
		contentPanel.add(lblPassword);
		
		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(80, 81, 208, 20);
		contentPanel.add(tfPassword);
		
		btnLogin = new Button("Iniciar Sesion");
		btnLogin.setBounds(10, 128, 278, 26);
		contentPanel.add(btnLogin);
		
		separatorButtons = new JSeparator();
		separatorButtons.setBounds(10, 165, 278, 2);
		contentPanel.add(separatorButtons);
		
		btnRegistrarse = new Button("Registrarse");
		btnRegistrarse.setBounds(10, 178, 278, 26);
		contentPanel.add(btnRegistrarse);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setVisible(false);
		lblError.setBounds(10, 95, 278, 14);
		contentPanel.add(lblError);
		
		btnVolver = new Button("<-");
		btnVolver.setBounds(10, 11, 43, 14);
		contentPanel.add(btnVolver);
		
	}
	
	public Button getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(Button btnVolver) {
		this.btnVolver = btnVolver;
	}

	public JLabel getLblError() {
		return lblError;
	}

	public void setLblError(JLabel lblError) {
		this.lblError = lblError;
	}

	public JTextField getTfUsername() {
		return tfUsername;
	}

	public void setTfUsername(JTextField tfUsername) {
		this.tfUsername = tfUsername;
	}

	public JTextField getTfPassword() {
		return tfPassword;
	}

	public void setTfPassword(JTextField tfPassword) {
		this.tfPassword = tfPassword;
	}

	public JLabel getLblUsername() {
		return lblUsername;
	}

	public void setLblUsername(JLabel lblUsername) {
		this.lblUsername = lblUsername;
	}

	public JLabel getLblPassword() {
		return lblPassword;
	}

	public void setLblPassword(JLabel lblPassword) {
		this.lblPassword = lblPassword;
	}

	public Button getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(Button btnLogin) {
		this.btnLogin = btnLogin;
	}

	public JSeparator getSeparatorButtons() {
		return separatorButtons;
	}

	public void setSeparatorButtons(JSeparator separatorButtons) {
		this.separatorButtons = separatorButtons;
	}

	public Button getBtnRegistrarse() {
		return btnRegistrarse;
	}

	public void setBtnRegistrarse(Button btnRegistrarse) {
		this.btnRegistrarse = btnRegistrarse;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}
}
