package lab6.java;

import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception {
        long time = System.nanoTime();
        WordSolver solver = new WordSolver("words.txt");
        solver.sort();
        System.out.println((System.nanoTime() - time) * Math.pow(10, -6) + " millisec");
        getMemoryConsumption();

        getWordsInformation(solver);
    }

    private static void getWordsInformation(WordSolver solver) {
        int simpleWordsCount = solver.getSimpleWords().size();
        int partlyConcatenatedWordsCount = solver.getPartlyConcatenatedWords().size();
        int fullyConcatenatedWordsCount = solver.getFullyConcatenatedWords().size();
        System.out.println("Simple number: " + simpleWordsCount);
        System.out.println("Partly number: " + partlyConcatenatedWordsCount);
        System.out.println("Fully number: " + fullyConcatenatedWordsCount);
        if (simpleWordsCount == 0 && partlyConcatenatedWordsCount == 0 && fullyConcatenatedWordsCount == 0)
            System.out.println("ERROR");

        List<String> longestWords = solver.getConcatenatedWordByLengthAt(1);
        System.out.println("The longest of concatenated: " + longestWords +
                " with length " + longestWords.get(0).length());
        System.out.println("The 2nd longest of concatenated: " + solver.getConcatenatedWordByLengthAt(2));
    }

    private static void getMemoryConsumption() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.printf("Memory used: %d B \n", memory);
    }
}
