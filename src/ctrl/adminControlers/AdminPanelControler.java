package ctrl.adminControlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Dao.IDAOUserConfigs;
import modelo.Dao.IDAOUsuario;
import vista.admin.AdminPanel;
import vista.admin.VistaCrear;
import vista.admin.VistaDetalles;
import vista.admin.VistaEliminar;
import vista.admin.VistaActualizar;
import vista.admin.VistaConfiguracionesUsuario;

/**
 * Controlador encargado del panel de administracion
 * 
 * @see AdminPanel
 */
public class AdminPanelControler implements ActionListener{
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
		vista.getBtnActualizar().addActionListener(this);
		vista.getBtnAgregar().addActionListener(this);
		vista.getBtnBuscar().addActionListener(this);
		vista.getBtnEliminar().addActionListener(this);
		vista.getBtnConfiguraciones().addActionListener(this);
		vista.setVisible(true);
	}
	
	private void openConfiguraciones(){
		VistaConfiguracionesUsuario vistaConf = new VistaConfiguracionesUsuario(vista);
		new ConfiguracionesControler(vistaConf, modeloUsers, modeloConfigs);
	}

	/**
	 * Abre la vista actualizar
	 */
	private void openActualizar() {
		VistaActualizar vistaActualizar = new VistaActualizar(vista);
		new ActualizarControler(vistaActualizar, modeloUsers);
	}
	/**
	 * Abre la vista buscar
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
		new EliminarControler(vistaEliminar, modeloUsers, modeloConfigs);
	}
	/**
	 * Abre la vista agregar
	 */
	private void openAgregar() {
		VistaCrear vistaAgregar = new VistaCrear(vista);
		new CreateControler(vistaAgregar, modeloConfigs, modeloUsers);
	}
	
	
	/** 
	 * Listener para la asignacion de acciones a los botones de la vista
	 * @param e evento generado por los componentes de la vista
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		if(source.equals(vista.getBtnActualizar())){
			openActualizar();
		}else if(source.equals(vista.getBtnAgregar())){
			openAgregar();
		}else if (source.equals(vista.getBtnBuscar())) {
			openBuscar();
		}else if (source.equals(vista.getBtnEliminar())) {
			openEliminar();
		}else if (source.equals(vista.getBtnConfiguraciones())) {
			openConfiguraciones();
		}
	}
}
