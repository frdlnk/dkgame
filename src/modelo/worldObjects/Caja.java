package modelo.worldObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import modelo.componentes.Fisica;
import motor_v1.motor.Entidad;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.component.Transform;
import utils.Movible;
import motor_v1.motor.entidades.SpriteSolido;
import motor_v1.motor.util.Vector2D;
import utils.Colisionable;

public class Caja extends SpriteSolido implements Colisionable {


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
		return entidad.getColisiona().colisionaCon(colisiona);
	}

	@Override
	public void onColision(Entidad entidad) {
		if (entidad instanceof Movible) {
			Movible objeto = (Movible) entidad;
			Fisica fisicaOtro = objeto.getFisica();
			Rectangle colliderOtro = objeto.getColisiona().getHitbox();
			Rectangle thisHitBox = colisiona.getHitbox();
			Vector2D direccion = fisicaOtro.getUltimaDireccion().scale(-1).normalize();
			Double direccionY = direccion.getY();
			Transform transformarOtro = fisicaOtro.getTransform();
			Vector2D ultimaPosicion = fisicaOtro.getTransform().getPosicion();
			
			while(objeto.getColisiona().colisionaCon(colisiona) && !direccionY.isNaN()) {
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
			}else if(colliderOtro.getMaxY() == thisHitBox.getMinY() || colliderOtro.getMinY() == thisHitBox.getMaxY()) {
				transformarOtro.getPosicion().setX(ultimaPosicion.getX());
				double pos = transformar.getPosicion().getY();
				pos += colliderOtro.getMaxY() == thisHitBox.getMinY() ? 0:textura.getHeight();
				pos -= colliderOtro.getMaxY() == thisHitBox.getMinY() ? colliderOtro.getHeight():0;
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
	
	
	@Override
	public void dibujar(Graphics g) {
		super.dibujar(g);
	}
	
	public void drawMargins(Graphics g) {
		Rectangle tect = colisiona.getHitbox();
		Renderer.dibujarBordes(g, transformar.getPosicion(), tect.getWidth(), tect.getHeight());
	}
}
