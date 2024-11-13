package modelo.entidades;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import modelo.armamento.armas.Arma;
import modelo.armamento.armas.LanzaCohetes;
import modelo.armamento.municiones.Municion;
import modelo.componentes.Fisica;
import modelo.spritesCargados.SpritesPlayer;
import modelo.worldObjects.Caja;
import motor_v1.motor.Entidad;
import motor_v1.motor.GameLoop;
import motor_v1.motor.Scene;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.component.Transform;
import motor_v1.motor.input.InputMouse;
import motor_v1.motor.util.Vector2D;
import utils.*;
import utils.arrays.ArrayString;
import vista.escena.EscenaJuego;

public class Player extends Soldado{
	public final static int speed = 40;
	public final static int jumpForce = 8;
	private double yVelocity;
	private Vector2D direccionDisparo;
	private boolean isGrounded;
	private boolean isMoving;
	private PlayerControls controles;
	private boolean estaAgachado;
	private SpritesPlayer spritesPlayer;

	public Player(String nombre, BufferedImage[] imagenes, Transform transformar, double duracionImagen) {
		this(nombre,imagenes,transformar.getPosicion(),duracionImagen);
	}

	public Player(String nombre, BufferedImage[] imagenes, Vector2D posicion, double duracionImagen) {
		super(nombre, imagenes, posicion, duracionImagen);
		spritesPlayer = new SpritesPlayer(this.getTransformar());
		colisiona.getHitbox().setLocation((int)posicion.getX(), (int)posicion.getY());
		fisica = new Fisica(1,1.5,transformar);
		yVelocity = 0;
		direccionDisparo = Vector2D.RIGHT;
		isGrounded = false;
		setArma(new LanzaCohetes());
		controles = new PlayerControls();
		estaAgachado = false;
		salud = 10;
	}
	

	@Override
	public void actualizar() {
		continuaEnSuelo();
		
		controles.actualizar();
		double direccionHorizontal = controles.getDireccionHorizontal();
		double direccionVertical = controles.getDireccionVertical();
		
		calcularDireccionDisparo(direccionHorizontal, direccionVertical);
		
		movimiento(direccionHorizontal);
		
		disparar();
		
		fisica.actualizar();
		calcularLimitesHorizontales();
		colisiona.actualizar();
		spritesPlayer.actualizar();
		super.actualizar();
	}
	
	private void movimiento(double direccionHorizontal) {
		double movimiento = speed;
		isMoving = true;
		spritesPlayer.cambiarAnimacionA(Assets.spriteNames[0]);
		if(controles.getDireccionVertical() == 1 && isGrounded && !estaAgachado) {
			agacharse();
		}else if(controles.getDireccionVertical() != 1 && estaAgachado){
			pararse();
		}
		if (estaAgachado) {
			movimiento *= 0.8;
			direccionDisparo.setY(0);
		}
		if (controles.isJump() && isGrounded) {
			isGrounded = false;
			fisica.impulsar(Vector2D.UP.scale(jumpForce));
		}
		fisica.addForce(new Vector2D(direccionHorizontal*movimiento*GameLoop.dt/100,0));
	}
	
	private void calcularDireccionDisparo(double direccionHorizontal, double direccionVertical) {
		double y = estaAgachado ? 0 : direccionVertical;
		double x = y != 0 || direccionHorizontal != 0 ? direccionHorizontal : direccionDisparo.getX(); 
		
		direccionDisparo = new Vector2D(x,y);
	}
	
	private void agacharse() {
		direccionDisparo.setY(0);
		transformar.escalarloA(new Vector2D(1,.5));
		Vector2D posicion = transformar.getPosicion();
		posicion.setY(posicion.getY()+40);
		estaAgachado = true;
	}
	
	private void pararse() {
		transformar.escalarloA(Vector2D.ONE);
		Vector2D posicion = transformar.getPosicion();
		posicion.setY(posicion.getY()-40);
		estaAgachado = false;
	}

	public void calcularLimitesHorizontales() {
		Vector2D posicion = transformar.getPosicion();
		if (posicion.getX() < 0) {
			posicion.setX(0);
			fisica.getVectorMovimiento().setX(0);
		} 
		if(posicion.getX() + Conf.PLAYER_WIDTH > Conf.WINDOW_WIDTH){
			posicion.setX(Conf.PLAYER_WIDTH - Conf.WINDOW_WIDTH);
			fisica.getVectorMovimiento().setX(0);
		}
	}
	
	
	public void disparar() {
		if (InputMouse.isPressed()) {
			spritesPlayer.cambiarAnimacionA(Assets.spriteNames[1]);
			Scene escena = Scene.getEscenaActual();
			ArrayString targetsIgnore = new ArrayString();
			targetsIgnore.add(Tags.PLAYER);
			Municion disparo = getArma().disparar(getCentro(), direccionDisparo, targetsIgnore);
			if (escena instanceof EscenaJuego && disparo != null) {
				((EscenaJuego) escena).addEntidad(disparo);
			}
		}
	}
	
	@Override
	public void dibujar(Graphics g) {
		Rectangle rect = colisiona.getHitbox();
		Vector2D posicion = new Vector2D(rect.getX(), rect.getY());
		Renderer.dibujarBordes(g, posicion, rect.getWidth(), rect.getHeight());
		spritesPlayer.dibujar(g);
		super.dibujar(g);
	}
	
	private void continuaEnSuelo() {
		isGrounded = isGrounded && fisica.getVectorMovimiento().getY() == 0;
	}

	public double getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(double yVelocity) {
		this.yVelocity = yVelocity;
	}

	public Fisica getFisica() {
		return fisica;
	}

	public void setFisica(Fisica fisica) {
		this.fisica = fisica;
	}

	public Vector2D getUltimaDirecionHorizontal() {
		return direccionDisparo;
	}

	public void setUltimaDirecionHorizontal(Vector2D ultimaDirecionHorizontal) {
		this.direccionDisparo = ultimaDirecionHorizontal;
	}

	public boolean isGrounded() {
		return isGrounded;
	}

	public void setGrounded(boolean isGrounded) {
		this.isGrounded = isGrounded;
	}

	@Override
	public void onColision(Entidad entidad) {
		if (entidad instanceof Recogible) {
			Object reward = ((Recogible) entidad).getReward();
			if (reward instanceof Arma) {
				setArma((Arma) reward);
			}
		}
		if(entidad instanceof Caja) {
			Rectangle otroHitbox = ((Colisionable) entidad).getColisiona().getHitbox();
			if(otroHitbox.getMinY() >= colisiona.getHitbox().getMaxY()-1 && fisica.getUltimaDireccion().getY() >= 0) {
				isGrounded = true;
			}
		}
	}

	@Override
	public void morir() {
		destruir();
	}

	@Override
	public void recibirDano(double dano) {
		salud -= dano;
		if (salud <= 0) {
			salud = 0;
			morir();
		}
	}
	
}
