package modelo.armamento.municiones;

import java.awt.Color;
import java.awt.Rectangle;

import modelo.arrays.ArrayString;
import modelo.componentes.Fisica;
import motor_v1.motor.Entidad;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.component.Transform;
import motor_v1.motor.entidades.Sprite;
import motor_v1.motor.util.Vector2D;
import utils.colision.ColisionInfo;
import utils.colision.Colisionable;
import utils.constants.Conf;
import utils.constants.Tags;
import utils.interfaces.Movible;

/**
 * Clase encargada del funcionamiento de una municion
 * 
 * Filtra las colisiones e implementa los impactos de las municiones
 * 
 * @author Joshua Elizondo Vasquez
 * @see Sprite, Colisionable, Movible
 */
public abstract class Municion extends Sprite implements Colisionable, Movible{
	protected Collider colisiona;
	protected Fisica fisica;
	private double velocity;
	protected ArrayString targetIgnore;
	private double dano;

	/**
	 * Construye una Municion
	 * @param nombre 	String tag que recibe la entidad
	 * @param posicion 	Vector2D posicion inicial
	 * @param direccion Vector2D direccion a seguir de la Municion
	 * @param targetsIgnore Lista de tags a ignorar para impactar
	 * @param dano double 	cantidad de dano que hace la municion a las entidades objetivo
	 */
	public Municion(Vector2D posicion, Vector2D direccion, ArrayString targetsIgnore, double dano, double velocity) {
		super(Tags.MUNICION);
		Rectangle rect = new Rectangle(15,10);
		Color color = new Color(255,153,0);
		this.textura = Renderer.crearTextura(rect, color);
		this.velocity = velocity;
		transformar = new Transform(posicion);
	    int width = this.textura.getWidth();
	    int height = this.textura.getHeight();
	    this.colisiona = new Collider(this.transformar, width, height);
	    this.fisica = new Fisica(1,0,transformar);
		fisica.impulsar(direccion.scale(this.velocity));
		renderer = new Renderer(transformar, textura);
		this.targetIgnore = targetsIgnore;
		targetsIgnore.add(Tags.MUNICION);
		this.dano = dano;
	}
	
	public Municion() {
		super(Tags.MUNICION);
	}
	
	/**
	 * Actualiza los componentes escenciales y sus limites
	 */
	@Override
	public void actualizar() {
		fisica.actualizar();
		colisiona.actualizar();
		calcularLimites();
		super.actualizar();
	}
	
	/**
	 * Calcula los limites de la municion en pantalla
	 */
	private void calcularLimites() {
		if(transformar.getPosicion().getX() > Conf.WINDOW_WIDTH
		|| transformar.getPosicion().getX() < 0
		|| transformar.getPosicion().getY() < 0
		|| transformar.getPosicion().getY() > Conf.WINDOW_HEIGHT)
		{
			destruir();
		}
	}
	
	/**
	 * Logica cuando la bala impacta contra un objetivo
	 * @param entidad
	 */
	protected abstract void impacto(Entidad entidad);
	
	/**
	 * Realiza el filtro de objetos a impactar
	 */
	@Override
	public void onColision(ColisionInfo colision) {
		if (!targetIgnore.contains(colision.getEntidad().getNombre())) {
			impacto(colision.getEntidad());
		}
	}

	/**
	 *Verifica si el objeto se encuentra en colision con otro
	 */
	@Override
	public ColisionInfo hayColision(Colisionable entidad) {
		//Si la bala ya a impactado no puede colisionar de nuevo
		if (!getViva()) {
			return null;
		}
		ColisionInfo colision = new ColisionInfo();
		Collider[] collidersEntidad = entidad.getColliders();
		for (int i = 0; i < collidersEntidad.length; i++) {
			Collider otroCollider = collidersEntidad[i];
			if (colisiona.colisionaCon(otroCollider)) {
				colision.setColider(colisiona);
				colision.setEntidad(this);
				colision.setColisionable(this);
				colision.setTriger(true);
				return colision;
			}
		}
		return null;
		
	}
	
	@Override
	public Collider[] getColliders() {
		return new Collider[]{colisiona};
	}

	public double getDano() {
		return dano;
	}

	public void setDano(double dano) {
		this.dano = dano;
	}

	@Override
	public Fisica getFisica() {
		return fisica;
	}


	public void setColisiona(Collider colisiona) {
		this.colisiona = colisiona;
	}

	public void setFisica(Fisica fisica) {
		this.fisica = (Fisica) fisica;
	}
	
	@Override
	public void setTransformar(Transform transformar) {
		fisica.setTransform(transformar);
		super.setTransformar(transformar);
		colisiona = new Collider(this.transformar, textura.getWidth(), textura.getHeight());
	}

}
