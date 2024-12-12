package ctrl.gameControlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import ctrl.adminControlers.CreateControler;
import modelo.User;
import modelo.Dao.IDAOUserConfigs;
import modelo.Dao.IDAOUsuario;
import vista.admin.VistaCrear;
import vista.game.VistaInicioJuego;
import vista.game.VistaLogin;

/**
 * Controlador de la vista de bienvenida al juego
 */
public class WelcomeGameControler implements ActionListener {
	public final static int SCORES_A_MOSTRAR = 5;
	private VistaInicioJuego vista;
	private IDAOUsuario modeloUser;
	private IDAOUserConfigs modeloConfigs;

	/**
	 * Crea un nuevo controlador con la vista y los DAO's asociados <br>
	 * carga los mejores puntajes
	 * 
	 * @param vista         vista asociada
	 * @param modeloUser    DAO de acceso a los usuarios
	 * @param modeloConfigs DAO de acceso a las configuraciones
	 */
	public WelcomeGameControler(VistaInicioJuego vista, IDAOUsuario modeloUser, IDAOUserConfigs modeloConfigs) {
		this.vista = vista;
		this.modeloConfigs = modeloConfigs;
		this.modeloUser = modeloUser;

		vista.getBtnSalir().addActionListener(this);
		vista.getBtnRegistrar().addActionListener(this);
		vista.getBtnIniciarJuego().addActionListener(this);

		loadBestScores();

		vista.setVisible(true);
	}

	/**
	 * Carga los mejores {@value #SCORES_A_MOSTRAR} scores
	 */
	private void loadBestScores() {
		ArrayList<User> bestScoArray = modeloUser.getBestScores(SCORES_A_MOSTRAR);
		String[] formatedBestScoreStrings = new String[SCORES_A_MOSTRAR];
		for (int i = 0; i < bestScoArray.size(); i++) {
			User user = bestScoArray.get(i);
			if (user != null) {
				formatedBestScoreStrings[i] = user.getUsername() + ": " + user.getScore();
			}
		}
		vista.getListPuntajes().setListData(formatedBestScoreStrings);
	}

	/**
	 * Muetra la vista de login
	 */
	private void mostrarLogin() {
		VistaLogin vistaLogin = new VistaLogin();
		new LoginControler(vistaLogin, modeloUser, modeloConfigs);
		vista.dispose();
	}

	/**
	 * Muestra la vista de registrarse
	 */
	private void mostrarVistaRegistrar() {
		VistaCrear vistaCrear = new VistaCrear(vista);
		new CreateControler(vistaCrear, modeloConfigs, modeloUser);
	}

	/**
	 * Cierra el programa
	 */
	private void salir() {
		vista.dispose();
		System.exit(0);
	}

	/**
	 * Asignacion de funciones a botones
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == vista.getBtnIniciarJuego()) {
			mostrarLogin();
		} else if (source.equals(vista.getBtnRegistrar())) {
			mostrarVistaRegistrar();
		} else if (source.equals(vista.getBtnSalir())) {
			salir();
		}

	}

	public VistaInicioJuego getVista() {
		return vista;
	}

	public void setVista(VistaInicioJuego vista) {
		this.vista = vista;
	}

	public IDAOUsuario getModeloUser() {
		return modeloUser;
	}

	public void setModeloUser(IDAOUsuario modeloUser) {
		this.modeloUser = modeloUser;
	}

	public IDAOUserConfigs getModeloConfigs() {
		return modeloConfigs;
	}

	public void setModeloConfigs(IDAOUserConfigs modeloConfigs) {
		this.modeloConfigs = modeloConfigs;
	}

	public static int getScoresAMostrar() {
		return SCORES_A_MOSTRAR;
	}

	@Override
	public String toString() {
		return "WelcomeGameControler [vista=" + vista + ", modeloUser=" + modeloUser + ", modeloConfigs="
				+ modeloConfigs + "]";
	}

}
