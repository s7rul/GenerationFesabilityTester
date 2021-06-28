package testUser;

import lookupTable.GenerationFeasibilityTester;

public class Main {
    static public void main(String[] args) {
        GenerationFeasibilityTester t = new GenerationFeasibilityTester();
        Boolean t1 = t.generationFeasibilityByDeviceID(1);
        System.out.println("ID: 1 result: " + t1);

        Boolean t2 = t.generationFeasibilityByDeviceID(2);
        System.out.println("ID: 2 result: " + t2);

        Boolean t3 = t.generationFeasibilityByDeviceID(3);
        System.out.println("ID: 3 result: " + t3);

        Boolean t4 = t.generationFeasibilityByDeviceID(4);
        System.out.println("ID: 4 result: " + t4);

        Boolean t5 = t.generationFeasibilityByDeviceID(5);
        System.out.println("ID: 5 result: " + t5);
    }
}
