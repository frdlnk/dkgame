package ctrl.gameControlers;

import ctrl.adminControlers.CreateControler;
import modelo.Usuario;
import modelo.Dao.IDAOUserConfigs;
import modelo.Dao.IDAOUsuario;
import modelo.arrays.UserArray;
import vista.admin.VistaCrear;
import vista.game.VistaInicioJuego;
import vista.game.VistaLogin;


public class WelcomeGameControler {
	private VistaInicioJuego vista;
	private IDAOUsuario modeloUser;
	private IDAOUserConfigs modeloConfigs;
	
	public WelcomeGameControler(VistaInicioJuego vista, IDAOUsuario modeloUser, IDAOUserConfigs modeloConfigs) {
		this.vista = vista;
		this.modeloConfigs = modeloConfigs;
		this.modeloUser = modeloUser;
		
		vista.getBtnSalir().addActionListener(e -> salir());
		vista.getBtnRegistrar().addActionListener(e -> mostrarVistaRegistrar());
		vista.getBtnIniciarJuego().addActionListener(e -> mostrarLogin());
		
		loadBestScores();
		
		vista.setVisible(true);
	}
	
	private void loadBestScores() {
		UserArray bestScoArray = modeloUser.getBestScores(5);
		String[] formatedBestScoreStrings = new String[5];
		for (int i = 0; i < formatedBestScoreStrings.length; i++) {
			Usuario user = bestScoArray.get(i);
			if (user != null) {
				formatedBestScoreStrings[i] = user.getUsername()+": " + user.getScore();
			}
		}
		vista.getListPuntajes().setListData(formatedBestScoreStrings);
	}

	private void mostrarLogin() {
		VistaLogin vistaLogin = new VistaLogin();
		new LoginControler(vistaLogin, modeloUser, modeloConfigs);
		vista.dispose();
	}

	private void mostrarVistaRegistrar() {
		VistaCrear vistaCrear = new VistaCrear(vista);
		new CreateControler(vistaCrear, modeloConfigs, modeloUser);
	}

	private void salir() {
		vista.dispose();
	}
}
