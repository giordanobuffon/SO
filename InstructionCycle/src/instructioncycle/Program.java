package instructioncycle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Program {

    public static void main(String[] args) {

        MainMemory mainMemory = new MainMemory();
        RegisterBank initRegisterBank = new RegisterBank();
        RegisterBank finalRegisterBank = new RegisterBank();
        Sreg sreg = new Sreg();
        String path = "src/instructioncycle/InstructionsAssembler.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while (br.ready()) {
                String line = br.readLine();
                mainMemory.add(line.toLowerCase());
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        InstructionCycle instructionCycle = new InstructionCycle();
        finalRegisterBank = instructionCycle.startCycle(mainMemory, initRegisterBank, sreg);

        System.out.println("Valor atual contido nos registradores:");
        for (int i = 0; i < finalRegisterBank.getSize(); i++) {
            System.out.printf("R%d -> %d\n", i, finalRegisterBank.getRegisterBank(i));
        }

    }

}
