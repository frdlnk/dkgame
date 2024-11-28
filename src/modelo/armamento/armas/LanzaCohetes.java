package modelo.armamento.armas;

import modelo.armamento.municiones.Choete;
import modelo.armamento.municiones.Municion;
import modelo.arrays.ArrayString;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.input.Key;
import motor_v1.motor.util.Vector2D;
import utils.constants.Tags;

/**
 * {@link Arma} que genera {@link Choete}s 
 * con una cadencia de {@value #SHOOT_DELAY}
 * 
 * @author Joshua Elizondo Vasquez
 * @see Arma
 * @see Choete
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
	protected Municion generarBala(Vector2D posicion, Vector2D direccion, ArrayString target) {
		target.add(Tags.FLOOR);
		return new Choete(posicion, direccion, target, 20);
	}

}
