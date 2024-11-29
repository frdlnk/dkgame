package modelo.componentes;

import javax.sound.midi.VoiceStatus;

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
	 * mueve el objeto a otr posicion
	 */
	@Override
	public void trasladarloA(Vector2D nuevaPosicion) {
		pos = nuevaPosicion;
	}
	
	
	public Transform getPivot() {
		return pivot;
	}
	
	public void setPivot(Transform pivot) {
		this.pivot = pivot;
	}
}
