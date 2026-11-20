import itsc2214.*;

public class Main {

    /**
     * Create a directed graph.
     */
    public static GraphMatrix makeDirected() {
        // Graph with 6 nodes, directed
        GraphMatrix g = new GraphMatrix(6, true);

        // Set a name for each node, going from 0-5
        g.setNode(0, "Woodward");
        // TODO add more node names here
        g.setNode(1, "Fretwell");
        g.setNode(2, "Union");
        g.setNode(3, "Atkins");
        g.setNode(4, "Cone");
        g.setNode(5, "UNCC");

        // Make an edge from 0 to 2 with weight of 5
        g.addEdge(0, 2, 5);
        // TODO add other edges here
        g.addEdge(0, 2, 5);
        g.addEdge(0, 1, 3);
        g.addEdge(1, 3, 2);
        g.addEdge(2, 4, 4);
        g.addEdge(3, 5, 6);
        g.addEdge(4, 5, 1);
        g.addEdge(5, 5, 7);

        return g;
    }

    /**
     * Create an undirected graph.
     */
    public static GraphMatrix makeUndirected() {
        // Graph with 6 nodes, UNdirected
        GraphMatrix g = new GraphMatrix(6);
        // Set a name for each node, going from 0-5
        g.setNode(0, "Woodward");
        // TODO add more node names here
        g.setNode(1, "Fretwell");
        g.setNode(2, "Union");
        g.setNode(3, "Atkins");
        g.setNode(4, "Cone");
        g.setNode(5, "UNCC");

        // Make an edge from 0 to 2 with weight of 5
        g.addEdge(0, 2, 5);
        // TODO add other edges here
        g.addEdge(0, 2, 5);
        g.addEdge(0, 1, 3);
        g.addEdge(1, 3, 2);
        g.addEdge(2, 4, 4);
        g.addEdge(3, 5, 6);
        g.addEdge(4, 5, 1);
        g.addEdge(5, 5, 7);

        return g;
    }

    /**
     * This routine outputs a Dot representation for the
     * graph. We saw the dot representation in a previous
     * lab. A representation starts with "graph {" if the
     * graph is undirected or "digraph {" if the graph is
     * directed. Then it is following by a number of lines
     * designating the nodes and edges. For example, if
     * there is a path from node Fretwell to Union, then
     * it should print "Fretwell -- Union" for an undirected
     * graph and "Fretwell -> Union" for a directed graph.
     * If the edge has a label (i.e., a cost), then append
     * at the end of the line "[label = "3"]" for a label
     * with an edge of cost 3. The output should end with
     * the closing "}". All lines designating a node and
     * an edge should end with a ";".
     * 
     * There is one caveat: if the graph is undirected,
     * the itsc2214 package copies the x->y edges to the
     * mirror side of the array, that is it will also
     * store y->x. So, to print an undirected graph, you
     * only want to print the upper right triangle of the
     * matrix. That is, if you use r to designate the row
     * and c to designate the column, you only want to
     * print values where c >= r.
     * You should be able to copy the text output of
     * printDot() from the Terminal in VS Code and paste
     * it into the online GraphViz tool
     * https://dreampuf.github.io/GraphvizOnline
     * to visualize your output.
     */

    public static void printDot(GraphMatrix g) {
        String arrow = null;
        // Print a graph for a directed graph
        /*
         * digraph {
         * node -> node [label="w"];
         * }
         */
        if (g.isDirected()) {
            System.out.println("digraph {");
            arrow = " -> ";
        }

        // OR for an undirected graph
        /*
         * graph {
         * node -- node [label="w"];
         * }
         */
        if (!g.isDirected()) {
            System.out.println("graph {");
            arrow = " -- ";
        }

        int n = g.getNodeCount();

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (!g.isDirected() && col < row) {
                    continue;
                }
                if (g.hasEdge(row, col)) {
                    System.out.println(g.getNode(row) + arrow + g.getNode(col)
                            + " [label=\"" + g.getWeight(row, col) + "\"];");
                }
            }
        }
        System.out.println("}");
    }

    public static void main(String[] args) {
        // change this to makeUndirected() to see
        // the undirected graph from above.
        GraphMatrix g = makeDirected();

        printDot(g);
    }

    /**
     * If the graph isDirected(), then in-degree is = count of all entries
     * in the column.If the graph is not directed, then return whatever
     * g.getNodeDegree() returns.
     * 
     * @param g    matrix
     * @param node of interest
     * @return in degree
     */
    public static int inDegree(GraphMatrix g, int node) {
        // TODO return something other than -1
        if (!g.isDirected()) {
            return g.getNodeDegree(node);
        }

        int count = 0;
        for (int col = 0; col < g.getNodeCount(); col++) {
            if (g.hasEdge(col, node)) {
                count++;
            }
        }
        return count;
    }

    /**
     * If the graph isDirected(), then out-degree is = count of all entries
     * in the row. If the graph is not directed, then return whatever
     * g.getNodeDegree() returns.
     * 
     * @param g    matrix
     * @param node of interest
     * @return out degree for the node
     */
    public static int outDegree(GraphMatrix g, int node) {
        // TODO return something other than -1
        if (!g.isDirected()) {
            return g.getNodeDegree(node);
        }

        int count = 0;
        for (int row = 0; row < g.getNodeCount(); row++) {
            if (g.hasEdge(row, node)) {
                count++;
            }
        }
        return count;
    }

    /**
     * A self loop is an edge from a node to itself. In a matrix representation
     * that means there is an entry at the diagonal of the matrix.
     * 
     * @param g    matrix
     * @param node of interest
     * @return true if there is a self loop, false otherwise
     */
    public static boolean selfLoop(GraphMatrix g, int node) {
        // TODO return something other than false
        if (g.hasEdge(node, node)) {
            return true;
        }
        return false;
    }
}