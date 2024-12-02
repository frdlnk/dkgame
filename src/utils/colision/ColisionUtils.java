package utils.colision;

import motor_v1.motor.Entidad;
import motor_v1.motor.entidades.ListaEntidades;

/**
 * Funciones utilitarias para la resolucion de colisiones
 */
public class ColisionUtils {
	/**
	 * Verifica la colision entre 2 objetos y decide cuales deben desencadenar onColision
	 * @param col1 objeto 1
	 * @param col2 objeto 2
	 */
	public static void colisionResolve(Colisionable col1, Colisionable col2) {
		ColisionInfo col1Colisiona = col1.hayColision(col2);
		ColisionInfo col2Colisiona = col2.hayColision(col1);
		//si col1 es triger y 2 no es trigerr, desencadena el evento de col1
		if (col1Colisiona != null && col1Colisiona.isTriger() && col2Colisiona!=null && !col2Colisiona.isTriger())
			col1.onColision(col2Colisiona);
		//si col2 es triger y col1 no es triger desencadena onColision de col2
		else if(col2Colisiona != null && col2Colisiona.isTriger() && col1Colisiona!=null && !col1Colisiona.isTriger())
			col2.onColision(col1Colisiona);
		//si hay colision y ninguno es trigger desencadena colision a los 2
		else if(col1Colisiona!=null && col2Colisiona!=null) {
			col1.onColision(col2Colisiona);
			col2.onColision(col1Colisiona);
		}
		
	}
	
	/**
	 * verifica la colision de un objeto con una lista de entidades
	 * @param lista
	 * @param colisionable
	 */
	public static void entityColisionVerifier(
			ListaEntidades lista, Colisionable colisionable)
	{
		for (int i = 0; i < lista.getSize(); i++) {
			Entidad listEntry = lista.get(i);
			if (listEntry instanceof Colisionable && colisionable != listEntry) {
				colisionResolve(colisionable, (Colisionable) listEntry);
			}
		}
	}

	@Override
	public String toString() {
		return "ColisionUtils []";
	}
	
	
}
