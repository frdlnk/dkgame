package ctrl.gameControlers;

import modelo.UserConfig;
import modelo.Usuario;
import motor_v1.motor.GameLoop;

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
	}
	
	
}
