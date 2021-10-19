import java.util.Arrays;

public class Vecteur implements ObjEmp {

    public Double[] numbers;

    public Vecteur (Double[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public ObjEmp add(ObjEmp oe) {
        try {
            if (oe instanceof Vecteur) {
                for (int i = 0; i < this.numbers.length; i++) {
                    this.numbers[i] += ((Vecteur) oe).numbers[i];
                }
                return this;
            }
            throw new Exception();
        } catch (Exception e) {return null;}
    }

    @Override
    public ObjEmp sub(ObjEmp oe) {
        return null;
    }

    @Override
    public ObjEmp mul(ObjEmp oe) {
        return null;
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
        return Arrays.toString(numbers);
    }
}
