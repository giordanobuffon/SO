package instructioncycle;

import java.io.*;
import java.util.ArrayList;

public class InstructionCycle {

    private ArrayList<String> mainMemory = new ArrayList<String>();
    private int pc; //Program Counter - CI
    private String ir; //Instruction Registrer - RI
    private int[] registerBank = new int[5];
    private boolean[] sreg = new boolean[8];

    public int[] startCycle(String path) throws IOException {
        readFile(path);
        pc = 0;
        while (pc < mainMemory.size()) {
            ir = mainMemory.get(pc);
            pc++;
            decodeAndExecute();
        }
        return registerBank;
    }

    private void readFile(String path) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));//"src/instructioncycle/InstructionsAssembler.txt"
            while (br.ready()) {
                String linha = br.readLine();
                mainMemory.add(linha.toLowerCase());
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    // decoding
    private void decodeAndExecute() {
        String[] decodedInstructions = ir.split(", | ");
        switch (decodedInstructions[0]) {
            case "add":
                add(parseInt(decodedInstructions[1]), parseInt(decodedInstructions[2]));
                break;
            case "bclr":
                bclr(parseInt(decodedInstructions[1]));
                break;
            case "dec":
                dec(parseInt(decodedInstructions[1]));
                break;
            case "goto":
                goTo(parseInt(decodedInstructions[1]));
                break;
            case "halt":
                return;
            case "null":
                break;
            case "mov":
                mov(parseInt(decodedInstructions[1]), parseInt(decodedInstructions[2]));
                break;
            case "sbrs":
                sbrs(parseInt(decodedInstructions[1]));
                break;
        }

    }

    // execution
    private int parseInt(String s) {
        return Integer.parseInt(s);
    }

    private void add(int rd, int ro) {
        registerBank[rd] += ro;
    }

    private void bclr(int i) {
        sreg[i] = false;
    }

    private void dec(int rd) {
        registerBank[rd] -= 1;
        if (registerBank[rd] == 0)
            sreg[1] = true;
    }

    private void goTo(int i) {
        pc = i;
    }

    private void mov(int rd, int ro) {
        registerBank[rd] = ro;
    }

    private void sbrs(int i) {
        if (sreg[i])
            pc++;
    }


}

