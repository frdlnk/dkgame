package ctrl.gameControlers;

import java.awt.Graphics;

import modelo.UserConfig;
import modelo.Usuario;
import motor_v1.motor.Entidad;
import motor_v1.motor.Lienzo;
import motor_v1.motor.Scene;
import utils.Conf;
import vista.escena.EscenaCarga;

public class Game extends Entidad{
	private Scene escenaActual;
	public static Lienzo lienzo;
	public static Usuario loggedUser;
	public static UserConfig configuracion;
	
	public Game(Usuario user, UserConfig configuracion) {
		Game.loggedUser = user;
		Game.configuracion = configuracion;
		escenaActual = new EscenaCarga();
		Scene.cambiarEscena(escenaActual);
		lienzo = new Lienzo(Conf.WINDOW_WIDTH, Conf.WINDOW_HEIGHT, "Metal Slug");
	}
	
	@Override
	public void actualizar() {
		escenaActual = Scene.getEscenaActual();
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
