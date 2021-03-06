package DataStructure;

import java.util.ArrayList;

class AdjacencyListGraph<E extends Comparable<E>> { // e.g. "Graph<Data>" gr = new Graph<Data>();
    private ArrayList<Vertex> verticies;

    public AdjacencyListGraph() {
        verticies = new ArrayList<>();
    }

    private class Vertex {
        E data;
        ArrayList<Vertex> adjacentVerticies;

        public Vertex(E data) {
            this.data = data;
            adjacentVerticies = new ArrayList<>();
        }

        // foreach statement to use data.
        public boolean addAdjacentVertex(Vertex toV) {
            for(Vertex v : adjacentVerticies) {
                if(v.data.compareTo(toV.data) == 0) {
                    return false;
                }
            }
            return adjacentVerticies.add(toV);
        }

        // conventional for statement to use index. 
        public boolean removeAdjacentVertex(E to) {
            for(int i = 0; i < adjacentVerticies.size(); i++) {
                if(adjacentVerticies.get(i).data.compareTo(to) == 0) {
                    adjacentVerticies.remove(i);
                    return true;
                }
            }
            return false;
        }
    }

    // check if there are fromV and toV. 
    // Otherwise, make either of them.
    // add the edge with the direction from fromV to toV. 
    public boolean addEdge(E from, E to) {
        Vertex fromV = null, toV = null;
        for (Vertex v : verticies) {
            if (from.compareTo(v.data) == 0) {
                fromV = v;
            } else if (to.compareTo(v.data) == 0) {
                toV = v;
            }
            if (fromV != null && toV != null) break;
        }
        if (fromV == null) {
            fromV = new Vertex(from);
            verticies.add(fromV);
        }
        if (toV == null) {
            toV = new Vertex(to);
            verticies.add(toV);
        }
        return fromV.addAdjacentVertex(toV);
    }

    public boolean removeEdge(E from, E to) {
        Vertex fromV = null;
        for (Vertex v : verticies) {
            if (from.compareTo(v.data) == 0) {
                fromV = v;
                break;
            }
        }
        if (fromV == null) return false;
        return fromV.removeAdjacentVertex(to);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : verticies) {
            sb.append("Vertex: ");
            sb.append(v.data);
            sb.append("\n");
            sb.append("Adjacent verticies: ");
            for (Vertex v2 : v.adjacentVerticies) {
                sb.append(v2.data);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
// Note that ArrayList includes Direction!!
// verticies vs adjacentVerticies.
// fromV has the reference of toV

// Note that equals() are note implemented uneccessarily**
// since E extends Comparable<E> and remove(index) directly. 



public class Graph {

    public static void main(String[] args) {
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(2, 5);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        
        System.out.println(graph);


    }
}

/* How to Code Algorithms: 
 * Look at input and output from outside to inside.
 * e.g. main() -> addEdge() -> addAdjacentVertex()
 * 
 * However, when you learn an algorithm, 
 * it's good to know fields first such as "what is this for?" 
 */










