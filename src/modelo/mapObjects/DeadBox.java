package modelo.mapObjects;

import java.awt.image.BufferedImage;

import modelo.entidades.Soldado;
import motor_v1.motor.Entidad;
import motor_v1.motor.component.Transform;
import motor_v1.motor.util.Vector2D;

public class DeadBox extends TriggerBox{

	public DeadBox(String nombre, BufferedImage textura, Transform transformar) {
		super(nombre, textura, transformar);
	}
	
	public DeadBox(String nombre, BufferedImage textura, Vector2D posicion) {
		super(nombre, textura, posicion);
	}

	@Override
	public void onColision(Entidad entidad) {
		
		if (entidad instanceof Soldado) {
			Soldado soldado = (Soldado) entidad;
			soldado.morir();
		}
	}

}
