package ctrl.adminControlers;

import modelo.Dao.IDAOUserConfigs;
import modelo.Dao.IDAOUsuario;
import modelo.Dao.file.DAO_UserConfig;
import vista.admin.AdminPanel;
import vista.admin.VistaCrear;
import vista.admin.VistaDetalles;
import vista.admin.VistaEliminar;
import vista.admin.VistaActualizar;
import vista.admin.VistaBuscar;

/**
 * Controlador encargado del panel de administracion
 */
public class AdminPanelControler {
	private AdminPanel vista;
	private IDAOUserConfigs modeloConfigs;
	private IDAOUsuario modeloUsers;

	/**
	 * Crea un nuevo controlador para la vista del panel deseada
	 * @param vista  Admin Panel que sera controlado
	 * @param modeloConfigs modelo de datos para las configuraciones del juego
	 * @param modeloUsers modelo de acceso para los usuarios
	 */
	public AdminPanelControler(AdminPanel vista, IDAOUserConfigs modeloConfigs, IDAOUsuario modeloUsers) {
		this.vista = vista;
		this.modeloConfigs = modeloConfigs;
		this.modeloUsers = modeloUsers;
		vista.getBtnActualizar().addActionListener(e -> openActualizar());
		vista.getBtnAgregar().addActionListener(e -> openAgregar());
		vista.getBtnBuscar().addActionListener(e -> openBuscar());
		vista.getBtnEliminar().addActionListener(e -> openEliminar());
		vista.setVisible(true);
	}
	
	/**
	 * Abre la vista actualizar
	 */
	private void openActualizar() {
		VistaActualizar vistaActualizar = new VistaActualizar(vista);
		new ActualizarControler(vistaActualizar, modeloUsers);
	}
	/**Abre la vista buscar
	 * 
	 */
	private void openBuscar() {
		VistaDetalles vistaBuscar = new VistaDetalles(vista);
		new DetallesControler(vistaBuscar, modeloUsers);
	}
	/**
	 * Abre la vista eliminar
	 */
	private void openEliminar() {
		VistaEliminar vistaEliminar = new VistaEliminar(vista);
		new EliminarControler(vistaEliminar, modeloUsers);
	}
	/**
	 * Abre la vista agregar
	 */
	private void openAgregar() {
		VistaCrear vistaAgregar = new VistaCrear(vista);
		new CreateControler(vistaAgregar, modeloConfigs, modeloUsers);
	}
}
