package utils;

import motor_v1.motor.Entidad;
import motor_v1.motor.entidades.ListaEntidades;

public class ColisionUtils {
	public static void colisionResolve(Colisionable col1, Colisionable col2) {
		ColisionInfo col1Colisiona = col1.hayColision(col2);
		ColisionInfo col2Colisiona = col2.hayColision(col1);
		if (col1.isTrigger() && col1Colisiona!=null && !col2.isTrigger()) {
			col1.onColision(col2Colisiona);
		}else if(col2.isTrigger() && col2Colisiona!=null && !col1.isTrigger()) {
			col2.onColision(col1Colisiona);
		}else if(col1Colisiona!=null && col2Colisiona!=null) {
			col1.onColision(col2Colisiona);
			col2.onColision(col1Colisiona);
		}
		
	}
	
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
}
