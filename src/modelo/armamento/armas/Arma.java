package modelo.armamento.armas;

import modelo.armamento.municiones.Municion;
import motor_v1.motor.GameLoop;
import motor_v1.motor.util.Vector2D;
import utils.Array;

/**
 * Esta clase concentra la logica de generacion de municiones
 * 
 * Extienda esta clase para crear armas que generen distintos tipos de municion y 
 * de control de disparos
 * 
 * Sus implementaciones se encargan de generar las nuevas instancias de municion
 * 
 * @author Joshua Elizondo Vasquez
 * @version 1.0
 */
public abstract class Arma {
	protected double shootDelay;
	private double timeToNextShoot;
	
	/**
	 * Construye un arma con delay predeterminado de 1s
	 */
	public Arma() {
		this(1);
	}
	
	/**
	 * Construye un arma con un delay especificado
	 * @param shootDelay tiempo en segundos necesario entre disparos
	 */
	public Arma(double shootDelay) {
		this.shootDelay = shootDelay;
		this.timeToNextShoot = 0;
	}
	
	/**
	 * Construye una municion nueva si y solo si cumple la cadencia de tiro
	 * @param posicion Vector2D posicion inicial de la municion a disparar
	 * @param direccion Vector2D direccion en la que se debe dirigir la municion
	 * @param targetsIgnored lista de tags que la municion debe ignorar
	 * @return Municion si cumple la cadencia de tiro, null en caso contrario
	 */
	public Municion disparar(Vector2D posicion, Vector2D direccion, Array<String> targetsIgnored) {
		if(timeToNextShoot <= 0) {
			timeToNextShoot = shootDelay;
			return generarBala(posicion, direccion, targetsIgnored);
		}
		return null;
	};
	
	/**
	 * Crea la instancia de Municion del arma concreta
	 * @param posicion Vector2D posicion inicial de la municion
	 * @param direccion Vector2D direccion de la municion
	 * @param targetsIgnored Lista de tags que la Municion debe ignorar
	 * @return Municion creada por el arma
	 */
	protected abstract Municion generarBala(Vector2D posicion, Vector2D direccion, Array<String> targetsIgnored);
	
	/**
	 * Se debe llamar cada frame controla la reduccion del tiempo de disparo
	 */
	public void actualizar() {
		timeToNextShoot = timeToNextShoot > 0 ? (timeToNextShoot - GameLoop.dt/1000) : 0;
	}
}
