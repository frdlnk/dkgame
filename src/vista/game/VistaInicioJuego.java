package vista.game;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import vista.components.Button;

public class VistaInicioJuego extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblBienvenida;
	private JList<String> listPuntajes;
	private JLabel lblPuntajes;
	private Button btnRegistrar;
	private Button btnIniciarJuego;
	private Button btnSalir;

	/**
	 * Create the frame.
	 */
	public VistaInicioJuego() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 361, 255);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		initComponents();
	}

	private void initComponents() {
		lblBienvenida = new JLabel("Bienvenido a Metal Slug");
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenida.setBounds(10, 11, 165, 14);
		contentPane.add(lblBienvenida);

		listPuntajes = new JList<>();
		listPuntajes.setBounds(210, 40, 99, 164);
		contentPane.add(listPuntajes);

		lblPuntajes = new JLabel("Mejores Puntajes");
		lblPuntajes.setBounds(210, 11, 99, 14);
		contentPane.add(lblPuntajes);

		btnRegistrar = new Button("Registrarse");
		btnRegistrar.setBounds(49, 100, 87, 31);
		contentPane.add(btnRegistrar);

		btnIniciarJuego = new Button("Iniciar Juego");
		btnIniciarJuego.setBounds(49, 58, 87, 31);
		contentPane.add(btnIniciarJuego);

		btnSalir = new Button("Salir");
		btnSalir.setBounds(49, 142, 87, 31);
		contentPane.add(btnSalir);

	}

	public JLabel getLblBienvenida() {
		return lblBienvenida;
	}

	public void setLblBienvenida(JLabel lblBienvenida) {
		this.lblBienvenida = lblBienvenida;
	}

	public JList<String> getListPuntajes() {
		return listPuntajes;
	}

	public void setListPuntajes(JList<String> listPuntajes) {
		this.listPuntajes = listPuntajes;
	}

	public JLabel getLblPuntajes() {
		return lblPuntajes;
	}

	public void setLblPuntajes(JLabel lblPuntajes) {
		this.lblPuntajes = lblPuntajes;
	}

	public Button getBtnRegistrar() {
		return btnRegistrar;
	}

	public void setBtnRegistrar(Button btnRegistrar) {
		this.btnRegistrar = btnRegistrar;
	}

	public Button getBtnIniciarJuego() {
		return btnIniciarJuego;
	}

	public void setBtnIniciarJuego(Button btnIniciarJuego) {
		this.btnIniciarJuego = btnIniciarJuego;
	}

	public Button getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(Button btnSalir) {
		this.btnSalir = btnSalir;
	}

	@Override
	public String toString() {
		return "VistaInicioJuego [contentPane=" + contentPane + ", lblBienvenida=" + lblBienvenida + ", listPuntajes="
				+ listPuntajes + ", lblPuntajes=" + lblPuntajes + ", btnRegistrar=" + btnRegistrar
				+ ", btnIniciarJuego=" + btnIniciarJuego + ", btnSalir=" + btnSalir + "]";
	}

}
