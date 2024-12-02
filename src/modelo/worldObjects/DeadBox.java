package modelo.worldObjects;

import java.awt.image.BufferedImage;

import modelo.entidades.Soldado;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Transform;
import utils.colision.ColisionInfo;
import utils.constants.Tags;

/**
 * Clase encargada de matar a los soldados que la toquen
 */
public class DeadBox extends TriggerBox {

	/**
	 * Crea una nueva deadBox
	 * 
	 * @param textura     imagen a mostrar
	 * @param transformar posicion de la dead Box
	 */
	public DeadBox(BufferedImage textura, Transform transformar) {
		super(Tags.DEADBOX, textura, transformar);
		colisiona.actualizar();
	}

	@Override
	public void onColision(ColisionInfo colision) {
		// mata soldados
		if (colision.getEntidad() instanceof Soldado) {
			Soldado soldado = (Soldado) colision.getEntidad();
			soldado.morir();
		}
	}

	@Override
	public Collider[] getColliders() {
		return new Collider[] { colisiona };
	}

	@Override
	public String toString() {
		return "DeadBox []";
	}

}
