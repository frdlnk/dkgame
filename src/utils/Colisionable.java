package utils;

import motor_v1.motor.Entidad;
import motor_v1.motor.component.Collider;

public interface Colisionable {
	public void onColision(Entidad entidad);
	public boolean hayColision(Colisionable entidad);
	public Collider getColisiona();
	public boolean isTrigger();
}
