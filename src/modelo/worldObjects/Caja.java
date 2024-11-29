package modelo.worldObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import modelo.componentes.Fisica;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.component.Transform;
import utils.colision.ColisionInfo;
import utils.colision.Colisionable;
import utils.interfaces.BorderDrawAble;
import utils.interfaces.Movible;
import motor_v1.motor.entidades.SpriteSolido;
import motor_v1.motor.util.Vector2D;

/**
 * Clase encargada de ser un objeto solido 
 */
public class Caja extends SpriteSolido implements Colisionable, BorderDrawAble {

	public Caja(String nombre, BufferedImage textura, Transform transformar) {
		super(nombre, textura, transformar);
	}
	
	@Override
	public void actualizar() {
		super.actualizar();
		colisiona.actualizar();
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
				return colision;
			}
		}
		return null;
	}

	@Override
	public void onColision(ColisionInfo colision) {
		if (colision.getEntidad() instanceof Movible) {
			Movible entidadmovible = (Movible) colision.getEntidad();
			Fisica fisicaOtro = entidadmovible.getFisica();
			Rectangle colliderOtro = entidadmovible.getColisiona().getHitbox();
			Rectangle thisHitBox = colisiona.getHitbox();
			Vector2D direccion = fisicaOtro.getUltimaDireccion().scale(-1).normalize();
			Double direccionY = direccion.getY();
			Transform transformarOtro = fisicaOtro.getTransform();
			Vector2D ultimaPosicion = fisicaOtro.getTransform().getPosicion();
			
			//mueve el objeto en direccion contraria hasta que deje de colisionar
			while(colision.getColider().colisionaCon(colisiona) && !direccionY.isNaN()) {
				Vector2D nuevaPosiocion = transformarOtro.getPosicion().add(direccion);
				transformarOtro.setPosicion(nuevaPosiocion);
				entidadmovible.getColisiona().actualizar(); 
			}
			
			//verifica el lado desde donde colisiono para eliminar sus fuerzas correspondientes
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
			//actualiza la entidad
			entidadmovible.getColisiona().actualizar();
		}
	}

	
	@Override
	public void dibujar(Graphics g) {
		super.dibujar(g);
	}
	
	@Override
	public void drawBorders(Graphics g) {
		Rectangle tect = colisiona.getHitbox();
		Renderer.dibujarBordes(g, transformar.getPosicion(), tect.getWidth(), tect.getHeight());
	}

	@Override
	public Collider[] getColliders() {
		return new Collider[] {colisiona};
	}
}
