import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PileRPL {

    List<ObjEmp> pile;

    public PileRPL() {
        this.pile = new LinkedList<>();
    }

    public void stack(ObjEmp oe) {
        this.pile.add(oe);
    }

    public void add() {
        ObjEmp oeA = this.pile.remove(this.pile.size()-1);
        ObjEmp oeB = this.pile.remove(this.pile.size()-1);
        this.pile.add(oeB.add(oeA));
    }

    public void sub() {
        ObjEmp oeA = this.pile.remove(this.pile.size()-1);
        ObjEmp oeB = this.pile.remove(this.pile.size()-1);
        this.pile.add(oeB.sub(oeA));
    }

    public void mul() {
        ObjEmp oeA = this.pile.remove(this.pile.size()-1);
        ObjEmp oeB = this.pile.remove(this.pile.size()-1);
        this.pile.add(oeB.mul(oeA));
    }

    public void div() {
        ObjEmp oeA = this.pile.remove(this.pile.size()-1);
        ObjEmp oeB = this.pile.remove(this.pile.size()-1);
        this.pile.add(oeB.div(oeA));
    }

    public void mod() {
        ObjEmp oeA = this.pile.remove(this.pile.size()-1);
        ObjEmp oeB = this.pile.remove(this.pile.size()-1);
        this.pile.add(oeB.mod(oeA));
    }

    public void clear() {
        this.pile.clear();
    }

    @Override
    public String toString() {
        return Arrays.toString(pile.toArray());
    }
}
