package algo;

import java.util.LinkedList;

/**
 * Representations: Adjacency list representation
 */
public class Graph {

    private Integer numVertices;

    private LinkedList<Integer> adjListArr[];

    /**
     * Given #numVertices initialize the ajdList array and each list associated with each vertex.
     */
    public Graph(Integer numVertices) {
        this.numVertices = numVertices;
        adjListArr = new LinkedList[numVertices];

        for (int i = 0; i < adjListArr.length; i++) {
            adjListArr[i] = new LinkedList<Integer>();
        }
    }

    /**
     * Applications:
     * <ol>
     * <li> <b>Gives Minimum Spanning Tree</b> and All pair shortest path for <b>Unweighted Graph</b>
     * <li> <b>Detect cycle in a graph</b> by checking for back edges in a DFS.
     * <li> <b>General path (not shortest) finding</b> b/w two vertices u->v. Start dfs(u) while keeping track of visited nodes
     * in one depth and once you reach v print the stack (path) contents.
     * <li> <b>Topological sort</b>
     * <li>Test for a graph being <b>Bipartite</b></li>
     * <li>Finding strongly connected components in a graph</li>
     * </ol>
     * <p>
     * Impl Considerations:
     * <p>
     * Algo: Recursively visit adjacent nodes of a given node. Gotchas:
     * <ol>
     * <li>Don't apply recursion on a visited node. Solve this using a flag for each node</li>
     * <li> Handle case for disconnected graph (forest) </li>
     * </ol>
     */
    public void dfs(Integer startIndex) {

        // maintain visited array defaulting each to zero for primitive boolean.
        boolean visited[] = new boolean[numVertices];

        // for each (potential) forest visit adjacent nodes recursively
        for (int i = 0; i < numVertices; i++) {
            startIndex = (startIndex + 1) % numVertices;

            // check for false forest
            if (!visited[startIndex]) {
                forestDFS(startIndex, visited);
            }

        }
    }

    private void forestDFS(Integer startIndex, final boolean[] visited) {

        System.out.println(startIndex);
        visited[startIndex] = true;

        for (Integer n : adjListArr[startIndex]) {
            if (!visited[n]) {
                forestDFS(n, visited);
            }
        }
    }


    public void bfs(Integer startNodeIndex) {
    }

    /*
     * Typical Application: Build order of dependencies in a project
     * Constraints: The input dependency graph must be DAG (Directed Acyclic Graph) i.e. no cycles
     */
    public void topologicalSort() {

    }
}
