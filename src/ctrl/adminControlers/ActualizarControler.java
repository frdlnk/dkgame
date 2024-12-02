package ctrl.adminControlers;

import java.awt.event.ActionEvent;

import modelo.Usuario;
import modelo.Dao.IDAOUsuario;
import vista.admin.VistaActualizar;
import vista.admin.VistaBuscar;

/**
 * Clase encargada de controlar la actualizacion de datos del usuario
 * 
 * @see BuscarControler
 * @see VistaBuscar
 * @see VistaActualizar
 */
public class ActualizarControler extends BuscarControler{
	private VistaActualizar vistaActualizar;
	private UserFieldsValidator validator;
	
	/**
	 * Crea un nuevo controlador de actualizar
	 * @param vista vista que se va a controlar
	 * @param modelo Dao de acceso a los datos del usuario
	 */
	public ActualizarControler(VistaActualizar vista, IDAOUsuario modelo) {
		super(vista, modelo);
		vistaActualizar = vista;
		validator = new UserFieldsValidator(modelo);
		vista.getBtnSelectUser().addActionListener(this);
		vista.getBtnActualizar().addActionListener(this);
		vista.getBtnCancelar().addActionListener(this);
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
			modeloUsuario.update(user);
			vistaActualizar.changeToSearchPanel();
		}
	}
	
	/**
	 * Verifica que la contrasena tenga el formato correcto
	 * <br> en caso de error muestra el mensaje de error
	 * @param password la contrasena que se debe verificar
	 * @return true si la contrasena cumple los requisitos, false si no
	 */
	private boolean validateData(String password) {
		//la setea no visible para que en la siguiente validacion si no hay error desaparesca
		vistaActualizar.getLblPasswordError().setVisible(false);
		String validatorResult = validator.validatePassword(password);
		if (validatorResult != null) {
			vistaActualizar.getLblPasswordError().setText(validatorResult);
			vistaActualizar.getLblPasswordError().setVisible(true);
			return false;
		}
		return true;
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
	
	private void mostrarBusqueda() {
		vistaActualizar.changeToSearchPanel();
	}
	
	/**
	 *Asignacion de mentodos a los botones
	 */
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		if (source.equals(vistaActualizar.getBtnSelectUser())) {
			mostrarInfoUsuario();
		}else if(source.equals(vistaActualizar.getBtnActualizar())){
			actualizarUsuario();
		}else if (source.equals(vistaActualizar.getBtnCancelar())) {
			mostrarBusqueda();
		}
	}

	public VistaActualizar getVistaActualizar() {
		return vistaActualizar;
	}

	public void setVistaActualizar(VistaActualizar vistaActualizar) {
		this.vistaActualizar = vistaActualizar;
	}

	public UserFieldsValidator getValidator() {
		return validator;
	}

	public void setValidator(UserFieldsValidator validator) {
		this.validator = validator;
	}

	@Override
	public String toString() {
		return "ActualizarControler [vistaActualizar=" + vistaActualizar + ", validator=" + validator + "]";
	}
	
	
}
