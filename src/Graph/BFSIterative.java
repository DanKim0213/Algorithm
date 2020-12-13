package Graph;

import java.util.*;
 
// data structure to store graph edges
 
class BFSIterative
{
    private static class Edge
    {
        int source, dest;
    
        public Edge(int source, int dest) {
            this.source = source;
            this.dest = dest;
        }
        
        // public boolean filterEdge() {}

    }
    
    // class to represent a graph object
    private static class Graph
    {
        // A List of Lists to represent an adjacency list
        List<List<Integer>> adjList = null;
    
        // Constructor
        Graph(List<Edge> edges, int N)
        {
            adjList = new ArrayList<>();
    
            for (int i = 0; i < N; i++) {
                adjList.add(new ArrayList<>());
            }
    
            // add edges to the undirected graph
            for (Edge edge: edges)
            {
                int src = edge.source;
                int dest = edge.dest;
    
                adjList.get(src).add(dest);
                // adjList.get(dest).add(src);
            }
        }

        public String ansPath(int dest) {
            int target = dest;
            StringBuilder sb = new StringBuilder();
            for (int i = dest-1; i >0; i--) {
                for (int j = 0; j < adjList.get(i).size();j++) {
                    if (adjList.get(i).get(j) == target) {
                        sb.append(target + " <-");
                        target = i;
                        break;
                    }
                }
            }
            sb.append("1");
            return sb.toString();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (Iterator<List<Integer>> vi = adjList.iterator(); vi.hasNext();) {
                sb.append("Vertex : " + i++ + "\n");
                List<Integer> vertex = vi.next();
                for (int a = 0; a < vertex.size(); a++) {
                    sb.append(vertex.get(a) + " ");
                }
                sb.append("\n");
            }

            return sb.toString();
        }
    }
    // Perform BFS on graph starting from vertex v
    public static void BFS(Graph graph, int v, boolean[] discovered)
    {
        // create a queue used to do BFS
        Queue<Integer> q = new ArrayDeque<>();
 
        // mark source vertex as discovered
        discovered[v] = true;
 
        // push source vertex into the queue
        q.add(v);
 
        // loop till queue is empty
        while (!q.isEmpty())
        {
            // pop front node from queue and print it
            v = q.poll();
            System.out.print(v + " ");
 
            // do for every edge (v -> u)
            for (int u : graph.adjList.get(v)) {
                if (!discovered[u]) {
                    // mark it discovered and push it into queue
                    discovered[u] = true;
                    q.add(u);
                }
            }
        }
    }

    // Iterative Java implementation of Breadth first search
    public static void main(String[] args)
    {
        // List of graph edges as per above diagram
        List<Edge> edges = Arrays.asList(
                new Edge(1, 2), new Edge(1, 3), new Edge(1, 3), new Edge(1, 4),
                new Edge(2, 5), new Edge(2, 6), new Edge(5, 9),
                new Edge(5, 10), new Edge(4, 7), new Edge(4, 8),
                new Edge(7, 11), new Edge(7, 12)
                // vertex 0, 13 and 14 are single nodes
        );
 
        // Set number of vertices in the graph
        final int N = 15;
 
        // create a graph from edges
        Graph graph = new Graph(edges, N);
 
        // stores vertex is discovered or not
        boolean[] discovered = new boolean[N];
 
        // Do BFS traversal from all undiscovered nodes to
        // cover all unconnected components of graph
        for (int i = 0; i < N; i++) {
            if (discovered[i] == false) {
                // start BFS traversal from vertex i
                BFS(graph, i, discovered);
            }
        }
        System.out.println();
        System.out.println(graph);
        System.out.println(graph.ansPath(10));
    }
}

// Cannot filter duplicated data. 