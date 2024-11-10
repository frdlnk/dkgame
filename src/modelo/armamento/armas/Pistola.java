package modelo.armamento.armas;

import modelo.armamento.municiones.Bala;
import modelo.armamento.municiones.Municion;
import motor_v1.motor.util.Vector2D;
import utils.arrays.ArrayString;

/**
 * {@link Arma} que genera Balas
 * 
 * @implNote Arma predeterminada
 * 
 * @author Joshua Elizondo Vasquez
 * @see Arma
 */
public class Pistola extends Arma {
	public final static double SHOOT_DELAY = .5;
	
	/**
	 * Construye una pistola con cadencia de {@value #SHOOT_DELAY}s
	 */
	public Pistola() {
		super(SHOOT_DELAY);
	}

	@Override
	protected Municion generarBala(Vector2D posicion, Vector2D direccion, ArrayString target) {
		return new Bala(posicion, direccion, target, 2);
	}

}
