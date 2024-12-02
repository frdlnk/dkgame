package ctrl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ctrl.adminControlers.AdminPanelControler;
import ctrl.gameControlers.WelcomeGameControler;
import modelo.Dao.IDAOUserConfigs;
import modelo.Dao.IDAOUsuario;
import modelo.Dao.file.DAO_UserConfig;
import modelo.Dao.file.DAO_Usuario;
import vista.MenuInicio;
import vista.admin.AdminPanel;
import vista.game.VistaInicioJuego;

/**
 * Controlador de inicio para la selecion del programa a evaluar
 */
public class MenuInicioControler implements ActionListener{
	private MenuInicio vista;
	private IDAOUsuario modeloUser;
	private IDAOUserConfigs modeloConfigs;
	
	public MenuInicioControler(MenuInicio vista,DAO_Usuario modeloUser, DAO_UserConfig modelConfigs) {
		this.modeloUser = modeloUser;
		this.modeloConfigs = modelConfigs;
		this.vista = vista;
		vista.getBtnAdministracion().addActionListener(this);
		vista.getBtnJugar().addActionListener(this);
		vista.setVisible(true);
	}

	/**
	 * Abre la aplicacion de administracion
	 */
	private void openAdmin() {
		AdminPanel vistaB = new AdminPanel();
		new AdminPanelControler(vistaB, modeloConfigs, modeloUser);
		vista.dispose();
	}
	
	/**
	 * Inicia la bienvenida al juego
	 */
	private void initGame() {
		VistaInicioJuego vistaInicioJuego = new VistaInicioJuego();
		new WelcomeGameControler(vistaInicioJuego, modeloUser, modeloConfigs);
		vista.dispose();
	}

	/**
	 * Asignacion de funciones a los botones
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(vista.getBtnAdministracion())) {
			openAdmin();
		}else if (source.equals(vista.getBtnJugar())) {
			initGame();
		}
	}

	public MenuInicio getVista() {
		return vista;
	}

	public void setVista(MenuInicio vista) {
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

	@Override
	public String toString() {
		return "MenuInicioControler [vista=" + vista + ", modeloUser=" + modeloUser + ", modeloConfigs=" + modeloConfigs
				+ "]";
	}
	
	
}
