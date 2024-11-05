package modelo.mapObjects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import modelo.componentes.Fisica;
import motor_v1.motor.Entidad;
import motor_v1.motor.component.Transform;
import motor_v1.motor.entidades.Movible;
import motor_v1.motor.entidades.SpriteSolido;
import motor_v1.motor.util.Vector2D;
import utils.Colisionable;

public class Caja extends SpriteSolido implements Colisionable {

	public Caja(String nombre) {
		super(nombre);
	}

	public Caja(String nombre, BufferedImage textura, Transform transformar) {
		super(nombre, textura, transformar);
	}

	public Caja(String nombre, BufferedImage textura, Vector2D posicion) {
		super(nombre, textura, posicion);
	}
	
	@Override
	public void actualizar() {
		super.actualizar();
		colisiona.actualizar();
	}

	@Override
	public boolean hayColision(Colisionable entidad) {
		return ((Colisionable) entidad).getColisiona().colisionaCon(colisiona);
	}

	@Override
	public void onColision(Entidad entidad) {
		if (entidad instanceof Movible && ((Movible) entidad).getFisica() instanceof Fisica) {
			Movible objeto = (Movible) entidad;
			Fisica fisicaOtro = (Fisica) objeto.getFisica();
			Rectangle colliderOtro = objeto.getColisiona().getHitbox();
			Rectangle thisHitBox = colisiona.getHitbox();
			Vector2D direccion = fisicaOtro.getUltimaDireccion().normalize().scale(-1);
			Transform transformarOtro = fisicaOtro.getTransform();
			Vector2D ultimaPosicion = fisicaOtro.getTransform().getPosicion();
			while(objeto.getColisiona().colisionaCon(colisiona)) {
				Vector2D nuevaPosiocion = transformarOtro.getPosicion().add(direccion);
				transformarOtro.setPosicion(nuevaPosiocion);
				objeto.getColisiona().actualizar(); 
			}
			
			
			if(colliderOtro.getMaxX() == thisHitBox.getMinX() || colliderOtro.getMinX() == thisHitBox.getMaxX()) {
				transformarOtro.getPosicion().setY(ultimaPosicion.getY());
				double pos = transformar.getPosicion().getX();
				pos += colliderOtro.getMaxX() == thisHitBox.getMinX() ? 0:textura.getWidth();
				pos -= colliderOtro.getMaxX() == thisHitBox.getMinX() ? colliderOtro.getWidth():0;
				transformarOtro.getPosicion().setX(pos);
				fisicaOtro.getVectorMovimiento().setX(0);
				fisicaOtro.getUltimaDireccion().setX(0);
			}else if(colliderOtro.getMaxY() <= thisHitBox.getMinY() || colliderOtro.getMinY() >= thisHitBox.getMaxY()) {
				transformarOtro.getPosicion().setX(ultimaPosicion.getX());
				double pos = transformar.getPosicion().getY();
				pos += colliderOtro.getMaxY() <= thisHitBox.getMinY() ? 0:textura.getHeight();
				pos -= colliderOtro.getMaxY() <= thisHitBox.getMinY() ? colliderOtro.getHeight():0;
				pos += 0.5;
				transformarOtro.getPosicion().setY(pos);
				fisicaOtro.getVectorMovimiento().setY(0);
				fisicaOtro.getUltimaDireccion().setY(0);
			}
			objeto.getColisiona().actualizar();
		}
	}

	@Override
	public boolean isTrigger() {
		return false;
	}
	
	

}
