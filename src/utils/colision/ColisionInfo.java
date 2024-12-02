package utils.colision;

import motor_v1.motor.Entidad;
import motor_v1.motor.component.Collider;

/**
 * Contiene informacion sobre la colision con un Objeto Colisionable
 */
public class ColisionInfo {
	private Entidad entidad;
	private Colisionable colisionable;
	private Collider colider;
	private boolean isTriger;

	public ColisionInfo(Entidad entidad, Colisionable colisionable, Collider colider, Boolean isTriger) {
		super();
		this.entidad = entidad;
		this.colisionable = colisionable;
		this.colider = colider;
		this.setTriger(isTriger);
	}

	public ColisionInfo() {
		super();
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public Colisionable getColisionable() {
		return colisionable;
	}

	public void setColisionable(Colisionable colisionable) {
		this.colisionable = colisionable;
	}

	public Collider getColider() {
		return colider;
	}

	public void setColider(Collider colider) {
		this.colider = colider;
	}

	public boolean isTriger() {
		return isTriger;
	}

	public void setTriger(boolean isTriger) {
		this.isTriger = isTriger;
	}

	@Override
	public String toString() {
		return "ColisionInfo [entidad=" + entidad + ", colisionable=" + colisionable + ", colider=" + colider
				+ ", isTriger=" + isTriger + "]";
	}

}
