package modelo.entidades.enemigos;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ctrl.gameControlers.Game;
import modelo.armamento.municiones.Choete;
import modelo.arrays.ArrayString;
import modelo.componentes.RelativeTransform;
import modelo.entidades.Player;
import modelo.spritesCargados.SpritesEnemy;
import motor_v1.motor.GameLoop;
import motor_v1.motor.Scene;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Transform;
import motor_v1.motor.util.Vector2D;
import utils.colision.ColisionInfo;
import utils.colision.Colisionable;
import utils.constants.Assets;
import utils.constants.Conf;
import vista.escena.EscenaJuego;

/**
 * {@link Enemigo} que vuela y bombardea al jugador
 * 
 * @see Enemigo
 * @see Choete
 */
public class Helicoptero extends Enemigo {
	//constantes de control y movimento
	public final static int VALOR_PUNTAJE = 500;
	private final static int SALUD = 150;
	private final static int SPEED = 80;
	private final static double TIEMPO_ENTRE_BOMBAS = .4;
	private final static double TIEMPO_ENTRE_BOMBARDEOS = 1.5;
	private final static int CANTIDAD_BOMBAS_POR_BOMBARDEO = 3;
	private static final double DANO_BASE = 20;
	//propiedades de control
	private int bombasArrojadas;
	private boolean bombardear;
	private double tiempoParaSiguienteBomba;
	private double tiempoParaSiguienteBombardeo;
	private Collider triggerBombardeo;

	private SpritesEnemy sprites;

	/**
	 * Crea un nuevo Helicoptero con {@value #SALUD} de vida
	 * @param imagenes
	 * @param posicion
	 */
	public Helicoptero(BufferedImage imagen, Transform posicion) {
		super(imagen, posicion, SALUD, VALOR_PUNTAJE);
		//sin gravedad porque vuela
		fisica.setGravity(0);
		//siempre en la posicion y del aire
		transformar.getPosicion().setY(80);
		bombasArrojadas = 0;
		tiempoParaSiguienteBomba = 0;
		tiempoParaSiguienteBombardeo = 0;
		//collider de bombardeo
		Vector2D posColliderB = new Vector2D(imagen.getWidth()/2-10,imagen.getHeight());
		RelativeTransform transBomb= new RelativeTransform(posColliderB, transformar);
		Rectangle hitBoxBombardero = new Rectangle(20,Conf.WINDOW_HEIGHT);
		triggerBombardeo = new Collider(transBomb, hitBoxBombardero);
		sprites = new SpritesEnemy(this);
	}
	
	@Override
	public void actualizar() {
		if (isInScreen()) {
			sprites.cambiarAnimacionA(Assets.enemNames[6]);
			bombardeoControl();
			
			//se mueve hacia el jugador
			Vector2D direccionJugador = getDireccionJugador(getCentro());
			if (direccionJugador != null) {
				direccionJugador.setY(0);
				fisica.addForce(direccionJugador.scale(SPEED*GameLoop.dt));
			}
			
			triggerBombardeo.actualizar();
			sprites.actualizar();
			super.actualizar();
		}
		
	}
	
	/**
	 * Logica de control para el arrojo de bombvas y cantidad de bombardeos por tiempo
	 */
	private void bombardeoControl() {
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
		//si colisiona con el cuerpo principal no se bombardea y se actua con la colision natural del Enemigo
		if (colision != null) {
			return colision;
		}
		//si hay una colision con el collider de bombardeo y el jugador emppieza el bombardeo
		if (entidad instanceof Player && triggerBombardeo.colisionaCon(((Player) entidad).getColisiona())) {
			return new ColisionInfo(this, this, triggerBombardeo, true);
		}
		return null;
	}

	@Override
	public void morir() {
		super.morir();
		destruir();
	}

	@Override
	public void disparar() {
		Scene escenaActual = Scene.getEscenaActual();
			
		//Crea y agrega los cohetes al escenario
		ArrayString targetsIgnored = new ArrayString();
		targetsIgnored.add(getNombre());
		double dano = DANO_BASE * Game.getConfiguracion().getMultiplicadorDanoEnemigo();
		Choete cohete = new Choete(posicionDisparo(), Vector2D.ZERO, targetsIgnored, dano);
		if (escenaActual instanceof EscenaJuego) {
			((EscenaJuego) escenaActual).addEntidad(cohete);
		}
	}

	@Override
	protected Vector2D posicionDisparo() {
		return getCentro();
	}

	@Override
	public void dibujar(Graphics g) {
		sprites.dibujar(g);
		super.dibujar(g);
	}
	
	@Override
	public Collider[] getColliders() {
		return new Collider[] {colisiona, triggerBombardeo};
	}


}
