public class ObjEmp { // extends Number

    //public Number val;
    public long val;

    ObjEmp(long val) {
        this.val = val;
    }

    public ObjEmp add(ObjEmp oe) {
        oe.val = this.val + oe.val;
        return oe;
    }
}
