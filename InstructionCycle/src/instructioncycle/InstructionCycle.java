package instructioncycle;

public class InstructionCycle {

    private int pc; //Program Counter - CI
    private String ir; //Instruction Registrer - RI
    private RegisterBank rb;
    private Sreg sreg;

    public RegisterBank startCycle(MainMemory mainMemory, RegisterBank registerBank, Sreg sreg) throws IndexOutOfBoundsException {
        this.rb = registerBank;
        this.sreg = sreg;

        boolean testHalt = false;
        pc = 0;
        try {
            while (!testHalt) {
                ir = mainMemory.get(pc);
                pc++;
                testHalt = decodeAndExecute();

            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return registerBank;
    }

    // decoding
    private boolean decodeAndExecute() {
        boolean testHalt = false;
        String[] decodInstructions = ir.split(", | ");
        switch (decodInstructions[0]) {
            case "add":
                add(decodInstructions);
                break;
            case "bclr":
                bclr(decodInstructions);
                break;
            case "dec":
                dec(decodInstructions);
                break;
            case "goto":
                goTo(decodInstructions);
                break;
            case "halt":
                testHalt = true;
                break;
            case "null":
                break;
            case "mov":
                mov(decodInstructions);
                break;
            case "sbrs":
                sbrs(decodInstructions);
                break;
            default:
                break;
        }
        return testHalt;
    }

    // execution
    private int parseIntFormat(String s) {
        s = s.replace("r", "");
        return Integer.parseInt(s);
    }

    private void add(String[] operands) {
        int value;
        int rd = parseIntFormat(operands[1]);
        if (operands[2].charAt(0) == 'r') {
            int ro = parseIntFormat(operands[2]);
            value = rb.getRegisterBank(rd) + rb.getRegisterBank(ro);
        } else {
            int var = parseIntFormat(operands[2]);
            value = rb.getRegisterBank(rd) + var;
        }
        rb.setRegisterBank(rd, value);
    }

    private void bclr(String[] operands) {
        int position = Integer.parseInt(operands[1]);
        sreg.setSreg(position, false);
    }

    private void dec(String[] operands) {
        int rd = parseIntFormat(operands[1]);
        int value = rb.getRegisterBank(rd) - 1;
        rb.setRegisterBank(rd, value);
        if (value == 0) {
            sreg.setSreg(1, true);
        }
    }

    private void goTo(String[] operands) {
        pc = parseIntFormat(operands[1]);
    }

    private void mov(String[] operands) {
        int value;
        int rd = parseIntFormat(operands[1]);
        if (operands[2].charAt(0) == 'r') {
            int ro = parseIntFormat(operands[2]);
            value = rb.getRegisterBank(ro);
        } else {
            value = parseIntFormat(operands[2]);
        }
        rb.setRegisterBank(rd, value);
    }

    private void sbrs(String[] operands) {
        int position = Integer.parseInt(operands[2]);
        if (sreg.getSreg(position))
            pc++;
    }

}