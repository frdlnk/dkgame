package vista.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import utils.constants.Colors;
import vista.components.Button;


public class VistaEliminar extends VistaBuscar {

	private Rectangle originaBounds;
	private JPanel deletePanel;
	private JLabel lblUsername;
	private JTextField tfUsername;
	private JLabel lblPassword;
	private JTextField tfPassword;
	private Button btnDelete;
	private JLabel lblNivel;
	private JLabel lblPuntaje;
	private Button btnDetalles;
	private JTextField tfNivel;
	private JTextField tfPuntaje;
	private Button btnCancelar;

	public VistaEliminar(JFrame frame) {
		super(frame);
		Dimension lastDimension = getSize();
		Dimension newDimension = new Dimension(lastDimension.width, lastDimension.height+30);
		setSize(newDimension);
		originaBounds = getBounds();
		deletePanel = new JPanel();
		deletePanel.setSize(380,240);
		deletePanel.setLocation(getLocation());
		initComponents();
	}

	private void initComponents() {
		deletePanel.setLayout(null);
		deletePanel.setBackground(Colors.BACKGROUND_COLOR);
		
		btnDetalles = new Button("Detalles");
		btnDetalles.setBounds(450, 412, 78, 19);
		btnDetalles.setBackground(Colors.COLOR6);
		getContentPanel().add(btnDetalles);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 8, 52, 14);
		deletePanel.add(lblUsername);

		tfUsername = new JTextField();
		tfUsername.setEditable(false);
		tfUsername.setBounds(93, 5, 261, 20);
		deletePanel.add(tfUsername);
		tfUsername.setColumns(10);
		lblUsername.setLabelFor(tfUsername);

		lblPassword = new JLabel("Contrasena: ");
		lblPassword.setBounds(10, 45, 63, 14);
		deletePanel.add(lblPassword);

		tfPassword = new JTextField();
		tfPassword.setEditable(false);
		tfPassword.setBounds(93, 42, 261, 20);
		deletePanel.add(tfPassword);
		tfPassword.setColumns(10);
		lblPassword.setLabelFor(tfPassword);
		
		btnDelete = new Button("Eliminar");
		btnDelete.setBounds(291, 164, 63, 26);
		btnDelete.setBorder(null);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(Colors.COLOR_DELETE);
		deletePanel.add(btnDelete);;
		
		lblNivel = new JLabel("Nivel:");
		lblNivel.setBounds(10, 84, 27, 14);
		deletePanel.add(lblNivel);
		
		lblPuntaje = new JLabel("Puntaje:");
		lblPuntaje.setBounds(10, 126, 41, 14);
		deletePanel.add(lblPuntaje);
		
		tfNivel = new JTextField();
		tfNivel.setEditable(false);
		tfNivel.setBounds(93, 81, 261, 20);
		deletePanel.add(tfNivel);
		tfNivel.setColumns(10);
		
		tfPuntaje = new JTextField();
		tfPuntaje.setEditable(false);
		tfPuntaje.setBounds(93, 123, 261, 20);
		deletePanel.add(tfPuntaje);
		tfPuntaje.setColumns(10);
		
		btnCancelar = new Button("Cancelar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBorder(new LineBorder(Colors.COLOR_GRAY));
		btnCancelar.setBackground(null);
		btnCancelar.setForeground(Colors.COLOR_GRAY);
		btnCancelar.setBounds(10, 164, 63, 26);
		deletePanel.add(btnCancelar);
	}
	
	/**
	 * Cambia el panel de contenido al de delete
	 */
	public void changeToDeletePanel() {
		setContentPane(deletePanel);
		setBounds(deletePanel.getBounds());
	}
	
	public void changeToBuscarPanel() {
		setContentPane(getBusquedaPanel());
		setBounds(originaBounds);
	}

	public Rectangle getOriginaBounds() {
		return originaBounds;
	}

	public void setOriginaBounds(Rectangle originaBounds) {
		this.originaBounds = originaBounds;
	}

	public JPanel getDetailsPanel() {
		return deletePanel;
	}

	public void setDetailsPanel(JPanel detailsPanel) {
		this.deletePanel = detailsPanel;
	}

	public JLabel getLblUsername() {
		return lblUsername;
	}

	public void setLblUsername(JLabel lblUsername) {
		this.lblUsername = lblUsername;
	}

	public JTextField getTfUsername() {
		return tfUsername;
	}

	public void setTfUsername(JTextField tfUsername) {
		this.tfUsername = tfUsername;
	}

	public JLabel getLblPassword() {
		return lblPassword;
	}

	public void setLblPassword(JLabel lblPassword) {
		this.lblPassword = lblPassword;
	}

	public JTextField getTfPassword() {
		return tfPassword;
	}

	public void setTfPassword(JTextField tfPassword) {
		this.tfPassword = tfPassword;
	}

	public JLabel getLblNivel() {
		return lblNivel;
	}

	public void setLblNivel(JLabel lblNivel) {
		this.lblNivel = lblNivel;
	}

	public JLabel getLblPuntaje() {
		return lblPuntaje;
	}

	public void setLblPuntaje(JLabel lblPuntaje) {
		this.lblPuntaje = lblPuntaje;
	}

	public Button getBtnDetalles() {
		return btnDetalles;
	}

	public void setBtnDetalles(Button btnDetalles) {
		this.btnDetalles = btnDetalles;
	}

	public JTextField getTfNivel() {
		return tfNivel;
	}

	public void setTfNivel(JTextField tfNivel) {
		this.tfNivel = tfNivel;
	}

	public JTextField getTfPuntaje() {
		return tfPuntaje;
	}

	public void setTfPuntaje(JTextField tfPuntaje) {
		this.tfPuntaje = tfPuntaje;
	}

	public JPanel getDeletePanel() {
		return deletePanel;
	}

	public void setDeletePanel(JPanel deletePanel) {
		this.deletePanel = deletePanel;
	}

	public Button getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(Button btnDelete) {
		this.btnDelete = btnDelete;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}
	
	
}
