package vista.zonas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ctrl.gameControlers.Game;
import modelo.UserConfig;
import modelo.componentes.RelativeTransform;
import modelo.worldObjects.Caja;
import modelo.worldObjects.Plataforma;
import motor_v1.motor.Entidad;
import motor_v1.motor.GameLoop;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.component.Transform;
import motor_v1.motor.entidades.ListaEntidades;
import motor_v1.motor.entidades.Sprite;
import motor_v1.motor.input.InputKeyboard;
import motor_v1.motor.input.Key;
import motor_v1.motor.util.Vector2D;
import utils.colision.ColisionUtils;
import utils.colision.Colisionable;
import utils.constants.Conf;
import utils.constants.Tags;
import utils.interfaces.BorderDrawAble;

/**
 * Base para crear zonas o niveles del juego
 * 
 * Permite el modo disenador
 * 
 */
public abstract class Zona extends Entidad implements Colisionable {
	protected ListaEntidades mapObjects;
	protected ListaEntidades staticObjects;
	protected ListaEntidades enemigos;
	protected Transform transformar;
	protected UserConfig config;

	// Variables del modo diseño
	private boolean modoDiseno;
	private Sprite plat1;
	private int actualPlatform;
	private double contx;
	private double conty;
	private double changeplatDelay;

	public Zona(Vector2D posicion, boolean modoDiseno) {
		super();
		config = Game.configuracion;
		transformar = new Transform(posicion);
		this.mapObjects = new ListaEntidades();
		this.staticObjects = new ListaEntidades();
		this.enemigos = new ListaEntidades();
		this.modoDiseno = modoDiseno;
		changeplatDelay = 0;
		crearComponentes();
		generarEnemigos();
		if (modoDiseno) {
			setFirstDesignPlatform();
		}
	}

	/**
	 * Busca el primer Elemento que se pueda mover para el modo disenador
	 */
	private void setFirstDesignPlatform() {
		actualPlatform = 0;
		Entidad entidad = mapObjects.get(actualPlatform);
		while (mapObjects.getSize() > actualPlatform && !(entidad instanceof Sprite)) {
			plat1 = (Sprite) entidad;
			actualPlatform++;
		}
		if (plat1 == null) {
			modoDiseno = false;
		}
	}

	/**
	 * Crea una plataforma en el mapa, y la agrega a los objetos del mapa
	 * 
	 * @param pos    posicion de la plataforma en la zona
	 * @param width  longitud de la plataforma
	 * @param height altura ed la plataforma
	 * @return la plataforma creada
	 */
	protected Plataforma createPlatform(Vector2D pos, int width, int height) {
		Rectangle dimensiones = new Rectangle(width, height);
		Color color = new Color(128, 50, 0);
		BufferedImage image = Renderer.crearTextura(dimensiones, color);
		Transform transform = new RelativeTransform(pos, this.transformar);
		Plataforma platform = new Plataforma(image, transform);
		platform.getColisiona().actualizar();
		mapObjects.add(platform.getNombre(), platform);
		return platform;
	}

	/**
	 * Crea una caja en el mapa, y la agrega a los objetos del mapa
	 * 
	 * @param pos    posicion de la caja en la zona
	 * @param width  longitud de la caja
	 * @param height altura ed la caja
	 * @return la caja creada
	 */
	protected Caja createCaja(Vector2D pos, int width, int height) {
		Rectangle dimensiones = new Rectangle(width, height);
		Color color = new Color(128, 50, 0, 50);
		BufferedImage image = Renderer.crearTextura(dimensiones, color);
		Transform transform = new RelativeTransform(pos, this.transformar);
		Caja caja = new Caja(Tags.STATIC_OBJECT, image, transform);
		caja.getColisiona().actualizar();
		mapObjects.add(caja.getNombre(), caja);
		return caja;
	}

	/**
	 * Anade entidades al mapa
	 * 
	 * @param entidad la entidad a anadir
	 */
	public void addMapObjects(Entidad entidad) {
		if (entidad instanceof Sprite) {
			Sprite entSprite = (Sprite) entidad;
			Vector2D relativePos = entSprite.getTransformar().getPosicion().subtract(transformar.getPosicion());
			Transform transform = new RelativeTransform(relativePos, transformar);
			entSprite.setTransformar(transform);
		}
		mapObjects.add(entidad.getNombre(), entidad);
	}

	/**
	 * Crea e inicializa los objetos iniciales el mapa
	 */
	protected abstract void crearComponentes();

	/**
	 * Crea e inicializa los enemigos iniciales del mapa
	 */
	protected abstract void generarEnemigos();

	/**
	 * Verifica las colisiones de un Colisionable con todos los objetos del mapa
	 * 
	 * @param colisionable objeto a verificar
	 */
	public void verificarColisiones(Colisionable colisionable) {
		ColisionUtils.entityColisionVerifier(staticObjects, colisionable);
		ColisionUtils.entityColisionVerifier(mapObjects, colisionable);
		ColisionUtils.entityColisionVerifier(enemigos, colisionable);
	}

	/**
	 * Calcula las colisiones de los objetos del mapa
	 */
	private void colisionesInternas() {
		verificarColisiones(enemigos);
		verificarColisiones(mapObjects);
		verificarColisiones(staticObjects);
	}

	/**
	 * Verifica las colisiones de una lista de entidades con los objetos del mapa
	 * 
	 * @param entidades
	 */
	public void verificarColisiones(ListaEntidades entidades) {
		for (int i = 0; i < entidades.getSize(); i++) {
			Entidad entidad = entidades.get(i);
			if (entidad instanceof Colisionable) {
				verificarColisiones((Colisionable) entidad);
			}
		}
	}

	@Override
	public void actualizar() {
		staticObjects.actualizar();
		mapObjects.actualizar();
		enemigos.actualizar();
		colisionesInternas();
		if (modoDiseno) {
			actualizarModoDiseno();
		}
		destruir();
	}

	@Override
	public void destruir() {
		staticObjects.destruir();
		mapObjects.destruir();
		enemigos.destruir();
	}

	@Override
	public void dibujar(Graphics g) {
		staticObjects.dibujar(g);
		mapObjects.dibujar(g);
		enemigos.dibujar(g);
		if (modoDiseno && plat1 instanceof BorderDrawAble) {
			((BorderDrawAble) plat1).drawBorders(g);
		}
	}

	/**
	 * Mueve la zona en una direccion a la velocidad de la configuracion
	 * {@value Conf#VELOCIDAD_CAMBIO_DE_ZONAS}
	 * 
	 * @param direccion direccion de movimiento
	 */
	public void moverZona(Vector2D direccion) {
		moverZona(direccion, Conf.VELOCIDAD_CAMBIO_DE_ZONAS);
	}

	/**
	 * Mueve la zona en una direccion especificada y una diatancia especificada
	 * 
	 * @param direccion direccion de movimiento
	 * @param distancia que recorrera en esa direccion
	 */
	public void moverZona(Vector2D direccion, double distancia) {
		Vector2D movimeinto = direccion.normalize().scale(distancia * GameLoop.dt);

		Vector2D nuevaPosicionZona = transformar.getPosicion().add(movimeinto);

		transformar.setPosicion(nuevaPosicionZona);

	}

	/**
	 * Actualizar habilitado para el funcionamiento del modo disenador
	 */
	private void actualizarModoDiseno() {
		// con enter puede cambiar entre la plataforma selecionada
		if (InputKeyboard.isDown(Key.ENTER) && changeplatDelay <= 0) {
			// enter mas shift la plataforma anterior
			if (InputKeyboard.isDown(Key.SHIFT)) {
				actualPlatform = actualPlatform - 1 < 0 ? mapObjects.getSize() - 1 : actualPlatform - 1;
			} else {
				actualPlatform = actualPlatform + 1 >= mapObjects.getSize() ? 0 : actualPlatform + 1;
			}
			// setea el nuevo objeto a mover si es de tipo Sprite
			if (mapObjects.get(actualPlatform) instanceof Sprite) {
				plat1 = (Sprite) mapObjects.get(actualPlatform);
			}
			// resetea los valores de posicionamiento
			contx = 0;
			conty = 0;
			// resetea la variable de control para cambiar entre plataformas
			changeplatDelay = .5;
		} else {
			changeplatDelay -= GameLoop.dt;
		}

		// si apreta shift puede mover los objetos mas rapido
		double distance = 0.5;
		if (InputKeyboard.isDown(Key.SHIFT)) {
			distance = 5;
		}
		// calculo de nueva posicion del objeto
		Vector2D posicionPlat;
		if (plat1.getTransformar() instanceof RelativeTransform) {
			posicionPlat = ((RelativeTransform) plat1.getTransformar()).getRelativePosicion();
		} else {
			posicionPlat = transformar.getPosicion();
		}
		if (InputKeyboard.isKeyPressed(Key.UP)) {
			conty -= distance;
			Vector2D newpos = posicionPlat.add(new Vector2D(0, -distance));
			plat1.getTransformar().trasladarloA(newpos);
			System.out.println(conty);
		}
		if (InputKeyboard.isKeyPressed(Key.DOWN)) {
			conty += distance;
			Vector2D newpos = posicionPlat.add(new Vector2D(0, distance));
			plat1.getTransformar().trasladarloA(newpos);
			System.out.println(conty);
		}
		if (InputKeyboard.isKeyPressed(Key.LEFT)) {
			contx -= distance;
			Vector2D newpos = posicionPlat.add(new Vector2D(-distance, 0));
			plat1.getTransformar().trasladarloA(newpos);
			System.out.println(contx);
		}
		if (InputKeyboard.isKeyPressed(Key.RIGHT)) {
			contx += distance;
			Vector2D newpos = posicionPlat.add(new Vector2D(distance, 0));
			plat1.getTransformar().trasladarloA(newpos);
			System.out.println(contx);
		}
	}

	public int enemigosrestantes() {
		return enemigos.getSize();
	}

	public ListaEntidades getMapObjects() {
		return mapObjects;
	}

	public void setMapObjects(ListaEntidades mapObjects) {
		this.mapObjects = mapObjects;
	}

	public ListaEntidades getStaticObjects() {
		return staticObjects;
	}

	public void setStaticObjects(ListaEntidades staticObjects) {
		this.staticObjects = staticObjects;
	}

	public ListaEntidades getEnemigos() {
		return enemigos;
	}

	public void setEnemigos(ListaEntidades enemigos) {
		this.enemigos = enemigos;
	}

	public Transform getTransformar() {
		return transformar;
	}

	public void setTransformar(Transform transformar) {
		this.transformar = transformar;
	}

	public UserConfig getConfig() {
		return config;
	}

	public void setConfig(UserConfig config) {
		this.config = config;
	}

	public boolean isModoDiseno() {
		return modoDiseno;
	}

	public void setModoDiseno(boolean modoDiseno) {
		this.modoDiseno = modoDiseno;
	}

	public Sprite getPlat1() {
		return plat1;
	}

	public void setPlat1(Sprite plat1) {
		this.plat1 = plat1;
	}

	public int getActualPlatform() {
		return actualPlatform;
	}

	public void setActualPlatform(int actualPlatform) {
		this.actualPlatform = actualPlatform;
	}

	public double getContx() {
		return contx;
	}

	public void setContx(double contx) {
		this.contx = contx;
	}

	public double getConty() {
		return conty;
	}

	public void setConty(double conty) {
		this.conty = conty;
	}

	public double getChangeplatDelay() {
		return changeplatDelay;
	}

	public void setChangeplatDelay(double changeplatDelay) {
		this.changeplatDelay = changeplatDelay;
	}

	@Override
	public String toString() {
		return "Zona [mapObjects=" + mapObjects + ", staticObjects=" + staticObjects + ", enemigos=" + enemigos
				+ ", transformar=" + transformar + ", config=" + config + ", modoDiseno=" + modoDiseno + ", plat1="
				+ plat1 + ", actualPlatform=" + actualPlatform + ", contx=" + contx + ", conty=" + conty
				+ ", changeplatDelay=" + changeplatDelay + "]";
	}

}
