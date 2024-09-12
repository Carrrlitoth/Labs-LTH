// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package graph;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/** These tests should work as is (not part of lab assignments). */
public class TestGraph {
  @Test public void testToString() {
    Graph<Integer> g = new SimpleGraph(5, new int[][] {{0, 3}, {0, 1}, {3, 1},});
    assertThat(g.toString()).isEqualTo(
        "digraph G {\n"
        + "\t0 -> 3\n"
        + "\t0 -> 1\n"
        + "\t3 -> 1\n"
        + "}");
  }

  @Test public void testNeighbours() {
    Graph<Integer> g = new SimpleGraph(5, new int[][] {{0, 3}, {0, 1}, {3, 1},});
    assertThat(g.neighbours(0)).containsExactly(1, 3);
    assertThat(g.neighbours(1)).containsExactly();
    assertThat(g.neighbours(3)).containsExactly(1);
  }

  @Test(expected=IllegalArgumentException.class) public void testNonSimple() {
    new SimpleGraph(1, new int[][] {{0,0}});
  }

  @Test(expected=IllegalArgumentException.class) public void testEdgeOutOfBounds() {
    new SimpleGraph(2, new int[][] {{0,2}});
  }

  @Test(expected=IllegalArgumentException.class) public void testEdgeOutOfBounds2() {
    new SimpleGraph(2, new int[][] {{-1,0}});
  }
}
