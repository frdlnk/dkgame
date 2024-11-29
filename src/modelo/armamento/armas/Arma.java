package modelo.armamento.armas;

import java.io.Serializable;

import modelo.armamento.municiones.Municion;
import modelo.arrays.ArrayString;
import motor_v1.motor.GameLoop;
import motor_v1.motor.util.Vector2D;

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
public abstract class Arma implements Serializable {
	public final static int BALAS_DEFAULT = 100;
	public final static int DEFAULT_DELAY = 1;
	
	protected double shootDelay;
	private double timeToNextShoot;
	private int balasRestantes;
	protected double dano;
	
	/**
	 * Construye un arma con delay predeterminado de {@value #DEFAULT_DELAY}s y {@value #BALAS_DEFAULT} municiones
	 */
	public Arma() {
		shootDelay = DEFAULT_DELAY;
		timeToNextShoot = 0;
		dano = 0;
		setBalasRestantes(BALAS_DEFAULT);
	}
	
	/**
	 * Construye un arma con un delay especificado y {@value #BALAS_DEFAULT} municiones
	 * @param shootDelay tiempo en segundos necesario entre disparos
	 */
	public Arma(double shootDelay, double dano) {
		this();
		this.shootDelay = shootDelay;
		this.dano = dano;
	}
	/**
	 * Construye un arma con el delay, dano y cantidad de municiones especificado
	 * @param shotDelay
	 * @param municiones
	 * @param dano
	  */
	public Arma(Double shotDelay, int municiones, double dano) {
		this(shotDelay, dano);
		setBalasRestantes(municiones);
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
	
	/**
	 * Suma balas al cargador
	 * @param cantidad
	 */
	public void anadirBalas(int cantidad) {
		setBalasRestantes(getBalasRestantes() + cantidad);
	}

	//getters y setters
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

	public double getDano() {
		return dano;
	}

	public void setDano(double dano) {
		this.dano = dano;
	}
	
	
	
}
