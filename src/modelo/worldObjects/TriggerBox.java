package modelo.worldObjects;

import java.awt.image.BufferedImage;

import motor_v1.motor.component.Transform;
import motor_v1.motor.entidades.SpriteSolido;
import motor_v1.motor.util.Vector2D;
import utils.Colisionable;

public abstract class TriggerBox extends SpriteSolido implements Colisionable{

	public TriggerBox(String nombre, BufferedImage textura, Vector2D posicion) {
		super(nombre, textura, posicion);
	}

	public TriggerBox(String nombre, BufferedImage textura, Transform transformar) {
		super(nombre, textura, transformar);
	}

	@Override
	public boolean hayColision(Colisionable entidad) {
		return colisiona.colisionaCon(entidad.getColisiona());
	}

	@Override
	public boolean isTrigger() {
		return true;
	}

}
