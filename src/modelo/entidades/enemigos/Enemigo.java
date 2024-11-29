package modelo.entidades.enemigos;

import java.awt.image.BufferedImage;

import ctrl.gameControlers.Game;
import modelo.componentes.Fisica;
import modelo.entidades.Player;
import modelo.entidades.Soldado;
import motor_v1.motor.component.Transform;
import motor_v1.motor.util.Vector2D;
import utils.colision.ColisionInfo;
import utils.colision.Colisionable;
import utils.constants.Conf;
import utils.constants.Tags;

/**
 * Clase enemigo con funciones basicas para el funcionamiento de un enemigo
 * 
 * @see Soldado
 */
public abstract class Enemigo extends Soldado {
	private int valorPuntaje;
	/**
	 * Crea un nuevo {@link Enemigo} con el tag {@value Tags#ENEMY}
	 * @param imagenes imagenes de gif a mostrarse
	 * @param posicion inicial del enemigo
	 * @param duracionImagen cantidad en segundos de la duracion del gif
	 */
	public Enemigo(BufferedImage[] imagenes, Transform posicion, double duracionImagen, double salud, int valorPuntaje) {
		super(Tags.ENEMY, imagenes, posicion, duracionImagen, salud);
		this.valorPuntaje = valorPuntaje;
		colisiona.actualizar();
		fisica = new Fisica(1,1,transformar);
	}
	
	@Override
	public void actualizar() {
		
		fisica.actualizar();
		colisiona.actualizar();
		super.actualizar();
	}
	
	/**
	 * verifica que este dentro de la pantalla
	 * @return
	 */
	public boolean isInScreen() {
		Vector2D pos = transformar.getPosicion();
		if (pos.getX() + colisiona.getHitbox().getWidth() > 0 && pos.getX() < Conf.WINDOW_WIDTH) {
			return true;
		}
		return false;
	}
	
	@Override
	public void recibirDano(double dano) {
		salud -= dano;
		if (salud <= 0) {
			morir();
		}
	}
	
	@Override
	public void morir() {
		Game.getJugador().recibirPuntos(valorPuntaje);
	}
	
	/**
	 * Consigue la direccion del jugador desde un punto dado
	 * @param posicion desde la cual se quiere saber la direccion hasta el jugador
	 * @return direccion hacia el jugador
	 */
	protected Vector2D getDireccionJugador(Vector2D posicion) {
		Vector2D distancia = getDistanciaJugador(posicion);
		
		return distancia != null ? distancia.normalize() : null;
	}
	
	
	/**
	 * Calcula la distancia desde un punto dado hasta el jugador
	 * @param posicion desde la cual se va a calcular la distancia
	 * @return distancia hasta el jugador
	 */
	protected Vector2D getDistanciaJugador(Vector2D posicion) {
		Player jugador = Game.getJugador();
		if (jugador != null) {
			Vector2D posJugador = jugador.getCentro();
			Vector2D direccionHaciaJugador = posJugador.subtract(posicion);
			return direccionHaciaJugador;
		}
		return null;
	}
	
	
	@Override
	public ColisionInfo hayColision(Colisionable entidad) {
		if (isInScreen()) {
			return super.hayColision(entidad);
		}
		return null;
	}

}
