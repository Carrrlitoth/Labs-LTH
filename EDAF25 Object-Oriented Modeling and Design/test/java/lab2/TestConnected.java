// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package lab2;

import graph.Graph;
import graph.SimpleGraph;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class TestConnected {
  @Test public final void oneVertex() {
    // A graph with one vertex is connected.
    Graph<Integer> g = new SimpleGraph(1);
    assertThat(Lab2.isConnected(g)).isTrue();
  }

  @Test public final void twoVerticesNoEdge() {
    // A graph with two vertices and no edges is not connected.
    Graph<Integer> g = new SimpleGraph(2);
    assertThat(Lab2.isConnected(g)).isFalse();
  }

  @Test public final void twoVerticesConnected1() {
    Graph<Integer> g = new SimpleGraph(2, new int[][] {
        {0, 1}, {1, 0}
    });
    assertThat(Lab2.isConnected(g)).isTrue();
  }

  @Test public final void twoVerticesConnected2() {
    Graph<Integer> g = new SimpleGraph(2, new int[][] {
        {0, 1}, {1, 0},
    });
    assertThat(Lab2.isConnected(g)).isTrue();
  }

  @Test public final void testConnectedThreeVertices() {
    Graph<Integer> g = new SimpleGraph(3, new int[][] {
        {0, 1}, {1, 0},
        {0, 2}, {2, 0},
    });
    assertThat(Lab2.isConnected(g)).isTrue();
  }

  @Test public final void testNotConnectedThreeVertices() {
    Graph<Integer> g = new SimpleGraph(3, new int[][] {
        {1, 2}, {2, 1},
    });
    assertThat(Lab2.isConnected(g)).isFalse();
  }

  @Test public final void testConnectedFiveVertices() {
    Graph<Integer> g = new SimpleGraph(5, new int[][] {
        {0, 1}, {1, 0},
        {0, 2}, {2, 0},
        {1, 2}, {2, 1},
        {0, 3}, {3, 0},
        {3, 4}, {4, 3},
    });
    assertThat(Lab2.isConnected(g)).isTrue();
  }

  @Test public final void testNotConnectedFiveVertices() {
    Graph<Integer> g = new SimpleGraph(5, new int[][] {
        {0, 1}, {1, 0},
        {0, 2}, {2, 0},
        {1, 2}, {2, 1},
        {3, 4}, {4, 3},
    });
    assertThat(Lab2.isConnected(g)).isFalse();
  }

}
