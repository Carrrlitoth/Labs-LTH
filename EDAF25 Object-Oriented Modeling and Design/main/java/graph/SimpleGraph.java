// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package graph;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A directed graph with a fixed number of vertices and edges.
 *
 * <p>Each vertex is represented by an integer from 0 to n-1, where n is the number of
 * vertices in the graph.
 * <p>
 * Edges are stored with adjacency lists for each vertex.
 */
public class SimpleGraph implements Graph<Integer> {
  private final int vertexCount;
  private final Map<Integer, Set<Integer>> adjacent;

  /**
   * Creates a graph with the edges listed in the second parameter.
   *
   * @param vertexCount number of vertices in the graph
   * @param edges array of directed edges in the format {@code {{source, dest}}}
   */
  public SimpleGraph(int vertexCount, int[]... edges) {
    checkArgument(vertexCount >= 0, "vertex count must be non-negative");
    this.vertexCount = vertexCount;
    adjacent = new HashMap<>(vertexCount);
    for (int i = 0; i < edges.length; ++i) {
      int u = edges[i][0];
      int v = edges[i][1];
      checkArgument(u != v, "edge (%s, %s) has same source and destination", u, v);
      checkArgument(u >= 0, "edge (%s, %s) has negative source", u, v);
      checkArgument(v >= 0, "edge (%s, %s) has negative destination", u, v);
      checkArgument(u < vertexCount, "edge (%s, %s) has out of bounds source", u, v);
      checkArgument(v < vertexCount, "edge (%s, %s) has out of bounds destination", u, v);
      Set<Integer> adj = adjacent.get(u);
      if (adj == null) {
        adj = new LinkedHashSet<>(); // LinkedHashSet to use edge insertion order for iteration.
        adjacent.put(u, adj);
      }
      checkArgument(!adj.contains(v), "cannot create duplicate edge (%s, %s)", u, v);
      adj.add(v);
    }
  }

  @Override public int vertexCount() {
    return vertexCount;
  }

  @Override public Collection<Integer> vertexSet() {
    return IntStream.range(0, vertexCount).boxed().collect(Collectors.toList());
  }

  @Override public Collection<Integer> neighbours(Integer v) {
    return Collections.unmodifiableSet(adjacent.getOrDefault(v, Collections.emptySet()));
  }

  /**
   * Returns a String representation of the vertices and edges of the graph.
   *
   * @return A String representation of the graph.
   */
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("digraph G {\n");
    for (int u = 0; u < vertexCount; ++u) {
      for (int v : neighbours(u)) {
        str.append(String.format("\t%d -> %d\n", u, v));
      }
    }
    str.append("}");
    return str.toString();
  }
}
