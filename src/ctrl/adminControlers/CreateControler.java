package ctrl.adminControlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.UserConfig;
import modelo.Usuario;
import modelo.Dao.IDAOUserConfigs;
import modelo.Dao.IDAOUsuario;
import vista.admin.VistaCrear;

/**
 * Controlador encargado del guardado de nuevos usuarios
 * 
 * @see VistaCrear
  */
public class CreateControler implements ActionListener{
	private VistaCrear vista;
	private IDAOUserConfigs modeloConfigs;
	private IDAOUsuario modeloUsers;
	private UserFieldsValidator validator;
	
	/**
	 * Constructor, crea un nuevo controlador asociado a la vista dada y con los DAO especificados
	 * @param vista	VistaCrear asociada al controlador para sus eventos
	 * @param modeloConfigs Dao de configuraciones de juego
	 * @param modeloUsers Dao de usuarios
	  */
	public CreateControler(VistaCrear vista, IDAOUserConfigs modeloConfigs,
			IDAOUsuario modeloUsers) {
		this.vista = vista;
		this.modeloConfigs = modeloConfigs;
		this.modeloUsers = modeloUsers;
		this.validator = new UserFieldsValidator(modeloUsers);
		
		vista.getBtnSave().addActionListener(this);
		vista.getBtnCancel().addActionListener(this);
		vista.setVisible(true);
	}
	
	/** 
	 * Cierra la vista cuando cancela la operacion
	 */
	private void onCancel() {
		vista.dispose();
	}

	/** 
	 * Guarda el usuario si los datos ingresados son validos
	 */
	private void saveUser() {
		String username = vista.getTfUsername().getText().trim();
		String password = vista.getTfPassword().getText().trim();
		if (validateData(username, password)) {
			UserConfig config = new UserConfig();
			int configId = modeloConfigs.insert(config);
			
			Usuario newUsuario = new Usuario(username, password);
			newUsuario.setLevel(0);
			newUsuario.setScore(0);
			newUsuario.setConfigId(configId);
			
			modeloUsers.insert(newUsuario);
			vista.dispose();
		}
	}
	
	/** 
	 * Valida los datos del usuario y muestra los errores correspondientes(en caso de haber)
	 * @param username String con el username a validar
	 * @param password String contrasena a validar
	 * @return boolean true si los campos cumplen con las condiciones, false en caso contrario
	 */
	private boolean validateData(String username, String password) {
		boolean isDataValid = true;
		vista.getLblPasswordError().setVisible(false);
		vista.getLblUsernameError().setVisible(false);
		
		String usernameValidationRe = validator.validateUsername(username);
		String passwordValidationRe = validator.validatePassword(password);
		
		if (usernameValidationRe != null) {
			vista.getLblUsernameError().setText(usernameValidationRe);;
			vista.getLblUsernameError().setVisible(true);
			isDataValid = false;
		}
		if (passwordValidationRe != null) {
			vista.getLblPassword().setText(passwordValidationRe);;
			vista.getLblPasswordError().setVisible(true);
			isDataValid = false;
		}
		
		return isDataValid;
	}

	
	/**
	 *Asignacion de funciones a los botones
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(vista.getBtnCancel())) {
			onCancel();
		}else if (source.equals(vista.getBtnSave())) {
			saveUser();
		}
	}

	public VistaCrear getVista() {
		return vista;
	}

	public void setVista(VistaCrear vista) {
		this.vista = vista;
	}

	public IDAOUserConfigs getModeloConfigs() {
		return modeloConfigs;
	}

	public void setModeloConfigs(IDAOUserConfigs modeloConfigs) {
		this.modeloConfigs = modeloConfigs;
	}

	public IDAOUsuario getModeloUsers() {
		return modeloUsers;
	}

	public void setModeloUsers(IDAOUsuario modeloUsers) {
		this.modeloUsers = modeloUsers;
	}

	public UserFieldsValidator getValidator() {
		return validator;
	}

	public void setValidator(UserFieldsValidator validator) {
		this.validator = validator;
	}

	@Override
	public String toString() {
		return "CreateControler [vista=" + vista + ", modeloConfigs=" + modeloConfigs + ", modeloUsers=" + modeloUsers
				+ ", validator=" + validator + "]";
	}
	
	
}
