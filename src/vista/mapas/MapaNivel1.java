package vista.mapas;

import vista.mapas.zonas.Zona1N1;
import vista.mapas.zonas.Zona2N1;

public class MapaNivel1 extends Map {

	public MapaNivel1() {
		super();
	}

	@Override
	protected void crearZonas() {
		Zona zona1 = new Zona1N1();
		zonas.add(zona1);
		Zona zona2 = new Zona2N1();
		zonas.add(zona2);
	}

}
