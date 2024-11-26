package ctrl.adminControlers;

import modelo.Usuario;
import modelo.Dao.IDAOUsuario;
import vista.admin.VistaActualizar;

/**
 * Clase encargada de controlar la actualizacion de datos del usuario
 */
public class ActualizarControler extends BuscarControler{
	private VistaActualizar vistaActualizar;
	
	/**
	 * Crea un nuevo controlador de actualizar
	 * @param vista vista que se va a controlar
	 * @param modelo Dao de acceso a los datos del usuario
	 */
	public ActualizarControler(VistaActualizar vista, IDAOUsuario modelo) {
		super(vista, modelo);
		vistaActualizar = vista;
		vista.getBtnSelectUser().addActionListener(e -> mostrarInfoUsuario());
		vista.getBtnActualizar().addActionListener(e -> actualizarUsuario());
		vista.setVisible(true);
	}

	/**
	 * Aqui se atualiza el usuario si los datos son correctos
	 */
	private void actualizarUsuario() {
		Usuario user = vistaActualizar.getListaUsuarios().getSelectedValue();
		String newPassword = vistaActualizar.getTfPassword().getText();
		if (validateData(newPassword)) {
			user.setPassword(newPassword);
			modelo.update(user);
			vistaActualizar.changeToSearchPanel();
		}
	}
	
	/**
	 * Verifica que la contrasena tenga el formato correcto
	 * @param password la contrasena que se debe verificar
	 * @return true si la contrasena cumple los reeuisitos., false si no
	 */
	private boolean validateData(String password) {
		boolean isDataValid = true;
		if (password.isEmpty()) {
			vistaActualizar.getLblPasswordError().setText("Debe ingresar una contrasena");
			vistaActualizar.getLblPasswordError().setVisible(true);
			isDataValid = false;
		}else if(password.length() < 4 || password.length() > 10) {
			vistaActualizar.getLblPasswordError().setText("La contrasena debe tener entre 4 y 10 cracteres");
			vistaActualizar.getLblPasswordError().setVisible(true);
			isDataValid = false;
		}else if(!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{4,}$")) {
			vistaActualizar.getLblPasswordError().setText(
					  "<html>"
					+ 	"<p>"
					+ 		"La contrasena debe tener al menos 1 mayuscula, 1 miniscula,"
					+ 		"<br> 1 numero y 1 caracter especial"
					+ 	"</p>"
					+ "</html>"
			);
			vistaActualizar.getLblPasswordError().setVisible(true);
			isDataValid = false;
			
		}
		return isDataValid;
	}

	/**
	 * Muestra la informacion del usuario sleccionado
	 */
	private void mostrarInfoUsuario() {
		Usuario user = vistaActualizar.getListaUsuarios().getSelectedValue();
		if (user != null) {
			vistaActualizar.getTfUsername().setText(user.getUsername());
			vistaActualizar.getTfPassword().setText(user.getPassword());
			vistaActualizar.changeToActualizarPanel();
		}
	}
	
	
}
