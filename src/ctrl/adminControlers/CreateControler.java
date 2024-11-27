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
 * @see IDAOUserConfigs 
 * @see IDAOUsuario 
 * @see VistaCrear
  */
public class CreateControler implements ActionListener{
	private VistaCrear vista;
	private IDAOUserConfigs modeloConfigs;
	private IDAOUsuario modeloUsers;
	
	/**
	 * Constructor, crea un nuevo controlador asociado a la vista dada y con los DAO especificados
	 * @param vista	VistaCrear asociada al controlador para sus eventos
	 * @param modeloConfigs Dao de configuraciones de juego
	 * @param modeloUsersIdaoUsuario Dao de usuarios
	  */
	public CreateControler(VistaCrear vista, IDAOUserConfigs modeloConfigs,
			IDAOUsuario modeloUsersIdaoUsuario) {
		this.vista = vista;
		this.modeloConfigs = modeloConfigs;
		this.modeloUsers = modeloUsersIdaoUsuario;
		
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
			System.out.println(modeloUsers.getAll().getSize());
			vista.dispose();
		}
	}
	
	/** 
	 * <p>Valida los datos de username y pasword para que se cumplan las siguientes condiciones<p>
	 * <ul>
	 * 	<li>Username: no vacio, entre 4 y 50 caracteres, unico en los registros</li>
	 * 	<li>Password: no vacio, entre 4 y 10 caracteres, al menos una mayuscula, minuscula, numero y caracter especial</li>
	 * </ul>
	 * @param username String con el usuario a validar
	 * @param password String contrasena a validar
	 * @return boolean true si los campos cumplen con las condiciones, false en caso contrario
	 */
	private boolean validateData(String username, String password) {
		boolean isDataValid = true;
		vista.getLblPasswordError().setVisible(false);
		vista.getLblUsernameError().setVisible(false);
		//Username validator
		if (username.isEmpty()) {
			vista.getLblUsernameError().setText("Debe ingresar un nombre");
			vista.getLblUsernameError().setVisible(true);
			isDataValid = false;
		}else if(username.length() < 4 || username.length() > 50) {
			vista.getLblUsernameError().setText("El nombre deusuario debe tener entre 4 y 50 caracteres");
			vista.getLblUsernameError().setVisible(true);
			isDataValid = false;
		}else if(modeloUsers.get(username) != null) {
			vista.getLblUsernameError().setText("El nombre de usuario ya existe");
			vista.getLblUsernameError().setVisible(true);
			isDataValid = false;
		}
		//Password validator
		if (password.isEmpty()) {
			vista.getLblPasswordError().setText("Debe ingresar una contrasena");
			vista.getLblPasswordError().setVisible(true);
			isDataValid = false;
		}else if(password.length() < 4 || password.length() > 10) {
			vista.getLblPasswordError().setText("La contrasena debe tener entre 4 y 10 cracteres");
			vista.getLblPasswordError().setVisible(true);
			isDataValid = false;
		}else if(!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{4,}$")) {
			/* 
			 * regex valida que hayan al menos una mayuscula, una minuscula, 1 digito, 1 caracter especial
			 * (?=.*\\d): un digito
			 * (?=.*[a-z]): una letra minuscula
			 * (?=.*[A-Z]): una mayuscula
			 * (?=.*\\W): un caracter especial
			 * .{4,}: cualquier caracter al menos 4 veces
			 * ^$: inicio y final de la cadena respectivamente
			 */
			vista.getLblPasswordError().setText(
					  "<html>"
					+ 	"<p>"
					+ 		"La contrasena debe tener al menos 1 mayuscula, 1 miniscula,"
					+ 		"<br> 1 numero y 1 caracter especial"
					+ 	"</p>"
					+ "</html>"
			);
			vista.getLblPasswordError().setVisible(true);
			isDataValid = false;
			
		}
		return isDataValid;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(vista.getBtnCancel())) {
			onCancel();
		}else if (source.equals(vista.getBtnSave())) {
			saveUser();
		}
	}
	
}
