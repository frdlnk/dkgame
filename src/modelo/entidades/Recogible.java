package modelo.entidades;

import java.awt.image.BufferedImage;

import modelo.componentes.Fisica;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Transform;
import motor_v1.motor.entidades.SpriteSolido;
import utils.colision.ColisionInfo;
import utils.colision.Colisionable;
import utils.constants.Tags;
import utils.interfaces.Movible;

/**
 * Objeto que el jugador puede recoger
 */
public abstract class Recogible extends SpriteSolido implements Colisionable, Movible {
	private Fisica fisica;
	private int puntos;
	
	/**
	 * Crea un nuevo recogible
	 * @param textura imagen a mostrar
	 * @param transformar posicion del objeto
	 */
	public Recogible(BufferedImage textura, Transform transformar, int puntos) {
		super(Tags.RECOGIBLE,textura, transformar);
		this.puntos = puntos;
		fisica = new Fisica(1,1,transformar);
		colisiona.actualizar();
	}
	
	@Override
	public void actualizar() {
		fisica.actualizar();
		colisiona.actualizar();
		super.actualizar();
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
		if (!getViva()) {
			return null;
		}
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

	@Override
	public Fisica getFisica() {
		return this.fisica;
	}

	public void setFisica(Fisica fisica) {
		this.fisica = fisica;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	@Override
	public String toString() {
		return "Recogible [fisica=" + fisica + ", puntos=" + puntos + "]";
	}
}