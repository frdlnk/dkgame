package vista.escena;

import java.awt.Color;
import java.awt.Graphics;

import motor_v1.motor.Scene;
import motor_v1.motor.entidades.SpriteText;
import motor_v1.motor.util.Vector2D;
import utils.Conf;

public class EscenaCarga extends Scene {
	private SpriteText loadingText;
	
	public EscenaCarga() {
		Color color = new Color(255,255,255);
		Vector2D posicionLoading = new Vector2D(Conf.WINDOW_WIDTH-80, Conf.WINDOW_HEIGHT-20);
		loadingText = new SpriteText("Cargando...", color, null, false);
		loadingText.setPosicion(posicionLoading);
	}
	
	@Override
	public void actualizar() {
		loadingText.actualizar();
	}

	@Override
	public void destruir() {
		loadingText.destruir();
	}

	@Override
	public void dibujar(Graphics g) {
		loadingText.dibujar(g);
	}

}
