package modelo.entidades;

import java.awt.image.BufferedImage;

import motor_v1.motor.component.Transform;
import motor_v1.motor.entidades.SpriteSolido;
import motor_v1.motor.util.Vector2D;
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
	public boolean hayColision(Colisionable entidad) {
		return colisiona.colisionaCon(entidad.getColisiona());
	}

	
	@Override
	public boolean isTrigger() {
		return false;
	}
}