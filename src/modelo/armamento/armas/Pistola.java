package modelo.armamento.armas;

import modelo.armamento.municiones.Bala;
import modelo.armamento.municiones.Municion;
import motor_v1.motor.util.Vector2D;
import utils.Array;

/**
 * Arma que genera Balas, arma PREDETERMINADA
 */
public class Pistola extends Arma {
	public final static double SHOOT_DELAY = .5;
	
	/**
	 * Construye una pistola con cadencia de .5s
	 */
	public Pistola() {
		super(SHOOT_DELAY);
	}

	@Override
	public Municion generarBala(Vector2D posicion, Vector2D direccion, Array<String> target) {
		return new Bala(posicion, direccion, target, 2);
	}

}
