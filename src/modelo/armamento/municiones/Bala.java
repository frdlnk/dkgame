package modelo.armamento.municiones;

import modelo.entidades.Soldado;
import motor_v1.motor.Entidad;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Movement;
import motor_v1.motor.component.Physics;
import motor_v1.motor.util.Vector2D;
import utils.Array;
import utils.Tags;

/**
 * {@link Municion} mas comun, crea una bala que viaja en linea recta hasta impactar
 * 
 * @author Joshua Elizondo Vasquez
 * @see Municion
 */
public class Bala extends Municion {
	
	/**
	 * Crea una nueva bala con dano predeterminado 5
	 * @param posicion 			Vector2D posicion inicial de la bala
	 * @param direccion 		Vector2D direccion de la bala
	 * @param targetsIgnored 	lista de tags a ignorar
	 * @param dano double 		cantidad de dano a generar en las entidades
	 */
	public Bala(Vector2D posicion, Vector2D direccion, Array<String> targetsIgnored, double dano) {
		super(Tags.STATIC_OBJECT.getTag(), posicion, direccion, targetsIgnored, dano);
	}


	@Override
	protected void impacto(Entidad entidad) {
		//si impacta en un soldado le hace dano
		if (entidad instanceof Soldado) {
			((Soldado) entidad).recibirDano(getDano());
		}
		//al impactar se destruye
		destruir();
	}

}
