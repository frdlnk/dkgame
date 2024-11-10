package modelo.componentes;

import motor_v1.motor.GameLoop;
import motor_v1.motor.component.Physics;
import motor_v1.motor.component.Transform;
import motor_v1.motor.util.Vector2D;
import utils.Conf;
import utils.arrays.ArrayVector2D;

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
	 * Crea una nueva fisica aplicada a un trasnform, con masa =1 y gravedad = 1
	 * @param transform
	 */
	public Fisica(Transform transform) {
		this(1,1,transform);
	}

	public Fisica(int masa, double gravity, Transform transform) {
		this.gravity = gravity;
		this.transform = transform;
		this.masa = masa;
		setFriccion(0);
		vectorMovimiento = Vector2D.ZERO;
		ultimaDireccion = Vector2D.ZERO;
		fuerzasAplicadas = new ArrayVector2D();
	}
    
	public void impulsar(Vector2D vector) {
		Vector2D newVector = new Vector2D(vector.getX()/masa, vector.getY()/masa);
		vectorMovimiento = vectorMovimiento.add(newVector);
	}
	
	public void impulsar(double force) {
		vectorMovimiento = vectorMovimiento.add(force/masa);
	}
	public void addForce(Vector2D vector) {
		Vector2D newVector = new Vector2D(vector.getX()/masa, vector.getY()/masa);
		fuerzasAplicadas.add(newVector);
		vectorMovimiento = vectorMovimiento.add(newVector);
	}
	
	public void addForce(double force) {
		fuerzasAplicadas.add(Vector2D.ONE.scale(force/masa));
		vectorMovimiento = vectorMovimiento.add(force/masa);
	}
	
	public void actualizar() {
		vectorMovimiento = vectorMovimiento.scale(1-getFriccion());
		Vector2D nuevaPosicion = transform.getPosicion().add(vectorMovimiento);
		ultimaDireccion = vectorMovimiento;
		for (int i = 0; i < fuerzasAplicadas.getSize(); i++) {
			Vector2D fuerzaAplicada = fuerzasAplicadas.get(i);
			vectorMovimiento = vectorMovimiento.subtract(fuerzaAplicada);
		}
		fuerzasAplicadas.clear();
		transform.trasladarloA(nuevaPosicion);
		vectorMovimiento.setY(vectorMovimiento.getY() + (gravity*Conf.GRAVITY*GameLoop.dt/1000));
	}

	public void acelerar() {
		vectorMovimiento = vectorMovimiento.scale(getAceleracion());
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


}
