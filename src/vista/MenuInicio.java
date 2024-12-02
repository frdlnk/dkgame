package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MenuInicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnAdministracion;
	private JButton btnJugar;
	private JLabel lblBienvenida;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MenuInicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBounds(100, 100, 450, 300);
		setContentPane(contentPane);

		initComponents();

	}

	private void initComponents() {
		contentPane.setLayout(null);
		btnAdministracion = new JButton("Sistema administracion");
		btnAdministracion.setBounds(44, 145, 176, 23);
		getContentPane().add(btnAdministracion);

		btnJugar = new JButton("Jugar");
		btnJugar.setBounds(290, 145, 88, 23);
		getContentPane().add(btnJugar);

		lblBienvenida = new JLabel("Bienvenido a Metal Slug, que desea hacer");
		lblBienvenida.setBounds(115, 67, 234, 14);
		getContentPane().add(lblBienvenida);

	}

	public JButton getBtnAdministracion() {
		return btnAdministracion;
	}

	public void setBtnAdministracion(JButton btnAdministracion) {
		this.btnAdministracion = btnAdministracion;
	}

	public JButton getBtnJugar() {
		return btnJugar;
	}

	public void setBtnJugar(JButton btnJugar) {
		this.btnJugar = btnJugar;
	}

	public JLabel getLblBienvenida() {
		return lblBienvenida;
	}

	public void setLblBienvenida(JLabel lblBienvenida) {
		this.lblBienvenida = lblBienvenida;
	}

	@Override
	public String toString() {
		return "MenuInicio [btnAdministracion=" + btnAdministracion + ", btnJugar=" + btnJugar + ", lblBienvenida="
				+ lblBienvenida + ", contentPane=" + contentPane + "]";
	}

}
