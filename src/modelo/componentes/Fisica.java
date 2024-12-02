package modelo.componentes;

import modelo.arrays.ArrayVector2D;
import motor_v1.motor.GameLoop;
import motor_v1.motor.component.Movement;
import motor_v1.motor.component.Physics;
import motor_v1.motor.component.Transform;
import motor_v1.motor.util.Vector2D;
import utils.constants.Conf;

/**
 * Componente encargado del calculo de las fisicas de un objeto con transformar
 * 
 * Esta clase se encarga de calcuylar el siguiente movimiento de un 
 * objeto segun las fuerzas aplicadas, realizando el calculo en base a su masa y escala de gravedad
 * 
 * Puede generar friccion y aceleracion
 * 
 * @implNote El indice de gravedad es la cantidad de efecto que tiene la constante gravedad sobre un objeto
 * Para eliminar la gravedad de un objeto setea la gravedad de este objeto a 0
 * @implNote Si desea una fisica mas realista en objetos complejos use transform relativos
 *  al transforma asignado al componente
 *  
 * @author Joshua Elizondo Vasquez
 * @see Physics, Transform, Conf 
 */
public class Fisica extends Physics{
	private double gravity;
	private Vector2D vectorMovimiento;
	private Vector2D ultimaDireccion;
	private Transform transform;
	private int masa;
	private ArrayVector2D fuerzasAplicadas;
	
	/**
	 * Crea una nueva fisica aplicada a un trasnform, con masa = 1 y gravedad = 1
	 * @param transform transform asociado
	 */
	public Fisica(Transform transform) {
		this(1,1,transform);
	}

	/**
	 * Crea una nueva Fisica con la masa, y gravedad especificadas y el trasnform asociado
	 * 
	 * @implNote la escala de gravedad no define la gravedad del mundo si no la magnituid
	 * <br>en la que la gravedad del mundo {@value Conf#GRAVITY} afecta este objeto
	 * 
	 * @param masa		masa del objeto
	 * @param gravity	escala de gravedad del objeto
	 * @param transform	transform asociado
	 */
	public Fisica(int masa, double gravity, Transform transform) {
		this.gravity = gravity;
		this.transform = transform;
		this.masa = masa;
		setFriccion(0);
		vectorMovimiento = Vector2D.ZERO;
		ultimaDireccion = Vector2D.ZERO;
		fuerzasAplicadas = new ArrayVector2D();
	}
    
	/**
	 * Anade un impulso instantaneo al objeto
	 * @param vector impulso vectorial
	 */
	public void impulsar(Vector2D vector) {
		Vector2D newVector = new Vector2D(vector.getX()/masa, vector.getY()/masa);
		vectorMovimiento = vectorMovimiento.add(newVector);
	}
	
	/**
	 * Anade un impulso instantaneo al objeto en su direccion actual
	 * @param force fuerza de impulso 
	 */
	public void impulsar(double force) {
		vectorMovimiento = vectorMovimiento.add(force/masa);
	}
	
	/**
	 * anade una fuerza continua al objeto
	 * @param vector fuerza vectorial aplicada
	 */
	public void addForce(Vector2D vector) {
		Vector2D newVector = new Vector2D(vector.getX()/masa, vector.getY()/masa);
		fuerzasAplicadas.add(newVector);
		vectorMovimiento = vectorMovimiento.add(newVector);
	}
	
	/**
	 * anade una fuerza continua al objeto en su direccion actual
	 * @param force fuerza aplicada
	 */
	public void addForce(double force) {
		fuerzasAplicadas.add(Vector2D.ONE.scale(force/masa));
		vectorMovimiento = vectorMovimiento.add(force/masa);
	}
	
	/**
	 * genera el calculo de fuerzas para el movimiento fisico del objeto
	 */
	public void actualizar() {
		vectorMovimiento = vectorMovimiento.scale(1-getFriccion());
		Vector2D nuevaPosicion;
		if (transform instanceof RelativeTransform) {
			nuevaPosicion = ((RelativeTransform)transform).getRelativePosicion().add(vectorMovimiento);
		}else {
			nuevaPosicion = transform.getPosicion().add(vectorMovimiento);
		}
		ultimaDireccion = vectorMovimiento;
		for (int i = 0; i < fuerzasAplicadas.getSize(); i++) {
			Vector2D fuerzaAplicada = fuerzasAplicadas.get(i);
			vectorMovimiento = vectorMovimiento.subtract(fuerzaAplicada);
		}
		fuerzasAplicadas.clear();
		transform.trasladarloA(nuevaPosicion);
		vectorMovimiento.setY(vectorMovimiento.getY() + (gravity*Conf.GRAVITY*GameLoop.dt));
	}

	/**
	 * acelera el objeto en su direccion actual a la aceleracion previamente seteada
	 */
	public void acelerar() {
		vectorMovimiento = vectorMovimiento.scale(getAceleracion());
	}
	/**
	 * acelera el objeto en su direccion actual
	 * @param factorAceleracion factor de aceleracion a aplicar
	 */
	public void acelerar(double factorAceleracion) {
		vectorMovimiento = vectorMovimiento.scale(factorAceleracion);
	}
	
	/**
	 *Acelera un objeto con un {@link Movement} asociado
	 *{@link Deprecated} reemplazado por {@link #acelerar()} 
	 */
	@Override
	@Deprecated
	public void acelerar(Movement movimiento) {
		super.acelerar(movimiento);
	}
	
	/**
	 *Acelera un objeto con la aceleracion dada y un {@link Movement} asociado
	 *{@link Deprecated} reemplazado por {@link #acelerar(double)}
	 */
	@Override
	@Deprecated
	public void acelerar(Movement movimiento, double factorAceleracion) {
		super.acelerar(movimiento, factorAceleracion);
	}
	
	public Vector2D getUltimaDireccion() {
		return ultimaDireccion;
	}

	public void setUltimaDireccion(Vector2D ultimaDireccion) {
		this.ultimaDireccion = ultimaDireccion;
	}

	public int getMasa() {
		return masa;
	}

	public void setMasa(int masa) {
		this.masa = masa;
	}


	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public Vector2D getVectorMovimiento() {
		return vectorMovimiento;
	}

	public void setVectorMovimiento(Vector2D vectorMovimiento) {
		this.vectorMovimiento = vectorMovimiento;
	}

	public Transform getTransform() {
		return transform;
	}

	public void setTransform(Transform transform) {
		this.transform = transform;
	}

	public ArrayVector2D getFuerzasAplicadas() {
		return fuerzasAplicadas;
	}

	public void setFuerzasAplicadas(ArrayVector2D fuerzasAplicadas) {
		this.fuerzasAplicadas = fuerzasAplicadas;
	}

	@Override
	public String toString() {
		return "Fisica [gravity=" + gravity + ", vectorMovimiento=" + vectorMovimiento + ", ultimaDireccion="
				+ ultimaDireccion + ", transform=" + transform + ", masa=" + masa + ", fuerzasAplicadas="
				+ fuerzasAplicadas + "]";
	}

	
}
