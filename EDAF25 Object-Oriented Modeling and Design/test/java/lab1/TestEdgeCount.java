// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package lab1;

import graph.Graph;
import graph.SimpleGraph;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class TestEdgeCount {
  @Test public void edgeCount0() {
    Graph<Integer> g = new SimpleGraph(0);
    assertThat(Lab1.edgeCount(g)).isEqualTo(0);
  }

  @Test public void edgeCount1() {
    Graph<Integer> g = new SimpleGraph(1);
    assertThat(Lab1.edgeCount(g)).isEqualTo(0);
  }

  @Test public void edgeCount2() {
    Graph<Integer> g = new SimpleGraph(2);
    assertThat(Lab1.edgeCount(g)).isEqualTo(0);
  }

  @Test public void edgeCount3() {
    Graph<Integer> g = new SimpleGraph(5, new int[][] {
        {0, 1},
        {1, 2},
        {2, 3},
        {3, 4},
        {4, 0},
    });
    assertThat(Lab1.edgeCount(g)).isEqualTo(5);
  }

  @Test public void edgeBetweenSimplePath() {
    Graph<Integer> g = new SimpleGraph(5, new int[][] {
        {0, 1},
        {1, 2},
        {2, 3},
        {3, 4},
    });
    assertThat(Lab1.edgeCount(g)).isEqualTo(4);
  }
}
