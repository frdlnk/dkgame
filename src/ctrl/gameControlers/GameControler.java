package ctrl.gameControlers;

import modelo.UserConfig;
import modelo.Usuario;
import modelo.Dao.IDAOUserConfigs;
import modelo.Dao.IDAOUsuario;
import modelo.Dao.file.DAO_UserConfig;
import modelo.Dao.file.DAO_Usuario;
import motor_v1.motor.GameLoop;
import vista.game.VistaInicioJuego;

/**
 * Esta calse se encarga de crear un nuevo juego y controlar el Loop principal
 */
public class GameControler {
	private static GameLoop loop;

	public GameControler(Usuario user, UserConfig config) {
		Game game = new Game(user, config);
		loop = new GameLoop(game, 3);
	}

	public static void detener() {
		if (loop != null) {
			loop.detener();
		}
		IDAOUsuario modeloUser = new DAO_Usuario();
		IDAOUserConfigs modeloConfigs = null;
		try {
			modeloConfigs = new DAO_UserConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}

		modeloUser.update(Game.loggedUser);
		Game.lienzo.dispose();

		VistaInicioJuego vista = new VistaInicioJuego();
		new WelcomeGameControler(vista, modeloUser, modeloConfigs);
	}

	public static GameLoop getLoop() {
		return loop;
	}

	public static void setLoop(GameLoop loop) {
		GameControler.loop = loop;
	}

	@Override
	public String toString() {
		return "GameControler []";
	}

}
