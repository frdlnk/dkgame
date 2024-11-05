package modelo.armamento.armas;

import modelo.armamento.municiones.Municion;
import motor_v1.motor.GameLoop;
import motor_v1.motor.util.Vector2D;
import utils.Array;

public abstract class Arma {
	protected double shootDelay;
	private double timeToNextShoot;
	
	public Arma() {
		this(1);
	}
	
	public Arma(double shootDelay) {
		this.shootDelay = shootDelay;
		this.timeToNextShoot = 0;
	}
	
	public  Municion disparar(Vector2D posicion, Vector2D direccion, Array<String> target) {
		if(timeToNextShoot <= 0) {
			timeToNextShoot = shootDelay;
			return generarBala(posicion, direccion, target);
		}
		return null;
	};
	
	protected abstract Municion generarBala(Vector2D posicion, Vector2D direccion, Array<String> target);
	
	public void actualizar() {
		timeToNextShoot = timeToNextShoot > 0 ? (timeToNextShoot - GameLoop.dt/1000) : 0;
	}
}
