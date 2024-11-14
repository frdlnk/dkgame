package utils;

import motor_v1.motor.Entidad;
import motor_v1.motor.entidades.ListaEntidades;

public class ColisionUtils {
	public static void colisionResolve(Colisionable col1, Colisionable col2, Entidad ent1, Entidad ent2) {
		boolean col1Colisiona = col1.hayColision(col2);
		boolean col2Colisiona = col2.hayColision(col1);
		if (col1.isTrigger() && col1Colisiona && !col2.isTrigger()) {
			col1.onColision(ent2);
		}else if(col2.isTrigger() && col2Colisiona && !col1.isTrigger()) {
			col2.onColision(ent1);
		}else if(col1Colisiona && col2Colisiona) {
			col1.onColision(ent2);
			col2.onColision(ent1);
		}
		
	}
	
	public static void entityColisionVerifier(
			ListaEntidades lista, Colisionable colisionable, Entidad entidad)
	{
		for (int i = 0; i < lista.getSize(); i++) {
			Entidad listEntry = lista.get(i);
			if (listEntry instanceof Colisionable && entidad != listEntry) {
				colisionResolve(colisionable, (Colisionable) listEntry, entidad, listEntry);
			}
		}
	}
}
