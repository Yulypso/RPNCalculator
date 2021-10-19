public class Nombre implements ObjEmp {

    public Double value;

    Nombre(Double value) {
        this.value = value;
    }

    public ObjEmp add(ObjEmp oe) {
        try {
            if (oe instanceof Nombre) {
                this.value += ((Nombre) oe).value;
                return this;
            }
            throw new Exception();
        } catch (Exception e) {return null;}
    }

    public ObjEmp sub(ObjEmp oe) {
        try {
            if (oe instanceof Nombre) {
                this.value -= ((Nombre) oe).value;
                return this;
            }
            throw new Exception();
        } catch (Exception e) {return null;}
    }

    public ObjEmp mul(ObjEmp oe) {
        try {
            if (oe instanceof Nombre) {
                this.value *= ((Nombre) oe).value;
                return this;
            }
            throw new Exception();
        } catch (Exception e) {return null;}
    }

    public ObjEmp div(ObjEmp oe) {
        try {
            if (oe instanceof Nombre) {
                this.value /= ((Nombre) oe).value;
                return this;
            }
            throw new Exception();
        } catch (Exception e) {return null;}
    }

    public ObjEmp mod(ObjEmp oe) {
        try {
            if (oe instanceof Nombre) {
                this.value %= ((Nombre) oe).value;
                return this;
            }
            throw new Exception();
        } catch (Exception e) {return null;}
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
