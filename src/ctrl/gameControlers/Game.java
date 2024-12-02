package ctrl.gameControlers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import modelo.UserConfig;
import modelo.Usuario;
import modelo.entidades.Player;
import motor_v1.motor.Entidad;
import motor_v1.motor.Lienzo;
import motor_v1.motor.Scene;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.component.Transform;
import motor_v1.motor.util.Vector2D;
import utils.constants.Conf;
import vista.escena.EscenaCarga;

/**
 * Clase encargada de mantener las escenas corriendo e iniciar la ventana
 */
public class Game extends Entidad{
	public static Lienzo lienzo;
	public static Usuario loggedUser;
	public static UserConfig configuracion;
	public static Player jugador;
	
	private Scene escenaActual;
	
	/**
	 * Crea un nuevo Juego 
	 * @param user Usuario logueado 
	 * @param configuracion configuracion asociada al usuario
	 */
	public Game(Usuario user, UserConfig configuracion) {
		Game.loggedUser = user;
		Game.configuracion = configuracion;
		jugador = generarJugador();
		escenaActual = new EscenaCarga();
		Scene.cambiarEscena(escenaActual);
		lienzo = new Lienzo(Conf.WINDOW_WIDTH, Conf.WINDOW_HEIGHT, "Metal Slug");
	}
	
	/**
	 * crea un nuevo objetop Player que represente al usuario en el juego
	 * @return PLayer que se controlara en el juego
	 */
	private Player generarJugador() {
		Rectangle rect = new Rectangle(50, 80);
		Color color = new Color(128,128,0,0);
		BufferedImage image = Renderer.crearTextura(rect, color);
		Vector2D posicionJ = new Vector2D(10,180);
		Transform transform = new Transform(posicionJ);
		return new Player(image, transform, configuracion.getVidasIniciales());
	}
	
	@Override
	public void actualizar() {
		loggedUser.setScore(jugador.getPuntaje());
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

	public Scene getEscenaActual() {
		return escenaActual;
	}

	public void setEscenaActual(Scene escenaActual) {
		this.escenaActual = escenaActual;
	}

	public static Lienzo getLienzo() {
		return lienzo;
	}

	public static void setLienzo(Lienzo lienzo) {
		Game.lienzo = lienzo;
	}

	public static Usuario getLoggedUser() {
		return loggedUser;
	}

	public static void setLoggedUser(Usuario loggedUser) {
		Game.loggedUser = loggedUser;
	}

	public static UserConfig getConfiguracion() {
		return configuracion;
	}

	public static void setConfiguracion(UserConfig configuracion) {
		Game.configuracion = configuracion;
	}

	public static Player getJugador() {
		return jugador;
	}

	public static void setJugador(Player jugador) {
		Game.jugador = jugador;
	}

	@Override
	public String toString() {
		return "Game [escenaActual=" + escenaActual + "]";
	}

}
