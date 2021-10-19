public class Richalculator {

    public static void main(String[] args) {
        PileRPL pile = new PileRPL(4);

        ObjEmp oe1 = new Vecteur(new Double[]{1.0, 2.0, 3.0});
        ObjEmp oe2 = new Vecteur(new Double[]{4.0, 5.0, 6.0});
        System.out.println(pile);
        pile.stack(oe1);
        System.out.println(pile);
        pile.stack(oe2);
        System.out.println(pile);
        pile.add();
        System.out.println(pile);
    }
}
