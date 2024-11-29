package vista.admin;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;

import vista.components.Button;

import javax.swing.border.LineBorder;

import utils.constants.Colors;


public class VistaCrear extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfUsername;
	private JTextField tfPassword;
	private Button btnSave;
	private JLabel lblPassword;
	private JLabel lblUsername;
	private Button btnCancel;
	private JLabel lblUsernameError;
	private JLabel lblPasswordError;

	/**
	 * Create the dialog.
	 */
	public VistaCrear(JFrame frame) {
		super(frame, true);
		initComponents();
	}
	public VistaCrear(JDialog dialog) {
		super(dialog, true);
		initComponents();
	}

	private void initComponents() {
		setBounds(100, 100, 450, 189);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground(Colors.BACKGROUND_COLOR);

		lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 20, 52, 20);
		contentPanel.add(lblUsername);

		tfUsername = new JTextField();
		tfUsername.setBounds(72, 20, 352, 20);
		contentPanel.add(tfUsername);
		tfUsername.setColumns(10);
		lblUsername.setLabelFor(tfUsername);

		lblPassword = new JLabel("Contrasena: ");
		lblPassword.setBounds(10, 55, 74, 32);
		contentPanel.add(lblPassword);

		tfPassword = new JTextField();
		tfPassword.setBounds(72, 61, 352, 20);
		contentPanel.add(tfPassword);
		tfPassword.setColumns(10);
		lblPassword.setLabelFor(tfPassword);
		
		btnCancel = new Button("Cancelar");
		btnCancel.setBounds(10, 112, 89, 23);
		contentPanel.add(btnCancel);
		btnCancel.setBackground(Colors.BACKGROUND_COLOR);
		btnCancel.setBorder(new LineBorder(Colors.COLOR_GRAY));
		btnCancel.setForeground(Colors.COLOR_GRAY);
		
		btnSave = new Button("Guardar");
		btnSave.setBounds(334, 112, 90, 23);
		btnSave.setBorder(null);
		btnSave.setForeground(Color.WHITE);
		btnSave.setBackground(Colors.COLOR2);
		contentPanel.add(btnSave);
		
		lblUsernameError = new JLabel("");
		lblUsernameError.setLabelFor(lblUsername);
		lblUsernameError.setForeground(Color.RED);
		lblUsernameError.setBounds(72, 38, 352, 14);
		lblUsernameError.setVisible(false);
		contentPanel.add(lblUsernameError);
		
		lblPasswordError = new JLabel("");
		lblPasswordError.setBounds(72, 81, 352, 32);
		lblPasswordError.setForeground(Color.RED);
		lblPasswordError.setVisible(false);;
		contentPanel.add(lblPasswordError);
		
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

	public Button getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(Button btnSave) {
		this.btnSave = btnSave;
	}

	public JLabel getLblPassword() {
		return lblPassword;
	}

	public void setLblPassword(JLabel lblPassword) {
		this.lblPassword = lblPassword;
	}

	public JLabel getLblUsername() {
		return lblUsername;
	}

	public void setLblUsername(JLabel lblUsername) {
		this.lblUsername = lblUsername;
	}

	public Button getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(Button btnCancel) {
		this.btnCancel = btnCancel;
	}

	public JLabel getLblUsernameError() {
		return lblUsernameError;
	}

	public void setLblUsernameError(JLabel lblUsernameError) {
		this.lblUsernameError = lblUsernameError;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JLabel getLblPasswordError() {
		return lblPasswordError;
	}

	public void setLblPasswordError(JLabel lblPasswordError) {
		this.lblPasswordError = lblPasswordError;
	}
}
