package modelo.entidades;

import java.awt.image.BufferedImage;

import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Transform;
import motor_v1.motor.entidades.SpriteSolido;
import motor_v1.motor.util.Vector2D;
import utils.ColisionInfo;
import utils.Colisionable;

public abstract class Recogible extends SpriteSolido implements Colisionable {

	public Recogible(String nombre, BufferedImage textura, Vector2D posicion) {
		super(nombre, textura, posicion);
	}

	public Recogible(String nombre, BufferedImage textura, Transform transformar) {
		super(nombre, textura, transformar);
	}
	
	public abstract Object getReward();

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