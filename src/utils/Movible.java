package utils;

import modelo.componentes.Fisica;
import motor_v1.motor.component.Collider;

public interface Movible {

   Collider getColisiona();

   Fisica getFisica();

   void setColisiona(Collider var1);

   void setFisica(Fisica var1);
}
