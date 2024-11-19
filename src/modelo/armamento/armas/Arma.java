package modelo.armamento.armas;

import modelo.armamento.municiones.Municion;
import motor_v1.motor.GameLoop;
import motor_v1.motor.util.Vector2D;
import utils.arrays.ArrayString;

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
	private int balasRestantes;
	
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
		setBalasRestantes(10);
	}
	
	/**
	 * Construye una municion nueva si y solo si cumple la cadencia de tiro
	 * @param posicion Vector2D posicion inicial de la municion a disparar
	 * @param direccion Vector2D direccion en la que se debe dirigir la municion
	 * @param targetsIgnored lista de tags que la municion debe ignorar
	 * @return Municion si cumple la cadencia de tiro, null en caso contrario
	 */
	public Municion disparar(Vector2D posicion, Vector2D direccion, ArrayString targetsIgnored) {
		if(timeToNextShoot <= 0 && balasRestantes > 0) {
			timeToNextShoot = shootDelay;
			Municion disparo = generarBala(posicion, direccion, targetsIgnored);
			if (disparo != null) balasRestantes--;
			return disparo;
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
	protected abstract Municion generarBala(Vector2D posicion, Vector2D direccion, ArrayString targetsIgnored);
	
	/**
	 * Se debe llamar cada frame controla la reduccion del tiempo de disparo
	 */
	public void actualizar() {
		timeToNextShoot -= timeToNextShoot > 0 ? GameLoop.dt : 0;
	}
	
	public void anadirBalas(int cantidad) {
		setBalasRestantes(getBalasRestantes() + cantidad);
	}

	public int getBalasRestantes() {
		return balasRestantes;
	}

	public void setBalasRestantes(int balasRestantes) {
		this.balasRestantes = balasRestantes;
	}

	public double getShootDelay() {
		return shootDelay;
	}

	public void setShootDelay(double shootDelay) {
		this.shootDelay = shootDelay;
	}

	public double getTimeToNextShoot() {
		return timeToNextShoot;
	}

	public void setTimeToNextShoot(double timeToNextShoot) {
		this.timeToNextShoot = timeToNextShoot;
	}
	
}
