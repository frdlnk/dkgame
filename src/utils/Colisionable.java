package utils;

import motor_v1.motor.component.Collider;

public interface Colisionable {
	public void onColision(ColisionInfo colision);
	public ColisionInfo hayColision(Colisionable entidad);
	public Collider[] getColliders();
}
