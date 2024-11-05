package modelo.maps;

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
	private Vector2D position;
	
	public Zona(Vector2D direccionMovimiento, Vector2D posicion) {
		super();
		this.position = posicion;
		this.direccionMovimiento = direccionMovimiento;
		this.mapObjects = new ListaEntidades();
		this.staticObjects = new ListaEntidades();
		crearComponentes();
		posicionarZonaEstadoinicial();
	}
	
	protected abstract void crearComponentes();
	
	public void verificarColisiones(Colisionable colisionable, Entidad entidad) {
		ColisionUtils.entityColisionVerifier(mapObjects, colisionable, entidad);
		ColisionUtils.entityColisionVerifier(staticObjects, colisionable, entidad);
	}
	
	@Override
	public void actualizar() {
		staticObjects.actualizar();
		staticObjects.destruir();
		mapObjects.actualizar();
		mapObjects.destruir();
	}
	@Override
	public void destruir() {
		staticObjects.destruir();
		mapObjects.destruir();
	}
	@Override
	public void dibujar(Graphics g) {
		staticObjects.dibujar(g);
		mapObjects.dibujar(g);
	}
	
	public void moverZona(Vector2D direccion) {
		Vector2D movimeinto = direccion.normalize().scale(Conf.VELOCIDAD_CAMBIO_DE_ZONAS*GameLoop.dt/1000);
		
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
		
		for (int i = 0; i < mapObjects.getSize(); i++) {
			Entidad entidad = mapObjects.get(i);
			if (entidad instanceof Sprite) {
				Transform transformEntidad = ((Sprite) entidad).getTransformar();
				Vector2D nuevaPosicion = transformEntidad.getPosicion().add(movimeinto);
				transformEntidad.setPosicion(nuevaPosicion);
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
