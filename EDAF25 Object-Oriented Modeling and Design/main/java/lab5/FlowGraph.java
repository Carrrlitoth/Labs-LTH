// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package lab5;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A flow graph with integer edge capacities.
 */
public class FlowGraph {
  private final int vertexCount;
  private final int[][] capacity;

  /**
   * Creates a flow graph with the edges listed in the second parameter.
   *
   * @param vertexCount number of vertices in the graph
   * @param edges list of directed edges
   */
  public FlowGraph(int vertexCount, FlowEdge... edges) {
    checkArgument(vertexCount >= 2,
        "vertex count must be at least 2 (source and sink must always exist)");
    this.capacity = new int[vertexCount][vertexCount];
    this.vertexCount = vertexCount;
    Map<Integer, Set<Integer>> adjacent = new HashMap<>(vertexCount);
    for (FlowEdge edge : edges) {
      int u = edge.source;
      int v = edge.destination;
      checkArgument(u != v, "edge (%s, %s) has same source and destination", u, v);
      checkArgument(u >= 0, "edge (%s, %s) has negative source", u, v);
      checkArgument(v >= 0, "edge (%s, %s) has negative destination", u, v);
      checkArgument(u < vertexCount, "edge (%s, %s) has out of bounds source", u, v);
      checkArgument(v < vertexCount, "edge (%s, %s) has out of bounds destination", u, v);
      Set<Integer> adj = adjacent.get(u);
      if (adj == null) {
        adj = new HashSet<>();
        adjacent.put(u, adj);
      }
      checkArgument(!adj.contains(v), "cannot create duplicate edge (%s, %s)", u, v);
      adj.add(v);
      capacity[u][v] = edge.maxCapacity;
    }
  }

  public int vertexCount() {
    return vertexCount;
  }

  /**
   * Returns the capacity on the edge from node u to v. If there is no
   * such edge, 0 is returned.
   */
  public int getCapacity(int u, int v) {
    checkArgument(u >= 0 && u < vertexCount, "argument u is out of bounds");
    checkArgument(v >= 0 && v < vertexCount, "argument v is out of bounds");
    return capacity[u][v];
  }
}
