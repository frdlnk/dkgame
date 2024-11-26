package modelo.armamento.armas;

import modelo.armamento.municiones.Bala;
import modelo.armamento.municiones.Municion;
import modelo.arrays.ArrayString;
import motor_v1.motor.util.Vector2D;

/**
 * {@link Arma} que genera Balas
 * 
 * @implNote Arma predeterminada
 * 
 * @author Joshua Elizondo Vasquez
 * @see Arma
 */
public class Pistola extends Arma {
	public final static double DEFAULT_SHOOT_DELAY = .5;
	
	/**
	 * Construye una pistola con cadencia de {@value #DEFAULT_SHOOT_DELAY}s
	 */
	public Pistola() {
		super(DEFAULT_SHOOT_DELAY);
	}

	@Override
	protected Municion generarBala(Vector2D posicion, Vector2D direccion, ArrayString target) {
		return new Bala(posicion, direccion, target, 10);
	}

}
