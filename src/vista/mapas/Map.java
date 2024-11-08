package vista.mapas;


import java.awt.Graphics;

import motor_v1.motor.Entidad;
import motor_v1.motor.util.Vector2D;
import utils.Array;
import utils.Colisionable;

public abstract class Map extends Entidad{
	protected Array<Zona> zonas;
	private Zona zonaActual;
	private Zona zonaAnterior;
	private int indiceZonaActual;
	private boolean isChangingZone;

	public Map(Array<Zona> zonas) {
		this.zonas = zonas;
		crearZonas();
	}
	
	public Map() {
		this.zonas = new Array<>();
		crearZonas();
		indiceZonaActual = 0;
		zonaActual = zonas.get(0);
		isChangingZone = false;
	}
	
	protected abstract void crearZonas();
	
	public void nextZone() {
		if (!isChangingZone) {
			indiceZonaActual++;
			zonaAnterior = zonaActual;
			if (indiceZonaActual >= zonas.getSize()) {
				indiceZonaActual = 0;
			}
			zonaActual = zonas.get(indiceZonaActual);
			isChangingZone = true;
		}
	}

	public Zona getActualZone() {
		return zonas.get(indiceZonaActual);
	}
	
	public void generarColisiones(Colisionable colisionable, Entidad entidad) {
		zonaActual.verificarColisiones(colisionable, entidad);
	}
	
	public void actualizar() {
		if (isChangingZone) {
			Vector2D direccion = zonaActual.getDireccionMovimiento().normalize();
			zonaActual.moverZona(direccion);
			zonaAnterior.moverZona(direccion);
			System.out.println(zonaActual.getPosition().getX());
			System.out.println(zonaActual.getPosition().getY());
			if (zonaActual.getPosition().getX() == 0) {
				isChangingZone = false;
				zonaAnterior = null;
			}
		}
		zonaActual.actualizar();
	}
	
	public void dibujar(Graphics g) {
		zonaActual.dibujar(g);
		if (isChangingZone) {
			zonaAnterior.dibujar(g);
		}
	}
	
	@Override
	public void destruir() {
		
	}

	public Array<Zona> getZonas() {
		return zonas;
	}

	public void setZonas(Array<Zona> zonas) {
		this.zonas = zonas;
	}

	public Zona getZonaActual() {
		return zonaActual;
	}

	public void setZonaActual(Zona zonaActual) {
		this.zonaActual = zonaActual;
	}

	public Zona getZonaAnterior() {
		return zonaAnterior;
	}

	public void setZonaAnterior(Zona zonaAnterior) {
		this.zonaAnterior = zonaAnterior;
	}

	public int getIndiceZonaActual() {
		return indiceZonaActual;
	}

	public void setIndiceZonaActual(int indiceZonaActual) {
		this.indiceZonaActual = indiceZonaActual;
	}

	public boolean isChangingZone() {
		return isChangingZone;
	}

	public void setChangingZone(boolean isChangingZone) {
		this.isChangingZone = isChangingZone;
	}
	
	
}
