package modelo.armamento.armas;

import modelo.armamento.municiones.Choete;
import modelo.armamento.municiones.Municion;
import motor_v1.motor.util.Vector2D;
import utils.Array;

/**
 * {@link Arma} que genera choetes
 * 
 * @author Joshua Elizondo Vasquez
 * @see Arma
 * 
 */
public class LanzaCohetes extends Arma {
	public final static double SHOOT_DELAY = 1.3;
	/**
	 * Crea un lanzaCohetes con cadencia de {@value #SHOOT_DELAY} segundos
	 */
	public LanzaCohetes() {
		super(SHOOT_DELAY);
	}
	
	@Override
	protected Municion generarBala(Vector2D posicion, Vector2D direccion, Array<String> target) {
		return new Choete("Cohete", posicion, direccion, target, 20);
	}

}
