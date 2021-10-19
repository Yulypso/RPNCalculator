public class Richalculator {

    public static void main(String[] args) {
        PileRPL pile = new PileRPL(4);

        ObjEmp oe1 = new Nombre(42.0);
        ObjEmp oe2 = new Nombre(18.5);
        System.out.println(pile);
        pile.stack(oe1);
        System.out.println(pile);
        pile.stack(oe2);
        System.out.println(pile);
        pile.add();
        System.out.println(pile);
    }
}
