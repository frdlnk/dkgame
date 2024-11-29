package vista.escena;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import ctrl.gameControlers.GameControler;
import ctrl.gameControlers.LoginControler;
import ctrl.gameControlers.Game;
import modelo.Dao.IDAOUserConfigs;
import modelo.Dao.IDAOUsuario;
import modelo.Dao.file.DAO_UserConfig;
import modelo.Dao.file.DAO_Usuario;
import modelo.entidades.Player;
import motor_v1.motor.Scene;
import motor_v1.motor.entidades.SpriteText;
import motor_v1.motor.input.InputKeyboard;
import motor_v1.motor.input.Key;
import motor_v1.motor.util.Vector2D;
import utils.constants.Assets;
import vista.game.VistaLogin;

/**
 * Escena para mostrar los resultados al ganar
 */
public class EscenaPerder extends Scene {
	private Player jugador;
	private SpriteText resumen;
	private SpriteText gameOver;
	
	public EscenaPerder() {
		jugador = Game.getJugador();
		String mensaje = "obtuviste un puntaje de " + jugador.getPuntaje();
		Vector2D posResumen = new Vector2D(30,90);
		resumen = new SpriteText(mensaje, Color.YELLOW, Assets.future, false);
		resumen.setPosicion(posResumen);
		String mensajeF = "Game Over";
		Vector2D posFelicitacion = new Vector2D(30,50);
		gameOver = new SpriteText(mensajeF, Color.RED, Assets.future, false);
		gameOver.setPosicion(posFelicitacion);
	}
	
	@Override
	public void actualizar() {
		resumen.actualizar();
		gameOver.actualizar();
		if (InputKeyboard.isDown(Key.SPACE)) {
			GameControler.detener();
		}
	}

	@Override
	public void destruir() {
		resumen.destruir();
		gameOver.destruir();
	}

	@Override
	public void dibujar(Graphics g) {
		gameOver.dibujar(g);
		resumen.dibujar(g);
	}

}
