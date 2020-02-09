import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntArrayList;

public class UndirectedGraph {
    private List<IntList> adjacencies;

    /**
     * Creates a graph from a list of adjacency lists.
     * Nodes with no neighbors are specified with an empty
     * adjacency list.
     *
     * @param adjacencies a list of adjacency lists (integer lists, specifying the labels of adjacent nodes)
     */
    public UndirectedGraph(List<IntList> adjacencies) {
        this.adjacencies = new ArrayList<>(adjacencies.size());
        for (List<Integer> adjacency : adjacencies) {
            this.adjacencies.add(new IntArrayList(adjacency));
        }
    }

    /**
     * Creates a graph from an adjacency matrix.
     *
     * @param nodes the number of nodes in the graph
     * @param adjacencyMatrix the adjacency matrix, where true marks a vertex exists between the node with the line
     *                        index and the node with the column index
     */
    public UndirectedGraph(int nodes, boolean[][] adjacencyMatrix) {
        adjacencies = new ArrayList<>(nodes);
        for (int i = 0; i < nodes; i++) {
            IntList adjacency = new IntArrayList();
            for (int j = 0; j < nodes; j++) {
                if (adjacencyMatrix[i][j]) {
                    adjacency.add(j);
                }
            }
            adjacencies.add(adjacency);
        }
    }

    /**
     * Get the graph in the form of a list of adjacency lists.
     *
     * @return an immutable list of adjacency lists
     */
    public ImmutableList<IntList> getAdjacencies() {
        return (ImmutableList<IntList>) adjacencies;
    }

    /**
     * Get the graph in the form of an adjacency matrix.
     *
     * @return square boolean adjacency matrix of size n, where n is the number of nodes
     */
    public boolean[][] getAdjacencyMatrix() {
        int nodes = adjacencies.size();
        boolean[][] matrix = new boolean[nodes][nodes];
        for (int i = 0; i < nodes; i++) {
            for (int j : adjacencies.get(i)) {
                matrix[i][j] = true;
            }
        }
        return matrix;
    }

    /**
     * Creates a graph from scanner input. Input will specify "n" (the number of nodes), "m" (the number of edges), and
     * then m pairs of integers, representing the 1-indexed edges in the graph. All of the numbers are separated by
     * whitespace.
     *
     * Example - the 3-node path graph:
     * 3 2
     * 1 2
     * 1 3
     *
     * @param scanner the scanner to read input from
     * @return the resulting UndirectedGraph instance
     */
    public static UndirectedGraph fromScannerEdgeLists(Scanner scanner) {
        int nodes = scanner.nextInt();
        int edges = scanner.nextInt();

        List<IntList> adjacencies = new ArrayList<>(nodes);
        for (int node = 0; node < nodes; node++) {
            adjacencies.add(new IntArrayList());
        }

        for (int edge = 0; edge < edges; edge++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            adjacencies.get(from - 1).add(to - 1);
            adjacencies.get(to - 1).add(from - 1);
        }

        return new UndirectedGraph(adjacencies);
    }

    /**
     * Output the graph's adjacency matrix as a series of 0s and 1s.
     *
     * Example output for the complete 3-node graph:
     * 0 1 1
     * 1 0 1
     * 1 1 0
     */
    public void outputMatrix() {
        boolean[][] matrix = this.getAdjacencyMatrix();
        for (boolean[] row : matrix) {
            for (boolean val : row) {
                System.out.print((val ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }
}
