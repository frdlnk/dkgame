package ctrl.gameControlers;

import javax.swing.JLabel;

import ctrl.adminControlers.CreateControler;
import modelo.UserConfig;
import modelo.Usuario;
import modelo.Dao.IDAOUserConfigs;
import modelo.Dao.IDAOUsuario;
import vista.admin.VistaCrear;
import vista.game.VistaInicioJuego;
import vista.game.VistaLogin;

public class LoginControler {
	private VistaLogin vista;
	private IDAOUsuario modeloUser;
	private IDAOUserConfigs modeloConfigs;
	
	public LoginControler(VistaLogin vista, IDAOUsuario modeloUser, IDAOUserConfigs modeloConfigs) {
		this.vista = vista;
		this.modeloUser = modeloUser;
		this.modeloConfigs = modeloConfigs;
		
		vista.getBtnLogin().addActionListener(e -> login());
		vista.getBtnRegistrarse().addActionListener(e -> mostrarRegistrar());
		vista.getBtnVolver().addActionListener(e -> volver());
		
		vista.setVisible(true);
	}

	private void volver() {
		VistaInicioJuego vistaInicio = new VistaInicioJuego();
		new WelcomeGameControler(vistaInicio, modeloUser, modeloConfigs);
		vista.dispose();
	}

	private void mostrarRegistrar() {
		VistaCrear vistaCrear = new VistaCrear(vista);
		new CreateControler(vistaCrear, modeloConfigs, modeloUser);
	}

	private void login() {
		if (validateFieldsNotEmpty()) {
			String username = vista.getTfUsername().getText();
			String password = vista.getTfPassword().getText();
			Usuario user = modeloUser.get(username);
			if (user != null && user.getPassword().equals(password)) {
				UserConfig config = modeloConfigs.get(user.getConfigId());
				iniciarJuego(user, config);
				vista.dispose();
			}else {
				vista.getLblError().setText("Username o contrasena incorrectos");
				vista.getLblError().setVisible(true);
			}
		}
	}
	
	private void iniciarJuego(Usuario user, UserConfig config) {
		new GameControler(user, config);
	}
	
	private boolean validateFieldsNotEmpty() {
		JLabel lblError = vista.getLblError();
		lblError.setVisible(false);
		boolean isDataValid = true;
		String username = vista.getTfUsername().getText();
		String password = vista.getTfPassword().getText();
		if (username.isBlank() || password.isBlank()) {
			isDataValid = false;
			lblError.setText("Debe ingresar ambos campos");
			lblError.setVisible(true);
		}
		return isDataValid;
	}
}
