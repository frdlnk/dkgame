package vista.zonas;

import java.awt.Graphics;

import motor_v1.motor.Entidad;
import motor_v1.motor.GameLoop;
import motor_v1.motor.component.Transform;
import motor_v1.motor.entidades.ListaEntidades;
import motor_v1.motor.entidades.Sprite;
import motor_v1.motor.util.Vector2D;
import utils.ColisionUtils;
import utils.Colisionable;
import utils.Conf;

public abstract class Zona extends Entidad{
	private Vector2D direccionMovimiento;
	protected ListaEntidades mapObjects;
	protected ListaEntidades staticObjects;
	protected ListaEntidades enemigos;
	private Vector2D position;
	
	public Zona(Vector2D direccionMovimiento, Vector2D posicion) {
		super();
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
	
	public void verificarColisiones(Colisionable colisionable, Entidad entidad) {
		ColisionUtils.entityColisionVerifier(mapObjects, colisionable, entidad);
		ColisionUtils.entityColisionVerifier(staticObjects, colisionable, entidad);
		ColisionUtils.entityColisionVerifier(enemigos, colisionable, entidad);
	}
	
	private void colisionesInternas() {
		verificarColisiones(enemigos);
		verificarColisiones(mapObjects);
		verificarColisiones(staticObjects);
	}
	
	private void verificarColisiones(ListaEntidades entidades) {
		for (int i = 0; i < entidades.getSize(); i++) {
			Entidad entidad = entidades.get(i);
			if (entidad instanceof Colisionable) {
				verificarColisiones((Colisionable) entidad, entidad);
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
		Vector2D movimeinto = direccion.normalize().scale(distancia*GameLoop.dt/1000);
		
		Vector2D nuevaPosicionZona = getPosition().add(movimeinto);
		
		if (direccionMovimiento.getX() < 0 && nuevaPosicionZona.getX() < 0) {
			nuevaPosicionZona.setX(0);
		}else if (direccionMovimiento.getX() > 0 && nuevaPosicionZona.getX() > 0) {
			nuevaPosicionZona.setX(0);
		}if (direccionMovimiento.getY() > 0 && nuevaPosicionZona.getY() > nuevaPosicionZona.getY()) {
			nuevaPosicionZona.setY(0);
		}else if(direccionMovimiento.getY() > 0 && nuevaPosicionZona.getY() > nuevaPosicionZona.getY()) {
			nuevaPosicionZona.setY(0);
		}
		
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
