package ctrl.adminControlers;

import modelo.Usuario;
import modelo.Dao.IDAOUsuario;
import vista.admin.VistaEliminar;

public class EliminarControler extends BuscarControler {
	VistaEliminar vistaEliminar;
	
	public EliminarControler(VistaEliminar vista, IDAOUsuario modelo) {
		super(vista, modelo);
		vistaEliminar = vista;
		
		vista.getBtnDetalles().addActionListener(e -> mostrarDetalles());
		vista.getBtnSave().addActionListener(e -> eliminarUsuario());
		
		vista.setVisible(true);
	}

	private void eliminarUsuario() {
		Usuario selectedUser = vistaEliminar.getListaUsuarios().getSelectedValue();
		modelo.delete(selectedUser);
		vistaEliminar.dispose();
	}

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

}
