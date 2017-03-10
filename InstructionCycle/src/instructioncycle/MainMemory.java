package instructioncycle;

import java.util.ArrayList;

public class MainMemory {

    private ArrayList<String> mainMemory = new ArrayList<String>();

    public void add(String line) {
        mainMemory.add(line);
    }

    public int size() {
        return mainMemory.size();
    }

    public String get(int i) {
        return mainMemory.get(i);
    }
}
