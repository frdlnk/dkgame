package vista.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

import utils.constants.Colors;
import vista.components.Button;

import java.awt.FlowLayout;

public class VistaDetalles extends VistaBuscar {

	private Rectangle busquedaBounds;
	private JPanel detailsPanel;
	private JLabel lblUsername;
	private JTextField tfUsername;
	private JLabel lblPassword;
	private JTextField tfPassword;
	private Button btnVolver;
	private JLabel lblNivel;
	private JLabel lblPuntaje;
	private Button btnDetalles;
	private JTextField tfNivel;
	private JTextField tfPuntaje;
	private Rectangle detailsBounds;

	public VistaDetalles(JFrame frame) {
		super(frame);
		Dimension lastDimension = getSize();
		Dimension newDimension = new Dimension(lastDimension.width, lastDimension.height+30);
		setSize(newDimension);
		busquedaBounds = getBounds();
		detailsPanel = new JPanel();
		detailsPanel.setSize(380,240);
		detailsPanel.setLocation(getLocation());
		detailsBounds = detailsPanel.getBounds();
		initComponents();
	}

	private void initComponents() {
		detailsPanel.setLayout(null);
		
		btnDetalles = new Button("Detalles");
		btnDetalles.setBounds(450, 412, 78, 19);
		btnDetalles.setBackground(Colors.COLOR6);
		getContentPanel().add(btnDetalles);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 8, 52, 14);
		detailsPanel.add(lblUsername);

		tfUsername = new JTextField();
		tfUsername.setEditable(false);
		tfUsername.setBounds(93, 5, 261, 20);
		detailsPanel.add(tfUsername);
		tfUsername.setColumns(10);
		lblUsername.setLabelFor(tfUsername);

		lblPassword = new JLabel("Contrasena: ");
		lblPassword.setBounds(10, 45, 63, 14);
		detailsPanel.add(lblPassword);

		tfPassword = new JTextField();
		tfPassword.setEditable(false);
		tfPassword.setBounds(93, 42, 261, 20);
		detailsPanel.add(tfPassword);
		tfPassword.setColumns(10);
		lblPassword.setLabelFor(tfPassword);
		
		btnVolver = new Button("Volver");
		btnVolver.setBounds(291, 164, 63, 26);
		btnVolver.setBorder(null);
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setBackground(Colors.COLOR2);
		detailsPanel.add(btnVolver);;
		
		lblNivel = new JLabel("Nivel:");
		lblNivel.setBounds(10, 84, 27, 14);
		detailsPanel.add(lblNivel);
		
		lblPuntaje = new JLabel("Puntaje:");
		lblPuntaje.setBounds(10, 126, 41, 14);
		detailsPanel.add(lblPuntaje);
		
		tfNivel = new JTextField();
		tfNivel.setEditable(false);
		tfNivel.setBounds(93, 81, 261, 20);
		detailsPanel.add(tfNivel);
		tfNivel.setColumns(10);
		
		tfPuntaje = new JTextField();
		tfPuntaje.setEditable(false);
		tfPuntaje.setBounds(93, 123, 261, 20);
		detailsPanel.add(tfPuntaje);
		tfPuntaje.setColumns(10);
	}
	
	public void changeToDetailsPanel() {
		setContentPane(detailsPanel);
		setBounds(detailsBounds);
	}
	
	public void changeToBuscarPanel() {
		setContentPane(getBusquedaPanel());
		setBounds(busquedaBounds);
	}

	public Rectangle getOriginaBounds() {
		return busquedaBounds;
	}

	public void setOriginaBounds(Rectangle originaBounds) {
		this.busquedaBounds = originaBounds;
	}

	public JPanel getDetailsPanel() {
		return detailsPanel;
	}

	public void setDetailsPanel(JPanel detailsPanel) {
		this.detailsPanel = detailsPanel;
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

	public Button getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(Button btnSave) {
		this.btnVolver = btnSave;
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
	
	
}
