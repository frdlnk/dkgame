package ctrl;

import modelo.Game;
import motor_v1.motor.GameLoop;
import utils.Assets;
import vista.escena.EscenaJuego;
import vista.escena.EscenaUno;

public class inicio {
	
	public static void main(String[] args) {
		
		
		if (Assets.load()) {
			EscenaJuego escenaInicial = new EscenaUno();
			Game game = new Game(escenaInicial);
			@SuppressWarnings("unused")
			GameLoop gameLoop = new GameLoop(game, 0);
		}
	}
}
