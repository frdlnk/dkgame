package modelo.componentes;

import  motor_v1.motor.component.Transform;
import motor_v1.motor.util.Vector2D;

/**
 * Transform relativo a otro transform
 * 
 * Este transform permite posicionar objetos en base a un transform pivote 
 * <br> uselo cuando la posicion de un objeto deba ser dada en base a la posicion de otro
 * <br> por ejemplo colliders multiples en un objeto complejo
 * 
 * @see Transform
 */
public class RelativeTransform extends Transform {
	private Transform pivot;
	private Vector2D pos;

	/**
	 * Crea un nuevo transform relativo a otro y con una posicion inicial
	 * @param posicion 	posicion inicial
	 * @param pivot		transform pivote
	 */
	public RelativeTransform(Vector2D posicion, Transform pivot) {
		super(posicion);
		this.pivot = pivot;
		pos = posicion;
	}
	
	/**
	 * Devuelve la posicion actual real del objeto
	 */
	@Override
	public Vector2D getPosicion() {
		return pivot.getPosicion().add(pos);
	}
	
	/**
	 * Devuelve la posicion relativa del objeto
	 */
	public Vector2D getRelativePosicion() {
		return pos;
	}
	
	/**
	 * devuelve la escala del objeto
	 */
	@Override
	public Vector2D getEscala() {
		return pivot.getEscala();
	}
	
	/**
	 * mueve el objeto a otr posicion
	 */
	@Override
	public void trasladarloA(Vector2D nuevaPosicion) {
		pos = nuevaPosicion;
	}
	
	@Override
	public void setEscala(Vector2D escala) {
		pivot.setEscala(escala);
	}
	
	@Override
	public void escalarloA(double factorTamano) {
		pivot.escalarloA(factorTamano);
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
