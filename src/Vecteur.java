import java.util.Arrays;

public class Vecteur implements ObjEmp {

    public Double[] values;

    public Vecteur (Double[] numbers) {
        this.values = numbers;
    }

    @Override
    public ObjEmp add(ObjEmp oe) {
        try {
            if (oe instanceof Vecteur) {
                for (int i = 0; i < this.values.length; i++) {
                    this.values[i] += ((Vecteur) oe).values[i];
                }
                return this;
            }
            throw new Exception();
        } catch (Exception e) {return null;}
    }

    @Override
    public ObjEmp sub(ObjEmp oe) {
        try {
            if (oe instanceof Vecteur) {
                for (int i = 0; i < this.values.length; i++) {
                    this.values[i] -= ((Vecteur) oe).values[i];
                }
                return this;
            }
            throw new Exception();
        } catch (Exception e) {return null;}
    }

    @Override
    public ObjEmp mul(ObjEmp oe) { // produit scalaire
        try {
            if (oe instanceof Vecteur) {
                for (int i = 0; i < this.values.length; i++) {
                    this.values[i] *= ((Vecteur) oe).values[i];
                }
                Nombre nombre = new Nombre(0.0);
                for (int i = 0; i < this.values.length; i++) {
                    nombre.value += this.values[i];
                }
                return nombre;
            }
            throw new Exception();
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
