import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UndirectedGraph graph = UndirectedGraph.fromScannerEdgeLists(scanner);
        graph.outputMatrix();
    }
}
