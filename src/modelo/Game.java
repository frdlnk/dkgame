package modelo;

import java.awt.Graphics;

import motor_v1.motor.Entidad;
import motor_v1.motor.Lienzo;
import motor_v1.motor.Scene;
import utils.Conf;

public class Game extends Entidad{
	private Scene escenaActual;
	public static Lienzo lienzo;
	
	public Game(Scene escena) {
		escenaActual = escena;
		Scene.cambiarEscena(escena);
		lienzo = new Lienzo(Conf.WINDOW_WIDHT, Conf.WINDOW_HEIGT, "Juego");
		lienzo.setResizable(true);
	}
	
	@Override
	public void actualizar() {
		escenaActual.actualizar();
	}

	@Override
	public void destruir() {
		escenaActual.destruir();
	}

	@Override
	public void dibujar(Graphics arg0) {
		lienzo.dibujar(escenaActual);
	}

}
