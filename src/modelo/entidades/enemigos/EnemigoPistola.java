package modelo.entidades.enemigos;

import java.awt.*;
import java.awt.image.BufferedImage;

import ctrl.gameControlers.Game;
import modelo.armamento.armas.Pistola;
import modelo.armamento.municiones.Municion;
import modelo.arrays.ArrayString;
import modelo.entidades.Player;
import modelo.spritesCargados.SpritesEnemy;
import motor_v1.motor.GameLoop;
import motor_v1.motor.Scene;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Transform;
import motor_v1.motor.util.Vector2D;
import utils.colision.ColisionInfo;
import utils.constants.Assets;
import utils.constants.Conf;
import vista.escena.EscenaJuego;

/**
 * {@link Enemigo} basico que dispara hacia el jugador
 * 
 * @see Enemigo
 */
public class EnemigoPistola extends Enemigo {
	//constantes de movimiento
	public final static int VALOR_PUNTAJE = 300;
	public final static int MUNICIONES = 1000;
	public final static double SHOOT_DELAY = 1.5;
	public final static int SPEED = 100;
	public final static int VIDA = 50;
	public final static int TIEMPO_DISPARANDO = 3;
	public final static int DANO_BASE = 10;
	//propiedades de control
	private boolean huir;
	private double tiempoDeDisparo;

	private SpritesEnemy sprites;

	/**
	 * Crea un nuevo enemigo con {@value #VIDA} de vida y una pistola con {@value #MUNICIONES} balas
	 * @param imagen principal de posicion y dimension
	 * @param posicion inicial del enemigo
	 * @param duracionImagen duracion del gif
	 */
	public EnemigoPistola(BufferedImage imagen, Transform posicion) {
		super(imagen, posicion, VIDA, VALOR_PUNTAJE);
		Double dano = DANO_BASE*Game.getConfiguracion().getMultiplicadorDanoEnemigo();
		setArma(new Pistola(SHOOT_DELAY,MUNICIONES, dano));
		sprites = new SpritesEnemy(this);
		sprites.cambiarAnimacionA(Assets.enemNames[3]);
		huir = false;
		tiempoDeDisparo = 2;
	}

	@Override
	public void onColision(ColisionInfo colision) {
		//si no se encuentra en tiempo de disparo y choca con eljugador huye
		if (colision .getEntidad() instanceof Player && tiempoDeDisparo < 0) {
			huir = puedeHuir(getDistanciaJugador(getCentro()));
		}
	}
	
	@Override
	public void actualizar() {
		//si debe huir lo hace
		if (isInScreen()) {
			sprites.cambiarAnimacionA(Assets.enemNames[3]);
			if (huir) {
				Vector2D distanciaJugador = getDistanciaJugador(getCentro());
				if (distanciaJugador != null) {
					//huye hasta verificar que se va a salir del mapa o esta los suficientemente lejos de l jugador
					Vector2D direccion = new Vector2D(distanciaJugador.getX(),0).normalize().scale(-1);
					fisica.addForce(direccion.scale(SPEED*GameLoop.dt));
					sprites.cambiarAnimacionA(Assets.enemNames[4]);
					huir = puedeHuir(distanciaJugador);
					tiempoDeDisparo = TIEMPO_DISPARANDO;
				}
				
			}else{
				//si no esta huyendo y esta en pantalla dispara
				disparar();
				sprites.cambiarAnimacionA(Assets.enemNames[5]);
			}
			tiempoDeDisparo -= GameLoop.dt;
			sprites.actualizar();
			super.actualizar();
		}
	}
	
	/**
	 * verifica si el enemigo pude huir del jugador
	 * @param distanciaJugador distancia hasta el jugador
	 * @return si debe huir o no
	 */
	private boolean puedeHuir(Vector2D distanciaJugador) {
		//no se sale de la pantalla y se aleja suficiente del jugador
		return Math.abs(distanciaJugador.getX()) < 120 
				&& transformar.getPosicion().getX() > 30
				&& transformar.getPosicion().getX() < Conf.WINDOW_WIDTH-30;
	}

	@Override
	public void disparar() {
		Scene escena = Scene.getEscenaActual();
		if (escena instanceof EscenaJuego) {
			Vector2D posicionDis = posicionDisparo();
			Vector2D direccion = getDireccionJugador(posicionDis);
			ArrayString targetsIgnore = new ArrayString();
			targetsIgnore.add(getNombre());
			Municion disparo = getArma().disparar(posicionDis, direccion, targetsIgnore);
			if(disparo != null) {
				((EscenaJuego) escena).addEntidad(disparo);
			}
		}
	}
	

	@Override
	public void morir() {
		super.morir();
		destruir();
	}

	@Override
	protected Vector2D posicionDisparo() {
		int disBalaX = 60;
		int disBalaY = -20;
		Vector2D direccionDisparo = getDireccionJugador(getCentro());
		int x = direccionDisparo.getX() > 0 ? disBalaX : -disBalaX;
		int y = disBalaY;
		
		Vector2D pos = getCentro().add(new Vector2D(x, y));
		return pos;
	}
	
	@Override
	public Collider[] getColliders() {
		return new Collider[] {colisiona};
	}

	@Override
	public void dibujar(Graphics g){
		sprites.dibujar(g);
	}

	public boolean isHuir() {
		return huir;
	}

	public void setHuir(boolean huir) {
		this.huir = huir;
	}

	public double getTiempoDeDisparo() {
		return tiempoDeDisparo;
	}

	public void setTiempoDeDisparo(double tiempoDeDisparo) {
		this.tiempoDeDisparo = tiempoDeDisparo;
	}

	public SpritesEnemy getSprites() {
		return sprites;
	}

	public void setSprites(SpritesEnemy sprites) {
		this.sprites = sprites;
	}

	@Override
	public String toString() {
		return "EnemigoPistola [huir=" + huir + ", tiempoDeDisparo=" + tiempoDeDisparo + ", sprites=" + sprites + "]";
	}
	
	
}
