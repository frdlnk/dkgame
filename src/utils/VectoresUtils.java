package utils;

import motor_v1.motor.util.Vector2D;

public class VectoresUtils {
	public static double productoPunto(Vector2D vector1, Vector2D vector2) {
		vector1 = vector1.normalize();
		vector2 = vector2.normalize();
		return vector1.getX()*vector2.getX() + vector1.getY()*vector2.getY();
	}
}
