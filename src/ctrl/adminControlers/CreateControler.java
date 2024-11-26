package ctrl.adminControlers;

import modelo.UserConfig;
import modelo.Usuario;
import modelo.Dao.IDAOUserConfigs;
import modelo.Dao.IDAOUsuario;
import vista.admin.VistaCrear;

public class CreateControler {
	private VistaCrear vista;
	private IDAOUserConfigs modeloConfigs;
	private IDAOUsuario modeloUsers;
	
	public CreateControler(VistaCrear vista, IDAOUserConfigs modeloConfigs,
			IDAOUsuario modeloUsersIdaoUsuario) {
		this.vista = vista;
		this.modeloConfigs = modeloConfigs;
		this.modeloUsers = modeloUsersIdaoUsuario;
		
		vista.getBtnSave().addActionListener(e -> saveUser());
		vista.getBtnCancel().addActionListener(e -> onCancel());
		vista.setVisible(true);
	}
	
	private void onCancel() {
		vista.dispose();
	}

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
	
	private boolean validateData(String username, String password) {
		boolean isDataValid = true;
		vista.getLblPasswordError().setVisible(false);
		vista.getLblUsernameError().setVisible(false);
		
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
		if (password.isEmpty()) {
			vista.getLblPasswordError().setText("Debe ingresar una contrasena");
			vista.getLblPasswordError().setVisible(true);
			isDataValid = false;
		}else if(password.length() < 4 || password.length() > 10) {
			vista.getLblPasswordError().setText("La contrasena debe tener entre 4 y 10 cracteres");
			vista.getLblPasswordError().setVisible(true);
			isDataValid = false;
		}else if(!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{4,}$")) {
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
	
}
