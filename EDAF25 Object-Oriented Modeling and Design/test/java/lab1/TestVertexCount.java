// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package lab1;

import graph.Graph;
import graph.SimpleGraph;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestVertexCount {

  @Test public void vertexCount0() {
    Graph<Integer> g = new SimpleGraph(0);
    assertEquals(0, Lab1.vertexCount(g));
  }

  @Test public void vertexCount1() {
    Graph<Integer> g = new SimpleGraph(1);
    assertEquals(1, Lab1.vertexCount(g));
  }

  @Test public void vertexCount2() {
    Graph<Integer> g = new SimpleGraph(2);
    assertEquals(2, Lab1.vertexCount(g));
  }

  @Test public void vertexCount5() {
    Graph<Integer> g = new SimpleGraph(5, new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 0}});
    assertEquals(5, Lab1.vertexCount(g));
  }
}
