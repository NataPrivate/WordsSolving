package lab6.java;

public class Main {
    public static void main(String[] args) throws Exception {
        long time = System.nanoTime();;
        WordSolver solver = new WordSolver("words0.txt");
        solver.sort();
        if (solver.getConcatenatedWords().size() == 0 || solver.getSimpleWords().size() == 0)
            System.out.println("ERROR");
        System.out.println("Simple:");
        for (String word : solver.getSimpleWords())
            System.out.println(word);
        System.out.println("Concatenated:");
        for (String word : solver.getConcatenatedWords())
            System.out.println(word);
        System.out.println((System.nanoTime() - time) + " nanosec");
        getMemoryConsumption();
        System.out.println("Count of concatenated: " + solver.getConcatenatedWords().size());
        System.out.println("The longest of concatenated: " + solver.getConcatenatedWordByLengthAt(1));
    }
    private static void getMemoryConsumption() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.printf("Memory used: %d B \n", memory);
    }
}
