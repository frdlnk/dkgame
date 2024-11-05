package modelo.armamento.municiones;

import java.awt.Color;
import java.awt.Rectangle;

import modelo.componentes.Fisica;
import motor_v1.motor.Entidad;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Movement;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.component.Transform;
import motor_v1.motor.entidades.Sprite;
import motor_v1.motor.util.Vector2D;
import utils.Array;
import utils.Colisionable;
import utils.Conf;

public abstract class Municion extends Sprite implements Colisionable{
	protected Movement movimiento;
	protected Collider colisiona;
	protected Fisica fisica;
	public final static double velocity = 7;
	protected Array<String> targetIgnore;
	private double dano;

	public Municion(String nombre, Vector2D posicion, Vector2D direccion, Array<String> targetsIgnore, double dano) {
		super(nombre);
		Rectangle rect = new Rectangle(15,10);
		Color color = new Color(255,153,0);
		this.textura = Renderer.crearTextura(rect, color);
		this.movimiento = new Movement(0.5, Vector2D.ZERO);
		transformar = new Transform(posicion);
	    int width = this.textura.getWidth();
	    int height = this.textura.getHeight();
	    this.colisiona = new Collider(this.transformar, width, height);
	    this.fisica = new Fisica(1,0,transformar);
	    
		fisica.impulsar(direccion.scale(velocity));
		fisica.setAceleracion(1);
		renderer = new Renderer(transformar, textura);
		this.targetIgnore = targetsIgnore;
	}
	
	@Override
	public void actualizar() {
		fisica.acelerar();
		fisica.actualizar();
		colisiona.actualizar();
		calcularLimites();
		super.actualizar();
	}
	
	private void calcularLimites() {
		if(transformar.getPosicion().getX() > Conf.WINDOW_WIDHT
		|| transformar.getPosicion().getX() < 0
		|| transformar.getPosicion().getY() < 0
		|| transformar.getPosicion().getY() > Conf.WINDOW_HEIGT)
		{
			destruir();
		}
	}
	
	protected abstract void impacto(Entidad entidad);
	
	@Override
	public void onColision(Entidad entidad) {
		if (!targetIgnore.contains(entidad.getNombre())) {
			impacto(entidad);
			destruir();
		}
	}

	@Override
	public boolean hayColision(Colisionable entidad) {
		if (!getViva()) {
			return false;
		}
		if (entidad instanceof Colisionable) {
			Collider entidadCollider = ((Colisionable) entidad).getColisiona();
			return colisiona.colisionaCon(entidadCollider);
		}
		return false;
	}

	@Override
	public Collider getColisiona() {
		return colisiona;
	}

	@Override
	public boolean isTrigger() {
		return false;
	}

	public double getDano() {
		return dano;
	}

	public void setDano(double dano) {
		this.dano = dano;
	}

}
