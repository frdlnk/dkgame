package ctrl.gameControlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import ctrl.adminControlers.CreateControler;
import modelo.UserConfig;
import modelo.Usuario;
import modelo.Dao.IDAOUserConfigs;
import modelo.Dao.IDAOUsuario;
import vista.admin.VistaCrear;
import vista.game.VistaInicioJuego;
import vista.game.VistaLogin;

/**
 * Clase encargada del inicio de sesion
 */
public class LoginControler implements ActionListener{
	private VistaLogin vista;
	private IDAOUsuario modeloUser;
	private IDAOUserConfigs modeloConfigs;
	
	/**
	 * Crea un nuevo controlador con la vista y modelos especificados
	 * @param vista vista asociada al controlador
	 * @param modeloUser DAo de acceso a los usuarios
	 * @param modeloConfigs DAO de acceso a las configuraciones
	 */
	public LoginControler(VistaLogin vista, IDAOUsuario modeloUser, IDAOUserConfigs modeloConfigs) {
		this.vista = vista;
		this.modeloUser = modeloUser;
		this.modeloConfigs = modeloConfigs;
		
		vista.getBtnLogin().addActionListener(this);
		vista.getBtnRegistrarse().addActionListener(this);
		vista.getBtnVolver().addActionListener(this);
		
		vista.setVisible(true);
	}

	/**
	 * Cancela el login y destruye la vista
	 */
	private void volver() {
		VistaInicioJuego vistaInicio = new VistaInicioJuego();
		new WelcomeGameControler(vistaInicio, modeloUser, modeloConfigs);
		vista.dispose();
	}

	/**
	 * Cambia a la vista de registrarse
	 */
	private void mostrarRegistrar() {
		VistaCrear vistaCrear = new VistaCrear(vista);
		new CreateControler(vistaCrear, modeloConfigs, modeloUser);
	}

	/**
	 * REaliza el login del usuario
	 */
	private void login() {
		if (validateFieldsNotEmpty()) {
			String username = vista.getTfUsername().getText();
			String password = vista.getTfPassword().getText();
			Usuario user = modeloUser.get(username);
			if (user != null && user.getPassword().equals(password)) {
				UserConfig config = modeloConfigs.get(user.getConfigId());
				for (UserConfig conf : modeloConfigs.getAll().getArregloObjetos()) {
					System.out.println(conf);
				}
				if (config != null) {
					iniciarJuego(user, config);
					vista.dispose();
				}
			}else {
				vista.getLblError().setText("Username o contrasena incorrectos");
				vista.getLblError().setVisible(true);
			}
		}
	}
	
	/**
	 * Inicia el juego 
	 * @param user Usuario logueado
	 * @param config Configuracion asociada al usuario
	 */
	private void iniciarJuego(Usuario user, UserConfig config) {
		new GameControler(user, config);
	}
	
	/**
	 * Valida que se hayan ingresado datos
	 * @return true si se ingresaron username y password, false si no
	 */
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

	/**
	 *Asignacion de funciones a los botones
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(vista.getBtnLogin())) {
			login();
		}else if (source.equals(vista.getBtnRegistrarse())) {
			mostrarRegistrar();
		}else if (source.equals(vista.getBtnVolver())) {
			volver();
		}
	}
}
