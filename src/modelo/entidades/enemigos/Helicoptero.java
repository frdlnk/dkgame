package modelo.entidades.enemigos;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import modelo.armamento.municiones.Choete;
import modelo.entidades.Player;
import motor_v1.motor.GameLoop;
import motor_v1.motor.Scene;
import motor_v1.motor.component.Collider;
import motor_v1.motor.util.Vector2D;
import utils.ColisionInfo;
import utils.Colisionable;
import utils.Conf;
import utils.RelativeTransform;
import utils.Tags;
import utils.arrays.ArrayString;
import vista.escena.EscenaJuego;

public class Helicoptero extends Enemigo {
	private final static int SPEED = 80;
	private final static double TIEMPO_ENTRE_BOMBAS = .4;
	private final static double TIEMPO_ENTRE_BOMBARDEOS = 1.5;
	private final static int CANTIDAD_BOMBAS_POR_BOMBARDEO = 3;
	private int bombasArrojadas;
	private boolean bombardear;
	private double tiempoParaSiguienteBomba;
	private double tiempoParaSiguienteBombardeo;
	private Collider triggerBombardeo;

	public Helicoptero(String nombre, BufferedImage[] imagenes, Vector2D posicion, double duracionImagen) {
		super(nombre, imagenes, posicion, duracionImagen);
		fisica.setGravity(0);
		transformar.getPosicion().setY(80);
		bombasArrojadas = 0;
		tiempoParaSiguienteBomba = 0;
		tiempoParaSiguienteBombardeo = 0;
		Vector2D posColliderB = new Vector2D(imagenes[0].getWidth()/2-10,imagenes[0].getHeight());
		RelativeTransform transBomb= new RelativeTransform(posColliderB, transformar);
		Rectangle hitBoxBombardero = new Rectangle(20,Conf.WINDOW_HEIGHT);
		triggerBombardeo = new Collider(transBomb, hitBoxBombardero);
	}
	
	@Override
	public void actualizar() {
		if (isInScreen()) {
			
			if (bombardear) {
				if (tiempoParaSiguienteBomba < 0) {
					disparar();
					bombasArrojadas++;
					tiempoParaSiguienteBomba = TIEMPO_ENTRE_BOMBAS;
				}
				if (bombasArrojadas == CANTIDAD_BOMBAS_POR_BOMBARDEO) {
					bombardear = false;
					tiempoParaSiguienteBombardeo = TIEMPO_ENTRE_BOMBARDEOS;
				}
				tiempoParaSiguienteBomba -= GameLoop.dt;
			}else {
				tiempoParaSiguienteBombardeo -= GameLoop.dt;
			}
			
			Vector2D direccionJugador = getDireccionJugador(getCentro());
			if (direccionJugador != null) {
				direccionJugador.setY(0);
				fisica.addForce(direccionJugador.scale(SPEED*GameLoop.dt));
			}
			
			triggerBombardeo.actualizar();
			super.actualizar();
		}
		
	}

	@Override
	public void onColision(ColisionInfo colision) {
		if (colision.getEntidad() instanceof Player && !bombardear && tiempoParaSiguienteBombardeo < 0) {
			bombardear = true;
			bombasArrojadas = 0;
		}
	}
	
	@Override
	public ColisionInfo hayColision(Colisionable entidad) {
		ColisionInfo colision =  super.hayColision(entidad);
		if (colision != null) {
			return colision;
		}
		if (entidad instanceof Player && triggerBombardeo.colisionaCon(((Player) entidad).getColisiona())) {
			return new ColisionInfo(this, this, triggerBombardeo, true);
		}
		return null;
	}

	@Override
	public void morir() {
		destruir();
	}

	@Override
	public void disparar() {
		Scene escenaActual = Scene.getEscenaActual();
			
		//Crea y agrega la granada al escenario
		ArrayString targetsIgnored = new ArrayString();
		targetsIgnored.add(getNombre());
		int dano = 30;
		Choete granada = new Choete(Tags.EXPLOCION, posicionDisparo(), Vector2D.ZERO, targetsIgnored, dano);
		if (escenaActual instanceof EscenaJuego) {
			((EscenaJuego) escenaActual).addEntidad(granada);
		}
	}

	@Override
	protected Vector2D posicionDisparo() {
		return getCentro();
	}

	@Override
	public void dibujar(Graphics g) {
		super.dibujar(g);
	}
	
	@Override
	public Collider[] getColliders() {
		return new Collider[] {colisiona, triggerBombardeo};
	}
}
