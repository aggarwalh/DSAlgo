package algo;

import org.junit.Test;

public class GraphTest {

    @Test
    public void testTopologicalSort(){
        Graph graph = new Graph(3);
        graph.addEdge(0,1);
        graph.addEdge(2,1);
        System.out.println("dfs doesn't give topological sort in spl cases of disconnected graph ");
        graph.dfs(0); // demo
        System.out.println("Result from topological sort");
        graph.topologicalSort();
    }

}
