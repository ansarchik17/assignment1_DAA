package assignment1.metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVWriter {
    private final PrintWriter writer;

    public CSVWriter(String filename) throws IOException {
        this.writer = new PrintWriter(new FileWriter(filename));
    }

    public void writeHeader() {
        writer.println("algorithm,n,comparisons,allocations,depth,time_ms");
    }

    public void writeRow(String algorithm, int n, long comparisons, long allocations, int depth, long timeMs) {
        writer.printf("%s,%d,%d,%d,%d,%d%n", algorithm, n, comparisons, allocations, depth, timeMs);
    }

    public void close() {
        writer.close();
    }
}
