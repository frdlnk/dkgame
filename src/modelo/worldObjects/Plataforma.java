package modelo.worldObjects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import modelo.componentes.Fisica;
import motor_v1.motor.component.Transform;
import utils.Movible;
import motor_v1.motor.util.Vector2D;
import utils.ColisionInfo;
import utils.Colisionable;

public class Plataforma extends Caja {


	public Plataforma(String nombre, BufferedImage textura, Transform transformar) {
		super(nombre, textura, transformar);
	}

	public Plataforma(String nombre, BufferedImage textura, Vector2D posicion) {
		super(nombre, textura, posicion);
	}

	@Override
	public ColisionInfo hayColision(Colisionable entidad) {
		if (entidad instanceof Movible ) 
		{
			//TODO: tratar de hacer con producto punto de la resta de pos-Jpos y Vector.UP
			Movible objeto = (Movible) entidad;
			Fisica fisicaOtro = objeto.getFisica();
			Rectangle otroHitbox = objeto.getColisiona().getHitbox();
			Rectangle thisHitBox = colisiona.getHitbox();  
			double thisYLevel = thisHitBox.getMaxY();
			double otroYLevel = otroHitbox.getMaxY();
			if(fisicaOtro.getUltimaDireccion().getY() >= 0 && otroYLevel <= thisYLevel) {
				boolean trigger = false;
				return new ColisionInfo(this,this,colisiona, trigger);
			}
			
		}
		return null;
	}

}
