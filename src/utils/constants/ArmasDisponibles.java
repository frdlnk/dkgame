package utils.constants;

import modelo.armamento.armas.Arma;
import modelo.armamento.armas.HeavyMachineGun;
import modelo.armamento.armas.LanzaCohetes;
import modelo.armamento.armas.Pistola;

public class ArmasDisponibles {
	public final static String PISTOLA = "Pistola";
	public final static String HEAVY_MACHINEGUN = "Heavy Machinegun";
	public final static String lANZA_COHETES = "Lanza Cohetes";

	public static String[] values() {
		return new String[] { PISTOLA, HEAVY_MACHINEGUN, lANZA_COHETES };
	}

	public static Arma generarArmaGenerica(String tipoArma) {
		return switch (tipoArma) {
		case PISTOLA -> new Pistola();
		case HEAVY_MACHINEGUN -> new HeavyMachineGun();
		case lANZA_COHETES -> new LanzaCohetes();
		default -> null;
		};
	}
}
