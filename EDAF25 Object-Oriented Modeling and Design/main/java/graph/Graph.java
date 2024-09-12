// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package graph;

import java.util.Collection;

/**
 * A generic directed graph.
 *
 * <p>For a graph with n vertices, the  are numbered from 0 to n-1.
 *
 * @param <T> the type of the vertex values in this graph
 */
public interface Graph<T> {
  /**
   * Returns the number of vertices in this graph.
   */
  int vertexCount();

  /**
   * Returns the collection of vertices in this graph.
   */
  Collection<T> vertexSet();

  /**
   * Returns the collection of neighbours for vertex v.
   * If v is not part of the graph, an empty list is returned.
   */
  Collection<T> neighbours(T v);
}
