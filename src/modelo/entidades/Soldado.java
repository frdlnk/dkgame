package modelo.entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import modelo.armamento.armas.Arma;
import modelo.componentes.Fisica;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Transform;
import motor_v1.motor.entidades.SpriteMovible;
import motor_v1.motor.util.Vector2D;
import utils.colision.ColisionInfo;
import utils.colision.Colisionable;
import utils.interfaces.Movible;

/**
 * Entidad base para los personajes del juego
 */
public abstract class Soldado extends SpriteMovible implements Colisionable, Movible {
	protected double salud;
	private Arma arma;
	protected Fisica fisica;

	/**
	 * Crea un nuevop soldado
	 * @param nombre	Tag del soldado	
	 * @param imagenes	Gif mostrar
	 * @param transformar posicion inicial del soldado
	 * @param duracionImagen 
	 */
	public Soldado(String nombre, BufferedImage imagen, Transform transformar, double salud) {
		super(nombre, imagen, transformar);
		this.salud = salud;
	}
	
	@Override
	public void actualizar() {
		if (arma != null) {
			arma.actualizar();
		}
		super.actualizar();
	}

	@Override
	public void dibujar(Graphics g) {
		Vector2D pos = transformar.getPosicion();
		pos.setY(pos.getY()+.5);
		super.dibujar(g);
		pos.setY(pos.getY()-.5);
	}

	/**
	 * Recibe dano
	 * @param dano cantidad de dano recibido
	 */
	public abstract void recibirDano(double dano);
	
	/**
	 * Logica para cuando muere el soldado
	 */
	public abstract void morir();
	
	/**
	 * Debe generar la logica de disparo
	 */
	public abstract void disparar();
	
	/**
	 * calcula la posicion en la que se crea la municion
	 * @return posicion de la municion
	 */
	protected abstract Vector2D posicionDisparo();

	public Arma getArma() {
		return arma;
	}

	public void setArma(Arma arma) {
		this.arma = arma;
	}

	public Fisica getFisica() {
		return fisica;
	}
	
	public void setFisica(Fisica fisica) {
		this.fisica = fisica;
	}
	
	public double getSalud() {
		return salud;
	}


	public void setSalud(double salud) {
		this.salud = salud;
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
				colision.setTriger(false);
				return colision;
			}
		}
		return null;
	}
	
}
