package modelo.armamento;

import java.awt.image.BufferedImage;

import modelo.arrays.ArrayString;
import modelo.entidades.Soldado;
import modelo.worldObjects.TriggerBox;
import motor_v1.motor.Entidad;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Transform;
import motor_v1.motor.util.Vector2D;
import utils.colision.ColisionInfo;
import utils.colision.Colisionable;

/**
 * Objeto encargado de representar una explosion que genere dano
 * 
 * @author Joshua Elizondo Vasquez
 * @see TriggerBox
 */
public class Explosion extends TriggerBox{
	private double dano;
	private ArrayString targetsIgnore;
	
	
	/**
	 * Crea una nueva explosion con dano predeterminado de 10
	 * @param nombre
	 * @param textura
	 * @param transformar
	 * @param targetsIgnore
	 */
	public Explosion(String nombre, BufferedImage textura, Transform transformar, ArrayString targetsIgnore) {
		super(nombre, textura, transformar);
		colisiona.actualizar();
		dano = 10;
		this.targetsIgnore = targetsIgnore;
	}
	

	/**
	 * Crea una nueva explosion con los parametros suministrados
	 * @param nombre	Tag asignado a esta explosion
	 * @param textura	Imagen que dibujara en pantalla
	 * @param posicion	Vector2D con la posicion de la explosion
	 * @param targetsIgnore Lista de tags que ignorara
	 */
	public Explosion(String nombre, BufferedImage textura, Vector2D posicion, ArrayString targetsIgnore) {
		super(nombre, textura, posicion);
		dano = 10;
		colisiona.actualizar();
		this.targetsIgnore = targetsIgnore;
	}

	@Override
	public void onColision(ColisionInfo colision) {
		if (colision.getEntidad() instanceof Soldado 
			&& !targetsIgnore.contains(colision.getEntidad().getNombre())) 
		{
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
	 * @param soldado Soldado al que se hara dano
	 */
	private void hitSoldado(Soldado soldado) {
		soldado.recibirDano(dano);
	}
	
	@Override
	public void actualizar() {
		//solo existe un frame
		destruir();
		super.actualizar();
	}


	@Override
	public Collider[] getColliders() {
		return new Collider[]{colisiona};
	}
}
