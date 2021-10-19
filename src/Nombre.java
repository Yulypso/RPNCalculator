public class Nombre implements ObjEmp {

    public Double value;

    Nombre(Double value) {
        this.value = value;
    }

    @Override
    public ObjEmp add(ObjEmp oe) {
        try {
            switch (oe.getClass().getName()) {
                case "Nombre" -> {
                    this.value += ((Nombre) oe).value;
                    return this;
                }
                case "Vecteur" -> {
                    for (int i = 0; i < ((Vecteur) oe).values.length; i++) {
                        ((Vecteur) oe).values[i] += this.value;
                    }
                    return oe;
                }
                case "Complexe" -> {
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
                case "Nombre" -> {
                    this.value -= ((Nombre) oe).value;
                    return this;
                }
                case "Vecteur" -> {
                    for (int i = 0; i < ((Vecteur) oe).values.length; i++) {
                        ((Vecteur) oe).values[i] -= this.value;
                    }
                    return oe;
                }
                case "Complexe" -> {
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
                case "Nombre" -> {
                    this.value *= ((Nombre) oe).value;
                    return this;
                }
                case "Vecteur" -> {
                    for (int i = 0; i < ((Vecteur) oe).values.length; i++) {
                        ((Vecteur) oe).values[i] *= this.value;
                    }
                    return oe;
                }
                case "Complexe" -> {
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
                case "Nombre" -> {
                    if (((Nombre) oe).value != 0) {
                        this.value /= ((Nombre) oe).value;
                        return this;
                    }
                }
                case "Complexe" -> {
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
            if ("Nombre".equals(oe.getClass().getName())) {
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
