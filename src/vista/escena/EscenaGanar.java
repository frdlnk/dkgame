package vista.escena;

import java.awt.Color;
import java.awt.Graphics;

import ctrl.gameControlers.GameControler;
import ctrl.gameControlers.Game;
import modelo.entidades.Player;
import motor_v1.motor.Scene;
import motor_v1.motor.entidades.SpriteText;
import motor_v1.motor.input.InputKeyboard;
import motor_v1.motor.input.Key;
import motor_v1.motor.util.Vector2D;
import utils.constants.Assets;

/**
 * Escena para mostrar los resultados al ganar
 */
public class EscenaGanar extends Scene {
	private Player jugador;
	private SpriteText resumen;
	private SpriteText felicitaciones;
	
	public EscenaGanar() {
		jugador = Game.getJugador();
		String mensaje = "obtuviste un puntaje de " + jugador.getPuntaje();
		Vector2D posResumen = new Vector2D(30,90);
		resumen = new SpriteText(mensaje, Color.YELLOW, Assets.future, false);
		resumen.setPosicion(posResumen);
		String mensajeF = "Felicidades ganaste";
		Vector2D posFelicitacion = new Vector2D(30,50);
		felicitaciones = new SpriteText(mensajeF, Color.YELLOW, Assets.future, false);
		felicitaciones.setPosicion(posFelicitacion);
	}
	
	@Override
	public void actualizar() {
		resumen.actualizar();
		felicitaciones.actualizar();
		if (InputKeyboard.isDown(Key.SPACE)) {
			int lastLevel = Game.getLoggedUser().getLevel();
			Game.getLoggedUser().setLevel(lastLevel+1);
			GameControler.detener();
		}
	}

	@Override
	public void destruir() {
		felicitaciones.destruir();
		resumen.destruir();
	}

	@Override
	public void dibujar(Graphics g) {
		felicitaciones.dibujar(g);
		resumen.dibujar(g);
	}

}
