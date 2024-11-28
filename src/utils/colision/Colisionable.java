package utils.colision;

import motor_v1.motor.component.Collider;

/**
 * Clase utilitaria para definir los objetos que pueden colisionar
 */
public interface Colisionable {
	/**
	 * Accion ejecutada al colisionar con algo
	 * @param colision Informacion sobre la colision
	 */
	public void onColision(ColisionInfo colision);
	/**
	 * Verifica si el objeto esta colisionando con otro
	 * @param entidad el objeto que se va a verificar la colision
	 * @return Informacion sobre la colision del objeto, null si no hay colision
	 */
	public ColisionInfo hayColision(Colisionable entidad);
	/**
	 * Devuelve todos los coliders asociados al objeto colisionable
	 * <br> Los colliders mostrados dependen del objeto en cuestion
	 * @return lista con los colliders activos del objeto 
	 */
	public Collider[] getColliders();
}
