package vista.escena;

import java.awt.Color;
import java.awt.Graphics;

import motor_v1.motor.Scene;
import motor_v1.motor.entidades.ListaEntidades;
import motor_v1.motor.entidades.SpriteText;
import motor_v1.motor.input.InputKeyboard;
import motor_v1.motor.input.Key;
import motor_v1.motor.util.Vector2D;
import utils.constants.Assets;
import utils.constants.Conf;

public class EscenaCarga extends Scene {
	private SpriteText loadingText;
	private ListaEntidades controlsText;
	private boolean assetsCargados;
	
	public EscenaCarga() {
		Color color = new Color(255,255,255);
		Vector2D posicionLoading = new Vector2D(Conf.WINDOW_WIDTH-80, Conf.WINDOW_HEIGHT-20);
		loadingText = new SpriteText("Cargando...", color, null, false);
		loadingText.setPosicion(posicionLoading);
		
		this.controlsText = new ListaEntidades();
		
		Vector2D posicionControls = new Vector2D(20, 20);
		String[] controlsText = {"Use A, W, S, D para moverse y apuntar"
							, "Use espacio para saltar"
							, "De click izquierdo para disparar"
							, "Presione ESC para pausa"};
		
		for (int i = 0; i < controlsText.length; i++) {
			Vector2D posNextLine = posicionControls.add(new Vector2D(0,i*15));
			SpriteText nextLine = new SpriteText(controlsText[i], color, null, false);
			nextLine.setPosicion(posNextLine);
			this.controlsText.add(nextLine.getNombre(), nextLine);
		}
	}
	
	@Override
	public void actualizar() {
		if (!assetsCargados) {
			Assets.load();
			loadingText.setMensaje("Presione espacio para continuar");
			Vector2D posicionLoading = new Vector2D(Conf.WINDOW_WIDTH-200, Conf.WINDOW_HEIGHT-20);
			loadingText.setPosicion(posicionLoading);
			assetsCargados = true;
		}else if (InputKeyboard.isDown(Key.SPACE)) {
			Scene nivel = new EscenaUno();
			Scene.cambiarEscena(nivel);
		}
	}

	@Override
	public void destruir() {
		loadingText.destruir();
		controlsText.destruir();
	}

	@Override
	public void dibujar(Graphics g) {
		loadingText.dibujar(g);
		controlsText.dibujar(g);
	}

}
