import java.util.LinkedList;
import java.util.List;

public class PileRPL {

    List<ObjEmp> pile;

    public PileRPL(long size) {
        this.pile = new LinkedList<ObjEmp>();
    }

    public void stack(ObjEmp oe) {
        this.pile.add(oe);
    }

    public void add() {
        ObjEmp oeA = this.pile.remove(this.pile.size()-1);
        ObjEmp oeB = this.pile.remove(this.pile.size()-1);
        this.pile.add(oeA.add(oeB));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ObjEmp oe: this.pile) {
            sb.append('[').append(oe.val).append(']');
        }
        return  "pile: " + sb;
    }
}
