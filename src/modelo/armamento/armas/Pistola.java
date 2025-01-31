package modelo.armamento.armas;

import java.util.ArrayList;

import modelo.armamento.municiones.Bala;
import modelo.armamento.municiones.Municion;
import motor_v1.motor.util.Vector2D;

/**
 * {@link Arma} que genera {@link Bala}s con una cadencia de
 * {@value #DEFAULT_SHOOT_DELAY}
 * 
 * @implNote Arma predeterminada
 * 
 * @author Joshua Elizondo Vasquez
 * @see Arma
 * @see Bala
 */
public class Pistola extends Arma {
	public final static double DEFAULT_SHOOT_DELAY = .5;

	/**
	 * Construye una pistola con cadencia de {@value #DEFAULT_SHOOT_DELAY}s
	 */
	public Pistola() {
		super(DEFAULT_SHOOT_DELAY, DEFAULT_DANO);
	}

	public Pistola(Double shotDelay, int municiones, double dano) {
		super(shotDelay, municiones, dano);
	}

	@Override
	protected Municion generarBala(Vector2D posicion, Vector2D direccion, ArrayList<String> target) {
		return new Bala(posicion, direccion, target, 10);
	}

	@Override
	public String toString() {
		return "Pistola []";
	}

}
