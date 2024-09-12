// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package lab2;

import graph.Graph;
import graph.SimpleGraph;
import org.junit.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class TestFindPath {
  @Test public final void testOneVertex() {
    Graph<Integer> g = new SimpleGraph(1);
    List<Integer> path = Lab2.findPath(g, 0, 0);
    assertThat(path).containsExactly(0);
  }

  @Test public final void testTwoVerticesNoPath() {
    Graph<Integer> g = new SimpleGraph(2);
    List<Integer> path = Lab2.findPath(g, 0, 1);
    assertThat(path).isEmpty();
  }

  @Test public final void testPathOfTwoVertices() {
    Graph<Integer> g = new SimpleGraph(2, new int[][] {
        {0, 1}, {1, 0},
    });
    List<Integer> path = Lab2.findPath(g, 0, 1);
    assertThat(path).containsExactly(0, 1).inOrder();
  }

  @Test public final void testPathOfThreeVertices() {
    Graph<Integer> g = new SimpleGraph(3, new int[][] {
        {0, 1}, {1, 0},
        {0, 2}, {2, 0},
    });
    List<Integer> path = Lab2.findPath(g, 1, 2);
    assertThat(path).containsExactly(1, 0, 2).inOrder();
  }

  @Test public final void testPathOfFiveVertices() {
    Graph<Integer> g = new SimpleGraph(5, new int[][] {
        {0, 1}, {1, 0},
        {1, 2}, {2, 1},
        {2, 3}, {3, 2},
        {3, 4}, {4, 3},
    });
    List<Integer> path = Lab2.findPath(g, 4, 0);
    assertThat(path).containsExactly(4, 3, 2, 1, 0).inOrder();
  }

}
