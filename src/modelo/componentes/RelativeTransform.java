package modelo.componentes;

import  motor_v1.motor.component.Transform;
import motor_v1.motor.util.Vector2D;

public class RelativeTransform extends Transform {
	private Transform pivot;
	private Vector2D pos;

	public RelativeTransform(Vector2D posicion, Transform pivot) {
		super(posicion);
		this.pivot = pivot;
		pos = posicion;
	}
	
	@Override
	public Vector2D getPosicion() {
		return pivot.getPosicion().add(pos);
	}
	
	@Override
	public Vector2D getEscala() {
		return pivot.getEscala();
	}
	
	@Override
	public void trasladarloA(Vector2D nuevaPosicion) {
		pos = nuevaPosicion;
	}
	
	@Override
	public void setEscala(Vector2D escala) {
		pivot.setEscala(escala);
	}
	
	@Override
	public double getRotacion() {
		return pivot.getRotacion();
	}
	
	@Override
	public void setRotacion(int rotacion) {
		pivot.setRotacion(rotacion);
	}
	
	public Transform getPivot() {
		return pivot;
	}
	
	public void setPivot(Transform pivot) {
		this.pivot = pivot;
	}
}
