package Models;

public class Nombre implements ObjEmp {

    public Double value;

    public Nombre(Double value) {
        this.value = value;
    }

    @Override
    public ObjEmp add(ObjEmp oe) {
        try {
            switch (oe.getClass().getName()) {
                case "Models.Nombre" -> {
                    this.value += ((Nombre) oe).value;
                    return this;
                }
                case "Models.Vecteur" -> {
                    for (int i = 0; i < ((Vecteur) oe).values.length; i++) {
                        ((Vecteur) oe).values[i] += this.value;
                    }
                    return oe;
                }
                case "Models.Complexe" -> {
                    ((Complexe) oe).real += this.value;
                    return oe;
                }
                default -> throw new Exception();
            }
        } catch (Exception e) {return null;}
    }

    @Override
    public ObjEmp sub(ObjEmp oe) {
        try {
            switch (oe.getClass().getName()) {
                case "Models.Nombre" -> {
                    this.value -= ((Nombre) oe).value;
                    return this;
                }
                case "Models.Vecteur" -> {
                    for (int i = 0; i < ((Vecteur) oe).values.length; i++) {
                        ((Vecteur) oe).values[i] -= this.value;
                    }
                    return oe;
                }
                case "Models.Complexe" -> {
                    ((Complexe) oe).real -= this.value;
                    return oe;
                }
                default -> throw new Exception();
            }
        } catch (Exception e) {return null;}
    }

    @Override
    public ObjEmp mul(ObjEmp oe) {
        try {
            switch (oe.getClass().getName()) {
                case "Models.Nombre" -> {
                    this.value *= ((Nombre) oe).value;
                    return this;
                }
                case "Models.Vecteur" -> {
                    for (int i = 0; i < ((Vecteur) oe).values.length; i++) {
                        ((Vecteur) oe).values[i] *= this.value;
                    }
                    return oe;
                }
                case "Models.Complexe" -> {
                    ((Complexe) oe).real *= this.value;
                    return oe;
                }
                default -> throw new Exception();
            }
        } catch (Exception e) {return null;}
    }

    @Override
    public ObjEmp div(ObjEmp oe) {
        try {
            switch (oe.getClass().getName()) {
                case "Models.Nombre" -> {
                    if (((Nombre) oe).value != 0) {
                        this.value /= ((Nombre) oe).value;
                        return this;
                    }
                }
                case "Models.Complexe" -> {
                    if (!(((Complexe) oe).real == 0 && ((Complexe) oe).imag == 0))
                        return new Complexe(this.value, 0.0).div(oe);
                }
                default -> throw new Exception();
            }
            throw new Exception();
        } catch (Exception e) {return null;}
    }

    @Override
    public ObjEmp mod(ObjEmp oe) {
        try {
            if ("Models.Nombre".equals(oe.getClass().getName())) {
                if (((Nombre) oe).value != 0) {
                    this.value %= ((Nombre) oe).value;
                    return this;
                }
            } else {
                throw new Exception();
            }
            throw new Exception();
        } catch (Exception e) {return null;}
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
