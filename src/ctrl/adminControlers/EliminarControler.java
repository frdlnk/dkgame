package ctrl.adminControlers;

import java.awt.event.ActionEvent;

import modelo.Usuario;
import modelo.Dao.IDAOUserConfigs;
import modelo.Dao.IDAOUsuario;
import vista.admin.VistaEliminar;

/**
 * Controlador encargado de la eliminacion de usuarios
 * 
 * @see BuscarControler
 * @see VistaEliminar
 */
public class EliminarControler extends BuscarControler{
	private VistaEliminar vistaEliminar;
	private IDAOUserConfigs modeloConfigs;
	
	/**
	 * Construye un nuevo controlador asociado a la vista especificada
	 * <br>y con el DAO especificado 
	 * @param vista Vista asociada al controlador
	 * @param modelo DAO de acceso a los usuarios
	 */
	public EliminarControler(VistaEliminar vista, IDAOUsuario modelo, IDAOUserConfigs modeloConfigs) {
		super(vista, modelo);
		vistaEliminar = vista;
		this.modeloConfigs = modeloConfigs;
		
		vista.getBtnDetalles().addActionListener(this);
		vista.getBtnDelete().addActionListener(this);
		vista.getBtnCancelar().addActionListener(this);
		
		vista.setVisible(true);
	}
	
	/**
	 * Cambia a la pantalla de busqueda
	 */
	private void volverABuscar() {
		vistaEliminar.changeToBuscarPanel();
	}

	/**
	 * Elimina el usuarios selecionado
	 */
	private void eliminarUsuario() {
		Usuario selectedUser = vistaEliminar.getListaUsuarios().getSelectedValue();
		modeloUsuario.delete(selectedUser);
		modeloConfigs.delete(selectedUser.getConfigId());
		vistaEliminar.dispose();
	}

	/**
	 * Muestra los detalles del usuario seleccionado
	 */
	private void mostrarDetalles() {
		Usuario selectedUser = vistaEliminar.getListaUsuarios().getSelectedValue();
		if (selectedUser != null) {
			vistaEliminar.getTfUsername().setText(selectedUser.getUsername());
			vistaEliminar.getTfNivel().setText(String.valueOf(selectedUser.getLevel()));
			vistaEliminar.getTfPassword().setText(selectedUser.getPassword());
			vistaEliminar.getTfPuntaje().setText(String.valueOf(selectedUser.getScore()));
			vistaEliminar.changeToDeletePanel();
		}
	}

	/**
	 * Asignacion de funciones a los botones
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(vistaEliminar.getBtnDetalles())) {
			mostrarDetalles();
		}else if(source.equals(vistaEliminar.getBtnDelete())) {
			eliminarUsuario();
		}else if(source.equals(vistaEliminar.getBtnCancelar())) {
			volverABuscar();
		}
	}

	public VistaEliminar getVistaEliminar() {
		return vistaEliminar;
	}

	public void setVistaEliminar(VistaEliminar vistaEliminar) {
		this.vistaEliminar = vistaEliminar;
	}

	public IDAOUserConfigs getModeloConfigs() {
		return modeloConfigs;
	}

	public void setModeloConfigs(IDAOUserConfigs modeloConfigs) {
		this.modeloConfigs = modeloConfigs;
	}

	@Override
	public String toString() {
		return "EliminarControler [vistaEliminar=" + vistaEliminar + ", modeloConfigs=" + modeloConfigs + "]";
	}

	
}
