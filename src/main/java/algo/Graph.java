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


    /**
     * Algo: For a given node visit all of it's adjacent nodes and then repeat this for each of next nodes.
     * Gotchas: Don't visit if a node if it has been already visited.
     * DS:
     * 1. Use a queue to maintain order of nodes to be visited. java has {@link LinkedList} that can be used for FIFO.
     * 2. Use a visited array of flags to note if a node is visited.
     */
    public void bfs(Integer startNodeIndex) {
        // create a visited array. In array type instance default values are assigned. For boolean it's false.
        boolean visited[] = new boolean[numVertices];
        LinkedList<Integer> queue = new LinkedList<Integer>();

        queue.addLast(startNodeIndex);

        while (!queue.isEmpty()) {
            // visit current node.
            Integer poppedNode = queue.removeFirst();
            System.out.println(poppedNode);

            visited[poppedNode] = true;
            LinkedList<Integer> adjNodes = adjListArr[poppedNode];
            for (Integer node : adjNodes) {
                if (!visited[node])
                    queue.addLast(node);
            }
        }

    }

    /**
     * For an acyclic DAG print a node before all it's adjacent nodes.
     *
     * Logic DFS looks good with one exception: If second (or later) visited connected component (forest) has an edge
     * directed to node visited in previous forest. In DFS your shall print second (or later) forest's node after previous one.
     * This shall violate the topological sort constraint.
     *
     * Fix: All connected components visited later should be printed first.
     *
     * Solution: Use a stack to push all element visited in #forestDFS AFTER recursing on it's adjacent nodes.
     *           Using this the second and later forests shall be pushed later and thus printed first.
     *
     * Gotchas:
     *     -> First two same as DFS ( don't revisit if already visited and cover disconnected components)
     *     -> Third: Ensure there is no cycle if not mentioned explicitly in the problem statement.
     *         -> For this, check if within a traversal of a connected sub-graph you don't revisit a node again.
     *             -> To implement that use flagging array for each connected sub-graph.
     */
    public void topologicalSort() {

    }

}
