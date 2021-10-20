package Models;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PileRPL {

    public List<ObjEmp> pile;

    public PileRPL() {
        this.pile = new LinkedList<>();
    }

    public void stack(ObjEmp oe) {
        this.pile.add(oe);
    }

    public void add() {
        ObjEmp oeA = this.pile.remove(this.pile.size()-1);
        ObjEmp oeB = this.pile.remove(this.pile.size()-1);
        ObjEmp res = oeB.add(oeA);
        if (res != null)
            this.pile.add(res);
        else
            System.err.println("[Error]: Forbidden operation");
    }

    public void sub() {
        ObjEmp oeA = this.pile.remove(this.pile.size()-1);
        ObjEmp oeB = this.pile.remove(this.pile.size()-1);
        ObjEmp res = oeB.sub(oeA);
        if (res != null)
            this.pile.add(res);
        else
            System.err.println("[Error]: Forbidden operation");
    }

    public void mul() {
        ObjEmp oeA = this.pile.remove(this.pile.size()-1);
        ObjEmp oeB = this.pile.remove(this.pile.size()-1);
        ObjEmp res = oeB.mul(oeA);
        if (res != null)
            this.pile.add(res);
        else
            System.err.println("[Error]: Forbidden operation");
    }

    public void div() {
        ObjEmp oeA = this.pile.remove(this.pile.size()-1);
        ObjEmp oeB = this.pile.remove(this.pile.size()-1);
        ObjEmp res = oeB.div(oeA);
        if (res != null)
            this.pile.add(res);
        else
            System.err.println("[Error]: Forbidden operation");
    }

    public void mod() {
        ObjEmp oeA = this.pile.remove(this.pile.size()-1);
        ObjEmp oeB = this.pile.remove(this.pile.size()-1);
        ObjEmp res = oeB.mod(oeA);
        if (res != null)
            this.pile.add(res);
        else
            System.err.println("[Error]: Forbidden operation");
    }

    public void clear() {
        this.pile.clear();
    }

    public void delete() {
        this.pile.remove(this.pile.size()-1);
    }

    @Override
    public String toString() {
        return Arrays.toString(pile.toArray());
    }
}
