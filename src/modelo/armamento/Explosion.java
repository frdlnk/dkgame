package modelo.armamento;

import java.awt.image.BufferedImage;

import modelo.arrays.ArrayString;
import modelo.entidades.Soldado;
import modelo.worldObjects.TriggerBox;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Transform;
import utils.colision.ColisionInfo;
import utils.colision.Colisionable;
import utils.constants.Tags;

/**
 * Objeto encargado de representar una explosion que genere dano
 * 
 * @author Joshua Elizondo Vasquez
 * @see TriggerBox
 */
public class Explosion extends TriggerBox {
	private double dano;
	private ArrayString targetsIgnore;

	/**
	 * Crea una nueva explosion
	 * 
	 * @param nombre
	 * @param textura
	 * @param transformar
	 * @param targetsIgnore
	 */
	public Explosion(BufferedImage textura, Transform transformar, ArrayString targetsIgnore, int dano) {
		super(Tags.EXPLOCION, textura, transformar);
		colisiona.actualizar();
		this.dano = dano;
		this.targetsIgnore = targetsIgnore;
	}

	@Override
	public void onColision(ColisionInfo colision) {
		if (colision.getEntidad() instanceof Soldado && !targetsIgnore.contains(colision.getEntidad().getNombre())) {
			hitSoldado((Soldado) colision.getEntidad());
		}
	}

	@Override
	public ColisionInfo hayColision(Colisionable entidad) {
		if (!getViva()) {
			return null;
		}
		return super.hayColision(entidad);
	}

	/**
	 * Aplica el dano generado al soldado que impacta
	 * 
	 * @param soldado Soldado al que se hara dano
	 */
	private void hitSoldado(Soldado soldado) {
		soldado.recibirDano(dano);
	}

	@Override
	public void actualizar() {
		// solo existe un frame
		destruir();
		super.actualizar();
	}

	@Override
	public Collider[] getColliders() {
		return new Collider[] { colisiona };
	}

	public double getDano() {
		return dano;
	}

	public void setDano(double dano) {
		this.dano = dano;
	}

	public ArrayString getTargetsIgnore() {
		return targetsIgnore;
	}

	public void setTargetsIgnore(ArrayString targetsIgnore) {
		this.targetsIgnore = targetsIgnore;
	}

	@Override
	public String toString() {
		return "Explosion [dano=" + dano + ", targetsIgnore=" + targetsIgnore + "]";
	}

}
