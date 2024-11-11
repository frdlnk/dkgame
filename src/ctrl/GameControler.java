package ctrl;

import motor_v1.motor.GameLoop;
import motor_v1.motor.Scene;
import utils.Assets;
import vista.escena.EscenaCarga;
import vista.escena.EscenaUno;


public class GameControler {
	GameLoop loop;
	Game juego;
	
	public GameControler() {
		Scene escenaCarga = new EscenaCarga();
		juego = new Game(escenaCarga);
	}
	
	public void iniciarJuego() {
		loop = new GameLoop(juego, 0);
		if(Assets.load()) {
			Scene nivel = new EscenaUno();
			Scene.cambiarEscena(nivel);
		}
	}
	
	public void detenerjuego() {
		loop.detener();
	}
}
