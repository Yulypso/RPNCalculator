public class Richalculator {

    public static void main(String[] args) {
        PileRPL pile = new PileRPL(4);
        ObjEmp oe1 = new ObjEmp(42);
        ObjEmp oe2 = new ObjEmp(22);
        System.out.println(pile);
        pile.stack(oe1);
        System.out.println(pile);
        pile.stack(oe2);
        System.out.println(pile);
        pile.add();
        System.out.println(pile);
    }
}
