// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package lab5;

/**
 * Represents an edge in a flowgraph.
 */
public class FlowEdge {
  public final int source;
  public final int destination;
  public final int maxCapacity;

  public FlowEdge(int source, int destination, int maxCapacity) {
    this.source = source;
    this.destination = destination;
    this.maxCapacity = maxCapacity;
  }

  @Override public String toString() {
    return String.format("%d -> %d [%s]", source, destination, maxCapacity);
  }
}
