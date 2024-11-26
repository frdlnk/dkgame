package ctrl.adminControlers;

import modelo.Usuario;
import modelo.Dao.IDAOUsuario;
import vista.admin.VistaDetalles;

public class DetallesControler extends BuscarControler {
	private VistaDetalles vistaDetails;

	public DetallesControler(VistaDetalles vista, IDAOUsuario modelo) {
		super(vista, modelo);
		vistaDetails = vista;
		vista.getBtnDetalles().addActionListener(e -> mostrarDetalles());
		vista.getBtnVolver().addActionListener(e -> mostrarBusqueda());
		vista.setVisible(true);
	}

	private void mostrarBusqueda() {
		vistaDetails.changeToBuscarPanel();
	}

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

}
