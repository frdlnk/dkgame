package modelo.entidades;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ctrl.gameControlers.Game;
import modelo.armamento.armas.Arma;
import modelo.armamento.municiones.Municion;
import modelo.arrays.ArrayString;
import modelo.componentes.Fisica;
import modelo.componentes.RelativeTransform;
import modelo.entidades.enemigos.Enemigo;
import modelo.spritesCargados.SpritesPlayer;
import modelo.worldObjects.Caja;
import motor_v1.motor.Entidad;
import motor_v1.motor.GameLoop;
import motor_v1.motor.Scene;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.component.Transform;
import motor_v1.motor.input.InputMouse;
import motor_v1.motor.util.Vector2D;
import utils.PlayerControls;
import utils.colision.ColisionInfo;
import utils.constants.ArmasDisponibles;
import utils.constants.Assets;
import utils.constants.Conf;
import utils.constants.Tags;
import vista.escena.EscenaJuego;

/**
 * Esta clase se encarga de la logica de control para un personaje jugable 
 * 
 * @author Joshua Elizondo Vasquez
 * @see Soldado
 */
public class Player extends Soldado{
	//cosntantes
	public final static double SALUD = 100.0;//px/s
	public final static int SPEED = 400;//px/s
	public final static int JUMP_FORCE = 8;//fuerza puntual aplicada
	public final static double DELAY_CUCHILLO = .3;
	
	//variables de posicion y estado
	private int vidasRestantes;
	private boolean isGrounded;
	private boolean isTouchingEnemy;
	private boolean estaAgachado;
	
	//variables de direccion
	private Vector2D direccionDisparo;
	
	//variables de cuchillo
	private boolean usaCuchillo;
	private double cuchilloDelay;
	private Collider cuchilloColider;
	
	//variables de movimiento
	private boolean isMoving;
	private PlayerControls controles;
	
	//variables de diseño
	private SpritesPlayer spritesPlayer;
	private int puntaje;
	
	/**
	 * Crea un nuevo jugador 
	 * @param imagenes gif a presentar
	 * @param transformar inicial del jugador
	 * @param duracionImagen 
	 */
	public Player(BufferedImage[] imagenes, Transform transformar, double duracionImagen, int vidasRestantes) {
		super(Tags.PLAYER,imagenes,transformar,duracionImagen, SALUD);
		spritesPlayer = new SpritesPlayer(this);
		colisiona.actualizar();
		fisica = new Fisica(1,1.5,transformar);
		direccionDisparo = Vector2D.RIGHT;
		isGrounded = false;
		String tipoArma = Game.getConfiguracion().getArmainicial();
		setArma(ArmasDisponibles.generarArmaGenerica(tipoArma));
		double dano = getArma().getDano()* Game.getConfiguracion().getMultiplicadorDano();
		getArma().setDano(dano);
		controles = new PlayerControls();
		estaAgachado = false;
		Vector2D posCuchillo = new Vector2D(Conf.PLAYER_WIDTH, Conf.PLAYER_HEIGHT/2);
		Transform transformChuchillo = new RelativeTransform(posCuchillo, transformar);
		cuchilloColider = new Collider(transformChuchillo, 30, 20);
		cuchilloDelay = DELAY_CUCHILLO;
		setPuntaje(0);
	}

	@Override
	public void actualizar() {
		continuaEnSuelo();
		
		//conseguir ejes de movimiento
		controles.actualizar();
		double direccionHorizontal = controles.getDireccionHorizontal();
		double direccionVertical = controles.getDireccionVertical();

		checkMovementSprites(direccionHorizontal, direccionVertical);
		movimiento(direccionHorizontal);
		
		
		calcularDireccionDisparo(direccionHorizontal, direccionVertical);
		disparar();
		
		fisica.actualizar();
		calcularLimitesHorizontales();
		colisiona.actualizar();
		cuchilloColider.actualizar();
		spritesPlayer.actualizar();
		isTouchingEnemy = false;
		
		if (cuchilloDelay > 0) {
			cuchilloDelay -= GameLoop.dt;
		}
		
		//arma default
		if (getArma().getBalasRestantes() < 1) {
			String tipoArma = Game.getConfiguracion().getArmainicial();
			setArma(ArmasDisponibles.generarArmaGenerica(tipoArma));
		}
		
		super.actualizar();
	}
	
	/**
	 * se encarga del movimiento del personaje
	 * @param direccionHorizontal eje direcional en x para el movimiento
	 */
	private void movimiento(double direccionHorizontal) {
		double movimiento = SPEED;
		isMoving = true;
		//decide si esta agachado
		if(controles.getDireccionVertical() == 1 && isGrounded && !estaAgachado) {
			agacharse();
		}else if(controles.getDireccionVertical() != 1 && estaAgachado){
			pararse();
		}
		//si esta agachado se mueve mas lento
		if (estaAgachado) {
			movimiento *= 0.8;
		}
		//solo salta si esta en el suelo t presina la teclka de salto
		if (controles.isJump() && isGrounded) {
			isGrounded = false;
			fisica.impulsar(Vector2D.UP.scale(JUMP_FORCE));
			if (estaAgachado) pararse();
		}
		//anade la fuerza segun la direccion dada
		fisica.addForce(new Vector2D(direccionHorizontal*movimiento*GameLoop.dt,0));
	}

	private void checkMovementSprites(double direccionHorizontal, double direccionVertical) {
		if (estaAgachado){
			spritesPlayer.cambiarAnimacionA(Assets.spriteNames[3]);
		}
		if (direccionHorizontal != 0 && estaAgachado){
			spritesPlayer.cambiarAnimacionA(Assets.spriteNames[5]);
		}
		if (!estaAgachado){
			spritesPlayer.cambiarAnimacionA(Assets.spriteNames[0]);
		}
		if (direccionVertical < 0){
			spritesPlayer.cambiarAnimacionA(Assets.spriteNames[6]);
		}
	}

	/**
	 * Calcula la direccion en la que esta viendo para disparar
	 * @param direccionHorizontal eje direccional en x 
	 * @param direccionVertical eje direccional en y
	 */
	private void calcularDireccionDisparo(double direccionHorizontal, double direccionVertical) {
		double y = estaAgachado ? 0 : direccionVertical;
		double x = direccionHorizontal; 
		
		//si la direccion en x es 0 en el input y en la ultima direccion de disparo
		// su direccion predeterminada sera adelante
		if(x == 0 && direccionDisparo.getX() == 0) x=1;
		
		//sino y la direccion de input es 0 usa la ultima direccion que uso
		else if(x==0) x = direccionDisparo.getX();
		//si no se cumplen esas condiciones la direccion sera el nuevo input del jugador(implicito)
		
		//si apunta verticalmente sin moverse, dispara solo verticalmente
		if(y!=0 && direccionHorizontal == 0) x=0;
		
		direccionDisparo = new Vector2D(x,y);
	}
	
	/**
	 * Hace agacjarse al jugador
	 */
	private void agacharse() {
		direccionDisparo.setY(0);
		transformar.escalarloA(new Vector2D(1,.5));
		Vector2D posicion = transformar.getPosicion();
		posicion.setY(posicion.getY()+40);
		estaAgachado = true;
	}
	
	/**
	 * Para al jugador 
	 */
	private void pararse() {
		transformar.escalarloA(Vector2D.ONE);
		Vector2D posicion = transformar.getPosicion();
		posicion.setY(posicion.getY()-40);
		estaAgachado = false;
	}

	/**
	 * Calcula los limites de pantalla
	 */
	public void calcularLimitesHorizontales() {
		Vector2D posicion = transformar.getPosicion();
		if (posicion.getX() < 0) {
			posicion.setX(0);
			fisica.getVectorMovimiento().setX(0);
		} 
		if(posicion.getX() + Conf.PLAYER_WIDTH > Conf.WINDOW_WIDTH){
			posicion.setX(Conf.WINDOW_WIDTH - Conf.PLAYER_WIDTH);
			fisica.getVectorMovimiento().setX(0);
		}
	}
	
	
	/**
	 * dispara con el arma actual del jugador
	 */
	public void disparar() {
		usaCuchillo = false;
		if (InputMouse.isPressed() && cuchilloDelay <= 0) {
			if (isTouchingEnemy) {
				usaCuchillo = true;
				cuchilloDelay = DELAY_CUCHILLO;
			}else {
				if (estaAgachado){
					spritesPlayer.cambiarAnimacionA(Assets.spriteNames[4]);
				} else if (direccionDisparo.getY() < 0) {
					spritesPlayer.cambiarAnimacionA(Assets.spriteNames[2]);
				} else {
					spritesPlayer.cambiarAnimacionA(Assets.spriteNames[1]);
				}
				Scene escena = Scene.getEscenaActual();
				ArrayString targetsIgnore = new ArrayString();
				targetsIgnore.add(getNombre());
				Municion disparo = getArma().disparar(posicionDisparo(), direccionDisparo, targetsIgnore);
				if (escena instanceof EscenaJuego && disparo != null) {
					((EscenaJuego) escena).addEntidad(disparo);
				}
			}
		}
	}
	
	@Override
	protected Vector2D posicionDisparo() {
		int x = direccionDisparo.getX() > 0 ? 67 : -67;
		int y = estaAgachado ? 0 : -20;
		
		if (direccionDisparo.getX() == 0) {
			x = 0;
			y += direccionDisparo.getY() > 0 ? 50 : -50;
		}
		Vector2D pos = getCentro().add(new Vector2D(x, y));
		return pos;
	}
	
	@Override
	public void dibujar(Graphics g) {
		Rectangle rect = colisiona.getHitbox();
		Vector2D posicion = new Vector2D(rect.getX(), rect.getY());
		Renderer.dibujarBordes(g, posicion, rect.getWidth(), rect.getHeight());
		rect = cuchilloColider.getHitbox();
		posicion = new Vector2D(rect.getX(), rect.getY());
		Renderer.dibujarBordes(g, posicion, rect.getWidth(), rect.getHeight());
		spritesPlayer.dibujar(g);
		super.dibujar(g);
	}
	
	@Override
	public void onColision(ColisionInfo colision) {
		Entidad entidad = colision.getEntidad();
		//si colisiona con un recogible
		if (entidad instanceof Recogible) {
			Object reward = ((Recogible) entidad).getReward();
			if (reward instanceof Arma) {
				setArma((Arma) reward);
			}
			setPuntaje(getPuntaje() + ((Recogible)entidad).getPuntos());
		}
		//si colisiona con una caja
		else if(entidad instanceof Caja) {
			Rectangle otroHitbox = colision.getColider().getHitbox();
			//is grounded
			if(otroHitbox.getMinY() >= colisiona.getHitbox().getMaxY()-1 && fisica.getUltimaDireccion().getY() >= 0) {
				isGrounded = true;
			}
		}
		//si colisiona con un enemigo
		else if (entidad instanceof Enemigo) {
			isTouchingEnemy = entidad.getViva();
			//puede usar el cuchillo
			if (usaCuchillo && cuchilloColider.colisionaCon(colision.getColider())) {
				((Enemigo) entidad).recibirDano(50);
				usaCuchillo = false;
			}
		}
	}
	
	@Override
	public void morir() {
		setVidasRestantes(getVidasRestantes() - 1);
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

	@Override
	public Collider[] getColliders() {
		return new Collider[] {colisiona,cuchilloColider};
	}
	
	/**
	 * verifica que aun continue en el suelo
	 */
	private void continuaEnSuelo() {
		isGrounded = isGrounded && fisica.getVectorMovimiento().getY() == 0;
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

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public void recibirPuntos(int puntos) {
		puntaje += puntos;
	}

	public int getVidasRestantes() {
		return vidasRestantes;
	}

	public void setVidasRestantes(int vidasRestantes) {
		this.vidasRestantes = vidasRestantes;
	}
}
