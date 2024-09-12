// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package lab1;

import graph.Graph;
import graph.SimpleGraph;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class TestEdgeBetween {

  @Test public void singleEdge() {
    Graph<Integer> g = new SimpleGraph(10, new int[][] {{4, 7}});
    assertThat(Lab1.edgeBetween(g, 4, 7)).isTrue();
    assertThat(Lab1.edgeBetween(g, 7, 4)).isFalse();
  }

  @Test public void simplePath() {
    Graph<Integer> g = new SimpleGraph(5, new int[][] {
        {0, 1},
        {1, 2},
        {2, 3},
        {3, 4},
    });
    assertThat(Lab1.edgeBetween(g, 0, 1)).isTrue();
    assertThat(Lab1.edgeBetween(g, 1, 2)).isTrue();
    assertThat(Lab1.edgeBetween(g, 2, 3)).isTrue();
    assertThat(Lab1.edgeBetween(g, 3, 4)).isTrue();
    assertThat(Lab1.edgeBetween(g, 4, 0)).isFalse();
    assertThat(Lab1.edgeBetween(g, 0, 2)).isFalse();
    assertThat(Lab1.edgeBetween(g, 0, 3)).isFalse();
    assertThat(Lab1.edgeBetween(g, 0, 4)).isFalse();
    assertThat(Lab1.edgeBetween(g, 1, 0)).isFalse();
    assertThat(Lab1.edgeBetween(g, 2, 0)).isFalse();
    assertThat(Lab1.edgeBetween(g, 3, 2)).isFalse();
    assertThat(Lab1.edgeBetween(g, 4, 1)).isFalse();
  }

  @Test public void loop() {
    Graph<Integer> g = new SimpleGraph(5, new int[][] {
        {0, 1},
        {1, 2},
        {2, 3},
        {3, 4},
        {4, 0}
    });
    assertThat(Lab1.edgeBetween(g, 0, 1)).isTrue();
    assertThat(Lab1.edgeBetween(g, 1, 2)).isTrue();
    assertThat(Lab1.edgeBetween(g, 2, 3)).isTrue();
    assertThat(Lab1.edgeBetween(g, 3, 4)).isTrue();
    assertThat(Lab1.edgeBetween(g, 4, 0)).isTrue();
    assertThat(Lab1.edgeBetween(g, 0, 2)).isFalse();
    assertThat(Lab1.edgeBetween(g, 0, 3)).isFalse();
    assertThat(Lab1.edgeBetween(g, 0, 4)).isFalse();
    assertThat(Lab1.edgeBetween(g, 1, 0)).isFalse();
    assertThat(Lab1.edgeBetween(g, 2, 0)).isFalse();
    assertThat(Lab1.edgeBetween(g, 3, 2)).isFalse();
    assertThat(Lab1.edgeBetween(g, 4, 1)).isFalse();
  }

}
