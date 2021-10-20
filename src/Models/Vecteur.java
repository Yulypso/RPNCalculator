package Models;

import java.util.Arrays;

public class Vecteur implements ObjEmp {

    public Double[] values;

    public Vecteur (Double[] values) {
        this.values = values;
    }

    @Override
    public ObjEmp add(ObjEmp oe) {
        try {
            switch (oe.getClass().getName()) {
                case "Models.Vecteur" -> {
                    for (int i = 0; i < this.values.length; i++) {
                        this.values[i] += ((Vecteur) oe).values[i];
                    }
                    return this;
                }
                case "Models.Nombre" -> {
                    for (int i = 0; i < this.values.length; i++) {
                        this.values[i] += ((Nombre) oe).value;
                    }
                    return this;
                }
                default -> throw new Exception();
            }
        } catch (Exception e) {return null;}
    }

    @Override
    public ObjEmp sub(ObjEmp oe) {
        try {
            switch (oe.getClass().getName()) {
                case "Models.Vecteur" -> {
                    for (int i = 0; i < this.values.length; i++) {
                        this.values[i] -= ((Vecteur) oe).values[i];
                    }
                    return this;
                }
                case "Models.Nombre" -> {
                    for (int i = 0; i < this.values.length; i++) {
                        this.values[i] -= ((Nombre) oe).value;
                    }
                    return this;
                }
                default -> throw new Exception();
            }
        } catch (Exception e) {return null;}
    }

    @Override
    public ObjEmp mul(ObjEmp oe) { // produit scalaire
        try {
            switch (oe.getClass().getName()) {
                case "Models.Vecteur" -> {
                    for (int i = 0; i < this.values.length; i++) {
                        this.values[i] *= ((Vecteur) oe).values[i];
                    }
                    Nombre nombre = new Nombre(0.0);
                    for (Double value : this.values) {
                        nombre.value += value;
                    }
                    return nombre;
                }
                case "Models.Nombre" -> {
                    for (int i = 0; i < this.values.length; i++) {
                        this.values[i] *= ((Nombre) oe).value;
                    }
                    return this;
                }
                default -> throw new Exception();
            }
        } catch (Exception e) {return null;}
    }


    @Override
    public ObjEmp div(ObjEmp oe) {
        return null;
    }

    @Override
    public ObjEmp mod(ObjEmp oe) {
        return null;
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }
}
