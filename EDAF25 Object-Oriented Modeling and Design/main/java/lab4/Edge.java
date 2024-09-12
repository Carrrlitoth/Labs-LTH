// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package lab4;

/**
 * An edge in a generic graph with an associated distance metric.
 */
public class Edge {
  public final int source;
  public final int destination;
  public final int distance;

  public Edge(int source, int destination, int distance) {
    this.source = source;
    this.destination = destination;
    this.distance = distance;
  }

  @Override public String toString() {
    return String.format("%d -> %d [%s]", source, destination, distance);
  }
}
