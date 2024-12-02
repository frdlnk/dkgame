package vista.admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import vista.components.Button;

/**
 * Vista para el panel de administracion
 */
public class AdminPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private Button btnAgregar;
	private Button btnBuscar;
	private Button btnActualizar;
	private Button btnEliminar;
	private Button btnConfiguraciones;

	/**
	 * Create the frame.
	 */
	public AdminPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 325);
		contentPane = new JPanel();
		contentPane.setBounds(100, 100, 450, 300);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		initComponents();
	}

	private void initComponents() {
		contentPane.setLayout(null);

		lblTitulo = new JLabel("Panel de Administracion");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(140, 11, 144, 14);
		contentPane.add(lblTitulo);

		btnAgregar = new Button("Agregar");
		btnAgregar.setBounds(82, 45, 94, 35);
		contentPane.add(btnAgregar);

		btnBuscar = new Button("Buscar");
		btnBuscar.setBounds(258, 63, 94, 35);
		contentPane.add(btnBuscar);

		btnActualizar = new Button("Actualizar");
		btnActualizar.setBounds(82, 125, 94, 35);
		contentPane.add(btnActualizar);

		btnEliminar = new Button("Eliminar");
		btnEliminar.setBounds(258, 161, 94, 35);
		contentPane.add(btnEliminar);

		btnConfiguraciones = new Button("Configuraciones");
		btnConfiguraciones.setBounds(82, 205, 94, 35);
		contentPane.add(btnConfiguraciones);
	}

	public Button getBtnConfiguraciones() {
		return btnConfiguraciones;
	}

	public void setBtnConfiguraciones(Button btnConfiguraciones) {
		this.btnConfiguraciones = btnConfiguraciones;
	}

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	public Button getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(Button btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public Button getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(Button btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public Button getBtnActualizar() {
		return btnActualizar;
	}

	public void setBtnActualizar(Button btnActualizar) {
		this.btnActualizar = btnActualizar;
	}

	public Button getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(Button btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	@Override
	public String toString() {
		return "AdminPanel [contentPane=" + contentPane + ", lblTitulo=" + lblTitulo + ", btnAgregar=" + btnAgregar
				+ ", btnBuscar=" + btnBuscar + ", btnActualizar=" + btnActualizar + ", btnEliminar=" + btnEliminar
				+ ", btnConfiguraciones=" + btnConfiguraciones + "]";
	}

}
