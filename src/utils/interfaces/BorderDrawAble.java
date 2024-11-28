package utils.interfaces;

import java.awt.Graphics;

/**
 * Interfaz utilitaria define objetos que puedan dibujar su borde
 */
@FunctionalInterface
public interface BorderDrawAble {
	/**
	 * Dibuja sus bordes
	 * @param g graficos para dibujar
	 */
	void drawBorders(Graphics g);
}
