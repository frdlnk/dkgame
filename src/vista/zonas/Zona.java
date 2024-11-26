package vista.zonas;

import java.awt.Graphics;

import ctrl.gameControlers.Game;
import modelo.UserConfig;
import motor_v1.motor.Entidad;
import motor_v1.motor.GameLoop;
import motor_v1.motor.component.Transform;
import motor_v1.motor.entidades.ListaEntidades;
import motor_v1.motor.entidades.Sprite;
import motor_v1.motor.util.Vector2D;
import utils.Conf;
import utils.colision.ColisionUtils;
import utils.colision.Colisionable;

public abstract class Zona extends Entidad implements Colisionable{
	private Vector2D direccionMovimiento;
	protected ListaEntidades mapObjects;
	protected ListaEntidades staticObjects;
	protected ListaEntidades enemigos;
	private Vector2D position;
	protected UserConfig config; 
	
	public Zona(Vector2D direccionMovimiento, Vector2D posicion) {
		super();
		config = Game.configuracion;
		this.position = posicion;
		this.direccionMovimiento = direccionMovimiento;
		this.mapObjects = new ListaEntidades();
		this.staticObjects = new ListaEntidades();
		this.enemigos = new ListaEntidades();
		crearComponentes();
		generarEnemigos();
		posicionarZonaEstadoinicial();
	}
	
	public void addMapObjects(Entidad entidad) {
		mapObjects.add(entidad.getNombre(), entidad);
	}
	
	protected abstract void crearComponentes();
	protected abstract void generarEnemigos();
	
	public void verificarColisiones(Colisionable colisionable) {
		ColisionUtils.entityColisionVerifier(staticObjects, colisionable);
		ColisionUtils.entityColisionVerifier(mapObjects, colisionable);
		ColisionUtils.entityColisionVerifier(enemigos, colisionable);
	}
	
	private void colisionesInternas() {
		verificarColisiones(enemigos);
		verificarColisiones(mapObjects);
		verificarColisiones(staticObjects);
	}
	
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
	}
	
	public void moverZona(Vector2D direccion) {
		moverZona(direccion, Conf.VELOCIDAD_CAMBIO_DE_ZONAS);
	}
	
	public void moverZona(Vector2D direccion, double distancia) {
		Vector2D movimeinto = direccion.normalize().scale(distancia*GameLoop.dt);
		
		Vector2D nuevaPosicionZona = getPosition().add(movimeinto);
		
		setPosition(nuevaPosicionZona);
		
		moverEntidades(enemigos, movimeinto);
		moverEntidades(mapObjects, movimeinto);
	}
	
	private void moverEntidades(ListaEntidades entidades, Vector2D movimiento) {
		for (int i = 0; i < entidades.getSize(); i++) {
			Entidad entidad = entidades.get(i);
			if (entidad instanceof Sprite) {
				Transform transformEntidad = ((Sprite) entidad).getTransformar();
				Vector2D nuevaPosicion = transformEntidad.getPosicion().add(movimiento);
				transformEntidad.trasladarloA(nuevaPosicion);
			}
		}
	}
	
	public void posicionarZonaEstadoinicial() {
		for (int i = 0; i < mapObjects.getSize(); i++) {
			Entidad entidad = mapObjects.get(i);
			if (mapObjects.get(i) instanceof Sprite) {
				Transform transformEntidad = ((Sprite) entidad).getTransformar();
				Vector2D movimiento = getPosition();
				Vector2D nuevaPosicion = transformEntidad.getPosicion().add(movimiento);
				transformEntidad.setPosicion(nuevaPosicion);
			}
		}
	}
	
	public int enemigosrestantes(){
		return enemigos.getSize();
	}

	public Vector2D getDireccionMovimiento() {
		return direccionMovimiento;
	}

	public void setDireccionMovimiento(Vector2D direccionMovimiento) {
		this.direccionMovimiento = direccionMovimiento;
	}

	public ListaEntidades getMapObjects() {
		return mapObjects;
	}

	public void setMapObjects(ListaEntidades mapObjects) {
		this.mapObjects = mapObjects;
	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}
	
}
