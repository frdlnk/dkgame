package ctrl.adminControlers;

import java.awt.event.ActionEvent;

import modelo.Usuario;
import modelo.Dao.IDAOUsuario;
import vista.admin.VistaDetalles;

/**
 * Controlador encargado de mostrar los detalles de un usuario
 * 
 * @see BuscarControler
 * @see VistaDetalles
 */
public class DetallesControler extends BuscarControler{
	private VistaDetalles vistaDetails;

	/**
	 * Construye un controlador asociado a la vista especificada
	 * <br> y con el Dao especificado 
	 * @param vista Vista a controlar
	 * @param modelo Dao de acceso a los usuarios
	 */
	public DetallesControler(VistaDetalles vista, IDAOUsuario modelo) {
		super(vista, modelo);
		vistaDetails = vista;
		vista.getBtnDetalles().addActionListener(this);
		vista.getBtnVolver().addActionListener(this);
		vista.setVisible(true);
	}

	/**
	 * Cambia a la pantalla de busqueda
	 */
	private void mostrarBusqueda() {
		vistaDetails.changeToBuscarPanel();
	}

	
	/**
	 * Muestra los detalles del usuario selecionado
	 */
	private void mostrarDetalles() {
		Usuario selectedUser = vistaDetails.getListaUsuarios().getSelectedValue();
		if (selectedUser != null) {
			vistaDetails.getTfUsername().setText(selectedUser.getUsername());
			vistaDetails.getTfNivel().setText(String.valueOf(selectedUser.getLevel()));
			vistaDetails.getTfPassword().setText(selectedUser.getPassword());
			vistaDetails.getTfPuntaje().setText(String.valueOf(selectedUser.getScore()));
			vistaDetails.changeToDetailsPanel();
		}
	}

	/**
	 * Asignacion de funciones a los botones
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(vistaDetails.getBtnDetalles())) {
			mostrarDetalles();
		}else if(source.equals(vistaDetails.getBtnVolver())) {
			mostrarBusqueda();
		}
	}

	public VistaDetalles getVistaDetails() {
		return vistaDetails;
	}

	public void setVistaDetails(VistaDetalles vistaDetails) {
		this.vistaDetails = vistaDetails;
	}

	@Override
	public String toString() {
		return "DetallesControler [vistaDetails=" + vistaDetails + "]";
	}

	
}
