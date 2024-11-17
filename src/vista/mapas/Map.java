package vista.mapas;


import java.awt.Graphics;

import motor_v1.motor.Entidad;
import motor_v1.motor.GameLoop;
import motor_v1.motor.util.Vector2D;
import utils.Colisionable;
import utils.arrays.ArrayZona;
import vista.zonas.Zona;

public abstract class Map extends Entidad{
	protected ArrayZona zonas;
	private Zona zonaActual;
	private Zona zonaAnterior;
	private int indiceZonaActual;
	private boolean isChangingZone;

	public Map(ArrayZona zonas) {
		this.zonas = zonas;
		crearZonas();
		indiceZonaActual = 0;
		zonaActual = zonas.get(0);
		isChangingZone = false;
	}
	
	public Map() {
		this(new ArrayZona());
	}
	
	protected abstract void crearZonas();
	
	public void nextZone(Zona zone) {
		if (!isChangingZone) {
			zonaAnterior = zonaActual;
			zonaActual = zone;
			isChangingZone = true;
		}
	}

	
	public void generarColisiones(Colisionable colisionable) {
		zonaActual.verificarColisiones(colisionable);
	}
	
	public void actualizar() {
		if (isChangingZone) {
			double distancia = zonaActual.getPosition().getX();
			Vector2D direccion = Vector2D.LEFT.scale(distancia*GameLoop.dt);
			zonaActual.moverZona(direccion);
			zonaAnterior.moverZona(direccion);
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
	

	public ArrayZona getZonas() {
		return zonas;
	}

	public void setZonas(ArrayZona zonas) {
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
