package modelo.worldObjects;

import java.awt.image.BufferedImage;

import modelo.entidades.Soldado;
import motor_v1.motor.Entidad;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Transform;
import motor_v1.motor.util.Vector2D;
import utils.ColisionInfo;

public class DeadBox extends TriggerBox{

	public DeadBox(String nombre, BufferedImage textura, Transform transformar) {
		super(nombre, textura, transformar);
	}
	
	public DeadBox(String nombre, BufferedImage textura, Vector2D posicion) {
		super(nombre, textura, posicion);
	}

	@Override
	public void onColision(ColisionInfo colision) {
		
		if (colision.getEntidad() instanceof Soldado) {
			Soldado soldado = (Soldado) colision.getEntidad();
			soldado.morir();
		}
	}

	@Override
	public Collider[] getColliders() {
		return new Collider[] {colisiona};
	}

}
