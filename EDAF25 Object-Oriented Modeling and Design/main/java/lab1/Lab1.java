// NOT TO BE PUBLISHED ON GITHUB. DO NOT SHARE LAB SOLUTIONS.
package lab1;

import graph.Graph;
import graph.SimpleGraph;

public class Lab1 {
  /**
   * Returns the number of vertices in the graph g.
   */
  public static int vertexCount(Graph<Integer> g) {
    // TODO(D1): implement this!
    return g.vertexCount();
  }

  /**
   * Returns the number of edges in the graph g.
   */
  public static int edgeCount(Graph<Integer> g) {
    int sum = 0;
    for (Integer vertex : g.vertexSet()) {
      sum += g.neighbours(vertex).size();
    }

    return sum;
  }

  /**
   * Returns true if there is an edge from vertex u to vertex v.
   * Returns false if u and v are not connected or if there is only an edge from v to u.
   *
   * @param g a graph containing u and v
   * @param u index of the first vertex in g
   * @param v index of the second vertex in g
   */
  public static boolean edgeBetween(Graph<Integer> g, int u, int v) {
    // TODO(D3): implement this!
    return g.neighbours(u).contains(v);
  }

  /**
   * Returns a simple graph with at least 6 vertices and at least 10 edges.
   */
  public static Graph<Integer> buildGraph() {
    // TODO(D5): implement this!
    return new SimpleGraph(7, new int[][] {
      { 0, 1 },
      { 1, 0 },
      { 1, 2 },
      { 2, 1 },
      { 3, 2 },
      { 3, 1 },
      { 4, 3 },
      { 4, 2 },
      { 4, 1 },
      { 5, 4 },
      { 5, 3 },
      { 5, 2 },
      { 5, 1 },
      { 6, 5 },
      { 6, 4 },
      { 6, 3 },
      { 6, 2 },
      { 6, 1 },
    });
  }
}
