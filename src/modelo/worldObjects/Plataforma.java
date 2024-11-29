package modelo.worldObjects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import modelo.componentes.Fisica;
import motor_v1.motor.component.Transform;
import utils.colision.ColisionInfo;
import utils.colision.Colisionable;
import utils.constants.Tags;
import utils.interfaces.Movible;

/**
 * Caja que solo se activa desde arriba
 * @implNote se puede abstraer para crea un objeto colisionable segun angulo de entrada
 */
public class Plataforma extends Caja {


	/**
	 * Crea una nueva plataforma
	 * @param textura imagen a mostrar
	 * @param transformar posicion inicial
	 */
	public Plataforma(BufferedImage textura, Transform transformar) {
		super(Tags.PLATFORM, textura, transformar);
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
