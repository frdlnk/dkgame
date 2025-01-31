package modelo.armamento.armas;

import java.util.ArrayList;

import modelo.armamento.municiones.Choete;
import modelo.armamento.municiones.Municion;
import motor_v1.motor.util.Vector2D;
import utils.constants.Tags;

/**
 * {@link Arma} que genera {@link Choete}s con una cadencia de
 * {@value #SHOOT_DELAY}
 * 
 * @author Joshua Elizondo Vasquez
 * @see Arma
 * @see Choete
 */
public class LanzaCohetes extends Arma {
	public final static double SHOOT_DELAY = 1.5;
	public final static double DEFAULT_DANO = 30;

	/**
	 * Crea un lanzaCohetes con cadencia de {@value #SHOOT_DELAY} segundos
	 */
	public LanzaCohetes() {
		super(SHOOT_DELAY, DEFAULT_DANO);
	}

	/**
	 * Crea un nuevo LanzaChoetes con la municion especificada
	 * 
	 * @param municiones cantidad de municiones disponibles
	 */
	public LanzaCohetes(int municiones) {
		super(SHOOT_DELAY, municiones, DEFAULT_DANO);
	}

	@Override
	protected Municion generarBala(Vector2D posicion, Vector2D direccion, ArrayList<String> target) {
		target.add(Tags.FLOOR);
		return new Choete(posicion, direccion, target, 20);
	}

	@Override
	public String toString() {
		return "LanzaCohetes []";
	}

}
