package vista.admin;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import vista.components.Button;

import javax.swing.JTextField;

import utils.constants.Colors;

import javax.swing.JLabel;

/**
 * Vista para actualizar usuarios
 */
public class VistaActualizar extends VistaBuscar {

	private static final long serialVersionUID = 1L;
	private final JPanel actualizarPanel = new JPanel();
	private Button btnSelectUser;
	private JTextField tfUsername;
	private JTextField tfPassword;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private Button btnActualizar;
	private Button btnCancelar;
	private Rectangle originaBounds;
	private JLabel lblPasswordError;

	/**
	 * Create the dialog.
	 */
	public VistaActualizar(JFrame frame) {
		super(frame);
		Dimension lastDimension = getSize();
		Dimension newDimension = new Dimension(lastDimension.width, lastDimension.height+30);
		setSize(newDimension);
		originaBounds = getBounds();
		actualizarPanel.setSize(380,180);
		actualizarPanel.setLocation(getLocation());
		initComponents();
	}
	
	public void changeToSearchPanel() {
		setContentPane(getBusquedaPanel());
		setBounds(originaBounds);
	}
	
	private void initComponents() {
		actualizarPanel.setLayout(null);
		
		btnSelectUser = new Button("Actualizar");
		btnSelectUser.setBounds(448, 408, 80, 20);
		btnSelectUser.setBackground(Colors.COLOR6);
		getContentPanel().add(btnSelectUser);
		
		
		tfUsername = new JTextField();
		tfUsername.setBounds(78, 5, 279, 20);
		tfUsername.setToolTipText("");
		tfUsername.setEditable(false);
		actualizarPanel.add(tfUsername);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 8, 48, 14);
		lblUsername.setLabelFor(tfUsername);
		actualizarPanel.add(lblUsername);

		tfPassword = new JTextField();
		tfPassword.setBounds(78, 36, 279, 20);
		actualizarPanel.add(tfPassword);
		
		lblPassword = new JLabel("Contrasena");
		lblPassword.setBounds(10, 40, 75, 14);
		actualizarPanel.add(lblPassword);
		
		btnActualizar = new Button("Actualizar");
		btnActualizar.setBounds(281, 109, 76, 21);
		actualizarPanel.add(btnActualizar);
		
		btnCancelar = new Button("Cancelar");
		btnCancelar.setBounds(10, 109, 62, 21);
		actualizarPanel.add(btnCancelar);
		
		lblPasswordError = new JLabel("");
		lblPasswordError.setBounds(78, 59, 279, 39);
		actualizarPanel.add(lblPasswordError);
		
	}
	
	public void changeToActualizarPanel() {
		setContentPane(actualizarPanel);
		setBounds(actualizarPanel.getBounds());
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

	public Button getBtnActualizar() {
		return btnActualizar;
	}

	public void setBtnActualizar(Button btnActualizar) {
		this.btnActualizar = btnActualizar;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JPanel getActualizarPanel() {
		return actualizarPanel;
	}

	public Button getBtnSelectUser() {
		return btnSelectUser;
	}

	public void setBtnSelectUser(Button btnSelectUser) {
		this.btnSelectUser = btnSelectUser;
	}

	public Rectangle getOriginaBounds() {
		return originaBounds;
	}

	public void setOriginaBounds(Rectangle originaBounds) {
		this.originaBounds = originaBounds;
	}

	public JLabel getLblPasswordError() {
		return lblPasswordError;
	}

	public void setLblPasswordError(JLabel lblPasswordError) {
		this.lblPasswordError = lblPasswordError;
	}
	
	
}
