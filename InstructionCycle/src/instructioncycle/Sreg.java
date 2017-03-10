package instructioncycle;

public class Sreg {

    private boolean[] sreg = new boolean[8];

    public boolean getSreg(int position) {
        return sreg[position];
    }

    public void setSreg(int position, boolean value) {
        sreg[position] = value;
    }
}
