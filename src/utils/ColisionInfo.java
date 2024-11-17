package utils;

import motor_v1.motor.Entidad;
import motor_v1.motor.component.Collider;

public class ColisionInfo {
	private Entidad entidad;
	private Colisionable colisionable;
	private Collider colider;
	public ColisionInfo(Entidad entidad, Colisionable colisionable, Collider colider) {
		super();
		this.entidad = entidad;
		this.colisionable = colisionable;
		this.colider = colider;
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
	
	
}
