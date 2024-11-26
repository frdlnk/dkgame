package ctrl;

import ctrl.adminControlers.AdminPanelControler;
import ctrl.gameControlers.WelcomeGameControler;
import modelo.Dao.IDAOUserConfigs;
import modelo.Dao.IDAOUsuario;
import modelo.Dao.file.DAO_UserConfig;
import modelo.Dao.file.DAO_Usuario;
import vista.MenuInicio;
import vista.admin.AdminPanel;
import vista.game.VistaInicioJuego;

public class MenuInicioControler{
	private MenuInicio vista;
	private IDAOUsuario modeloUser;
	private IDAOUserConfigs modeloConfigs;
	
	public MenuInicioControler(DAO_Usuario modeloUser, DAO_UserConfig modelConfigs) {
		this.modeloUser = modeloUser;
		this.modeloConfigs = modelConfigs;
		vista = new MenuInicio();
		vista.getBtnAdministracion().addActionListener(e -> openAdmin());
		vista.getBtnJugar().addActionListener(e -> initGame());
		vista.setVisible(true);
	}

	private void openAdmin() {
		AdminPanel vistaB = new AdminPanel();
		new AdminPanelControler(vistaB, modeloConfigs, modeloUser);
		vista.dispose();
	}
	
	private void initGame() {
		VistaInicioJuego vistaInicioJuego = new VistaInicioJuego();
		new WelcomeGameControler(vistaInicioJuego, modeloUser, modeloConfigs);
		vista.dispose();
	}
}
