package instructioncycle;

public class RegisterBank {
    private int size = 5;
    private int[] registerBank = new int[size];

    public int getRegisterBank(int position) {
        return registerBank[position];
    }

    public void setRegisterBank(int position, int value) {
        registerBank[position] = value;
    }

    public int getSize(){
        return size;
    }

}
