package modelo.entidades;

import java.awt.image.BufferedImage;

import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Transform;
import motor_v1.motor.entidades.SpriteSolido;
import motor_v1.motor.util.Vector2D;
import utils.colision.ColisionInfo;
import utils.colision.Colisionable;
import utils.constants.Tags;

/**
 * Objeto que el jugador puede recoger
 */
public abstract class Recogible extends SpriteSolido implements Colisionable {
	private int puntos;
	
	/**
	 * Crea un nuevo recogible
	 * @param textura imagen a mostrar
	 * @param transformar posicion del objeto
	 */
	public Recogible(BufferedImage textura, Transform transformar, int puntos) {
		super(Tags.RECOGIBLE,textura, transformar);
		this.puntos = puntos;
	}
	
	/**
	 * regresa el objeto contenido por este objeto
	 * @return recompensa del recogible
	 */
	public abstract Object getReward();
	
	/**
	 * los puntos que vale este recogible
	 * @return
	 */
	public int getPuntos() {
		return puntos;
	}

	@Override
	public ColisionInfo hayColision(Colisionable entidad) {
		boolean trigger = false;
		ColisionInfo colision = new ColisionInfo(this,this,colisiona, trigger);
		Collider[] collidersEntidad = entidad.getColliders();
		for (int i = 0; i < collidersEntidad.length; i++) {
			if (colisiona.colisionaCon(collidersEntidad[i])) {
				return colision;
			}
		}
		return null;
	}

}