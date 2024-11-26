package ctrl.gameControlers;

import modelo.UserConfig;
import modelo.Usuario;
import motor_v1.motor.GameLoop;

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
