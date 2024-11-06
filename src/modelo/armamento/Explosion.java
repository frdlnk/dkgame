package modelo.armamento;

import java.awt.image.BufferedImage;

import modelo.entidades.Soldado;
import modelo.mapObjects.TriggerBox;
import motor_v1.motor.Entidad;
import motor_v1.motor.component.Transform;
import motor_v1.motor.util.Vector2D;
import utils.Array;
import utils.Colisionable;

/**
 * Objeto encargado de representar una explosion que realice dano
 */
public class Explosion extends TriggerBox{
	//private double tiempoDeExplosion;
	//private double factorDeDanoXDistancia;
	//private Vector2D epicentroRelativo = Vector2D.ZERO;
	private double dano;
	private Array<String> targetsIgnore;
	
	
	/**
	 * Crea una nueva explosion con dano predeterminado de 10
	 * @param nombre
	 * @param textura
	 * @param transformar
	 * @param targetsIgnore
	 */
	public Explosion(String nombre, BufferedImage textura, Transform transformar, Array<String> targetsIgnore) {
		super(nombre, textura, transformar);
		colisiona.actualizar();
		dano = 10;
		this.targetsIgnore = targetsIgnore;
	}
	

	public Explosion(String nombre, BufferedImage textura, Vector2D posicion, Array<String> targetsIgnore) {
		super(nombre, textura, posicion);
		dano = 10;
		colisiona.actualizar();
		this.targetsIgnore = targetsIgnore;
	}

	@Override
	public void onColision(Entidad entidad) {
		if (entidad instanceof Soldado && !targetsIgnore.contains(entidad.getNombre())) {
			hitSoldado((Soldado) entidad);
		}
	}
	
	@Override
	public boolean hayColision(Colisionable entidad) {
		if (!getViva()) {
			return false;
		}
		return super.hayColision(entidad);
	}

	private void hitSoldado(Soldado soldado) {
		//TODO: generar el epicentro y calculo de la explosion para dano dinamico segun distancia
		//Vector2D epicentroExp = transformar.getPosicion().add(epicentroRelativo);
		//double distanciaSoldado = epicentroRelativo.subtract(soldado.getCentro()).getMagnitude()+1;
		//double danoConseguido = (dano/distanciaSoldado)*factorDeDanoXDistancia;
		soldado.recibirDano(dano);
	}
	
	@Override
	public void actualizar() {
		//TODO crear timer de duracion y controlar colision unica
		//solo existe un segundo
		destruir();
		super.actualizar();
	}
}
