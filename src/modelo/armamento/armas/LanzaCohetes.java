package modelo.armamento.armas;

import modelo.armamento.municiones.Choete;
import modelo.armamento.municiones.Municion;
import motor_v1.motor.util.Vector2D;
import utils.Array;

/**
 * Arma que genera choetes
 */
public class LanzaCohetes extends Arma {

	/**
	 * Crea un lanzaCohetes con cadencia de 3 segundos
	 */
	public LanzaCohetes() {
		super(3);
	}
	
	@Override
	protected Municion generarBala(Vector2D posicion, Vector2D direccion, Array<String> target) {
		return new Choete("Cohete", posicion, direccion, target, 20);
	}

}
