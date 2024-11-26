package modelo.worldObjects;

import java.awt.image.BufferedImage;

import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Transform;
import motor_v1.motor.entidades.SpriteSolido;
import motor_v1.motor.util.Vector2D;
import utils.colision.ColisionInfo;
import utils.colision.Colisionable;

public abstract class TriggerBox extends SpriteSolido implements Colisionable{

	public TriggerBox(String nombre, BufferedImage textura, Vector2D posicion) {
		super(nombre, textura, posicion);
	}

	public TriggerBox(String nombre, BufferedImage textura, Transform transformar) {
		super(nombre, textura, transformar);
	}

	@Override
	public ColisionInfo hayColision(Colisionable entidad) {
		ColisionInfo colision = new ColisionInfo();
		Collider[] collidersEntidad = entidad.getColliders();
		for (int i = 0; i < collidersEntidad.length; i++) {
			Collider otroCollider = collidersEntidad[i];
			if (colisiona.colisionaCon(otroCollider)) {
				colision.setColider(colisiona);
				colision.setEntidad(this);
				colision.setColisionable(this);
				colision.setTriger(true);
				return colision;
			}
		}
		return null;
	}

}
